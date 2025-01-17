package site.cilicili.common.mvcConfig;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.log.domain.dto.SysLogOperationDto;
import site.cilicili.backend.log.domain.pojo.SysLogOperationEntity;
import site.cilicili.backend.log.service.SysLogOperationService;
import site.cilicili.common.config.dynamicDb.AutoUpdateTableTime;
import site.cilicili.common.config.dynamicDb.annotation.DbChangeConfig;
import site.cilicili.common.config.dynamicDb.dataSource.DbInitialization;
import site.cilicili.common.util.IpUtil;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.mvcConfig
 * Date:2024/1/20 20:34
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Component
@Aspect
@RequiredArgsConstructor
public class LogOperationAspect {
    private final Logger logger = LoggerFactory.getLogger(LogOperationAspect.class);
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final SysLogOperationService sysLogOperationService;
    private final AutoUpdateTableTime autoUpdateTableTime;
    private final ObjectMapper objectMapper;
    private final DbInitialization dbInitialization;
    private final DbChangeConfig dbChangeConf;

    @Pointcut("execution(* site.cilicili..controller.*.*(..))")
    public void logOperationPointCut() {
        // 记录操作日志
    }

    @Pointcut("execution(* site.cilicili.backend.log.controller.SysLogOperationController.*(..))")
    public void excludeLogOperationPointCut() {
    }

    @AfterReturning(value = "logOperationPointCut()&&!excludeLogOperationPointCut()", returning = "r")
    public void logOperation(R r) {
        writeOperationLog(response.getStatus(), request, r.getMessage(), r);
    }

    public void writeOperationLog(Integer status, HttpServletRequest request, String memo, Object r) {
        Optional.ofNullable(r)
                .filter(res -> dbInitialization.isValid())
                .map(res -> {
                    try {
                        if (res instanceof R r1) {
                            res = BeanUtil.toBean(r1, R.class).setData(null);
                        }
                        return SysLogOperationDto.builder()
                                .operationBody(objectMapper.writeValueAsString(res))
                                .operationIp(IpUtil.getRemoteIp(request))
                                .operationApi(request.getRequestURI())
                                .operationMethod(request.getMethod())
                                .memo(memo)
                                .operationStatus(Integer.toUnsignedLong(status))
                                .operationUsername(Optional.ofNullable(autoUpdateTableTime.getAuthUserDetails())
                                        .map(AuthUserDetails::getRealName)
                                        .orElse(null));

                    } catch (JsonProcessingException e) {
                        logger.error(e.getMessage());
                    }
                    return null;
                })
                .map(sysLogOperationDto -> BeanUtil.toBean(sysLogOperationDto.build(), SysLogOperationEntity.class))
                .ifPresent(sysLogOperationService::insert);
    }
}
