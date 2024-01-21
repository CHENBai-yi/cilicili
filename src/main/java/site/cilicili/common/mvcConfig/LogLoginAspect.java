package site.cilicili.common.mvcConfig;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.authentication.user.dto.UserDto;
import site.cilicili.backend.log.domain.dto.SysLogLoginDto;
import site.cilicili.backend.log.domain.pojo.SysLogLoginEntity;
import site.cilicili.backend.log.service.SysLogLoginService;
import site.cilicili.backend.user.domain.dto.KickOnlineUserRequest;
import site.cilicili.backend.user.domain.pojo.SysUserEntity;
import site.cilicili.backend.user.service.SysUserService;
import site.cilicili.common.config.dynamicDb.dataSource.DbInitialization;
import site.cilicili.common.util.IpUtil;
import site.cilicili.common.util.R;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.mvcConfig
 * Date:2024/1/20 20:33
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Component
@Aspect
@RequiredArgsConstructor
public class LogLoginAspect {
    private static final JSONConfig JSON_CONFIG = new JSONConfig();

    static {
        JSON_CONFIG.setIgnoreNullValue(true);
        JSON_CONFIG.setDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    private final Logger logger = LoggerFactory.getLogger(LogOperationAspect.class);
    private final SysLogLoginService sysLogLoginService;
    private final StringRedisTemplate stringRedisTemplate;
    private final SysUserService sysUserService;
    private final HttpServletRequest request;
    private final DbInitialization dbInitialization;

    @Pointcut("execution(* site.cilicili.authentication.user.controller.UsersController.login(..)) throws AppException")
    public void logLoginPointCut() {
    }

    @Pointcut(
            "execution(* site.cilicili.authentication.user.controller.UsersController.logout(..)) || execution(* site.cilicili.backend.user.controller.SysUserOnlineController.kickOnlineUser(..))")
    public void logLogoutPointCut() {
    }

    @AfterReturning(value = "logLoginPointCut()", returning = "res")
    public void logLogin(JoinPoint joinPoint, R res) {
        Optional.ofNullable(res)
                .filter(r -> dbInitialization.isValid())
                .flatMap(r -> Optional.ofNullable(joinPoint.getArgs()))
                .ifPresent(objects -> {
                    final UserDto.Login user = (UserDto.Login) objects[0];
                    final HttpHeaders httpHeaders = (HttpHeaders) objects[1];
                    final String agentString = httpHeaders.getFirst(HttpHeaders.USER_AGENT);
                    final UserAgent userAgent = UserAgentUtil.parse(agentString);
                    final SysLogLoginDto.SysLogLoginDtoBuilder builder = SysLogLoginDto.builder()
                            .loginOs(userAgent.getOs().getName())
                            .loginPlatform(userAgent.getPlatform().getName())
                            .loginBrowser(String.format(
                                    "%1$s %2$s", userAgent.getBrowser().getName(), userAgent.getVersion()))
                            .loginUsername(user.getUsername())
                            .stable("yesNo_no")
                            .status("onOff_on")
                            .memo(String.format("%1$s %2$s", res.getMessage(), agentString))
                            .sort(1L)
                            .loginIp(IpUtil.getRemoteIp(request))
                            .createdAt(LocalDateTime.now());
                    if (res.getCode() == 1) {
                        builder.loginSuccess("yesNo_yes");
                        stringRedisTemplate
                                .opsForValue()
                                .setIfAbsent(user.getUsername(), JSONUtil.toJsonStr(builder.build(), JSON_CONFIG));
                    } else {
                        builder.loginSuccess("yesNo_no");
                        sysLogLoginService.insert(BeanUtil.toBean(builder.build(), SysLogLoginEntity.class));
                    }
                    if (logger.isDebugEnabled()) {
                        logger.info("{} IP:{}-->{}", user.getUsername(), IpUtil.getRemoteIp(request), res);
                    }
                });
    }

    @After("logLogoutPointCut()")
    public void logLogout(JoinPoint joinPoint) {
        Optional.ofNullable(joinPoint.getArgs())
                .filter(objects -> dbInitialization.isValid())
                .map(objects -> objects[0])
                .map(o -> {
                    if (o instanceof AuthUserDetails authUserDetails) {
                        return authUserDetails;
                    }
                    if (o instanceof KickOnlineUserRequest kickOnlineUserRequest) {
                        return Optional.ofNullable(sysUserService.getOne(new QueryWrapper<SysUserEntity>()
                                        .eq("username", kickOnlineUserRequest.username())))
                                .map(sysUserEntity -> AuthUserDetails.builder()
                                        .username(sysUserEntity.getUsername())
                                        .realName(sysUserEntity.getRealName())
                                        .id(sysUserEntity.getId())
                                        .build())
                                .orElse(AuthUserDetails.builder()
                                        .username(kickOnlineUserRequest.username())
                                        .realName(kickOnlineUserRequest.username())
                                        .build());
                    }
                    return null;
                })
                .filter(authUserDetails -> stringRedisTemplate.hasKey(authUserDetails.getUsername()))
                .map(authUserDetails -> {
                    final SysLogLoginEntity sysLogLoginEntity = JSONUtil.toBean(
                            stringRedisTemplate.opsForValue().get(authUserDetails.getUsername()),
                            JSON_CONFIG,
                            SysLogLoginEntity.class);
                    sysLogLoginEntity.setLoginUsername(authUserDetails.getRealName());
                    sysLogLoginEntity.setUsername(authUserDetails.getUsername());
                    return sysLogLoginEntity;
                })
                .filter(sysLogLoginEntity -> stringRedisTemplate.delete(sysLogLoginEntity.getUsername()))
                .ifPresent(sysLogLoginEntity -> {
                    if (logger.isDebugEnabled()) {
                        logger.info("{} IP:{} 登出成功！", sysLogLoginEntity.getUsername(), sysLogLoginEntity.getLoginIp());
                    }
                    sysLogLoginEntity.setUpdatedAt(LocalDateTime.now());
                    sysLogLoginService.insert(sysLogLoginEntity);
                });
    }
}
