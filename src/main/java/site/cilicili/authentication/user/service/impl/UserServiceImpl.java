package site.cilicili.authentication.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RHyperLogLog;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.authentication.user.dto.UserDto;
import site.cilicili.authentication.user.entity.UserEntity;
import site.cilicili.authentication.user.repository.UserRepository;
import site.cilicili.authentication.user.service.EmailService;
import site.cilicili.authentication.user.service.UserService;
import site.cilicili.backend.log.service.SysLogLoginService;
import site.cilicili.backend.user.domain.dto.KickOnlineUserRequest;
import site.cilicili.backend.user.domain.pojo.SysUserRoleEntity;
import site.cilicili.backend.user.service.SysUserOnlineService;
import site.cilicili.backend.user.service.SysUserRoleService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.JwtUtils;
import site.cilicili.common.util.R;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

/**
 * @author BaiYiChen
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserRepository, UserEntity> implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final SysUserOnlineService sysUserOnlineService;
    private final SysLogLoginService sysLogLoginService;
    private final RHyperLogLog<Object> ipAddressHyperLogLog;
    private final SysUserRoleService sysUserRoleService;
    private final StringRedisTemplate stringRedisTemplate;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    @Override
    public UserDto registration(final UserDto.Registration registration) {
        userRepository.findByUsernameOrEmail(registration.getUsername(), registration.getEmail()).stream()
                .findAny()
                .ifPresent(entity -> {
                    throw new AppException(Error.DUPLICATED_USER);
                });
        UserEntity userEntity = UserEntity.builder()
                .username(registration.getUsername())
                .email(registration.getEmail())
                .password(passwordEncoder.encode(registration.getPassword()))
                .avatar("")
                .build();
        userRepository.insert(userEntity);
        return convertEntityToDto(userEntity);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R login(UserDto.Login login, final HttpHeaders headers) {
        ipAddressHyperLogLog.add(Objects.requireNonNull(headers.getHost()).getHostString());
        if (Objects.nonNull(login.getCode()) && StrUtil.isNotEmpty(login.getCode())) {
            final String code = stringRedisTemplate.opsForValue().get(login.getEmail());
            if (Objects.nonNull(code) && StrUtil.isNotEmpty(code)) {
                if (!code.equals(login.getCode())) {
                    return R.no("验证码不正确！");
                }
                login.setPassword(code);
            } else {
                return R.no("验证码过期.");
            }
        }
        return userRepository
                .findByUsername(login.getUsername(), login.getEmail())
                .map(r -> Optional.ofNullable(sysUserOnlineService.queryById(r.getUsername(), null))
                        .filter(userEntity -> stringRedisTemplate.hasKey(r.getUsername()))
                        .map(rr -> R.no(Error.ALREADY_LOGIN.getMessage()))
                        .orElseGet(() -> Optional.of(r)
                                .filter(user -> login.getPassword().equals(login.getCode())
                                        || passwordEncoder.matches(login.getPassword(), user.getPassword()))
                                .map(user -> {
                                    login.setEmail(user.getEmail());
                                    login.setUsername(user.getUsername());
                                    return convertEntityToDto(user);
                                })
                                .filter(userDto ->
                                        sysUserOnlineService.insertOrUpdate(userDto.getUsername(), userDto.getToken()))
                                .map(userDto -> R.yes("登录成功.").setData(userDto))
                                .orElse(R.no("登陆失败!"))))
                .orElse(R.no(Error.LOGIN_INFO_INVALID.getMessage()));
    }

    private UserDto convertEntityToDto(UserEntity userEntity) {
        return UserDto.builder()
                .username(userEntity.getUsername())
                .nickname(userEntity.getNickname())
                .avatar(userEntity.getAvatar())
                .realName(userEntity.getRealName())
                .gender(userEntity.getGender())
                .mobile(userEntity.getMobile())
                .token(jwtUtils.encode(userEntity.getUsername()))
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto currentUser(AuthUserDetails authUserDetails) {
        UserEntity userEntity = Optional.ofNullable(userRepository.selectById(authUserDetails.getId()))
                .orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));
        return convertEntityToDto(userEntity);
    }

    @Override
    public UserDto update(UserDto.Update update, AuthUserDetails authUserDetails) {
        UserEntity userEntity = Optional.ofNullable(userRepository.selectById(authUserDetails.getId()))
                .orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));

        if (update.getUsername() != null) {
            userRepository
                    .findByUsername(update.getUsername(), update.getEmail())
                    .filter(found -> !found.getId().equals(userEntity.getId()))
                    .ifPresent(found -> {
                        throw new AppException(Error.DUPLICATED_USER);
                    });
            userEntity.setUsername(update.getUsername());
        }

        if (update.getEmail() != null) {
            userRepository
                    .findByEmail(update.getEmail())
                    .filter(found -> !found.getId().equals(userEntity.getId()))
                    .ifPresent(found -> {
                        throw new AppException(Error.DUPLICATED_USER);
                    });
            userEntity.setEmail(update.getEmail());
        }

        if (update.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(update.getPassword()));
        }

        if (update.getBio() != null) {
            userEntity.setAvatar(update.getBio());
        }

        if (update.getImage() != null) {
            userEntity.setAvatar(update.getImage());
        }

        userRepository.insert(userEntity);
        return convertEntityToDto(userEntity);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R logout(final AuthUserDetails authUserDetails, final KickOnlineUserRequest kickOnlineUserRequest) {
        return Optional.ofNullable(authUserDetails)
                .map(auth -> this.sysUserOnlineService.kickOnlineUser(kickOnlineUserRequest))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R frontendRegistration(final UserDto.FrontendRegistration registration) {
        return Optional.ofNullable(stringRedisTemplate.opsForValue().get(registration.getEmail()))
                .map(code -> {
                    if (!code.equals(registration.getCode())) {
                        return R.no("验证码不正确！");
                    }
                    return baseMapper
                            .findByEmail(registration.getEmail())
                            .map(userEntity -> R.no("该账号已被注册"))
                            .orElseGet(() -> {
                                registration.setUsername(String.format("%1$s%2$s", "用户", RandomUtil.randomNumbers(4)));
                                registration.setPassword(passwordEncoder.encode(registration.getPassword()));
                                if (saveOrUpdate(BeanUtil.toBean(registration, UserEntity.class))) {
                                    final SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity(
                                            registration.getRoleCode(), registration.getUsername());
                                    if (sysUserRoleService.saveOrUpdate(sysUserRoleEntity)) {
                                        return R.yes("注册成功！");
                                    }
                                }
                                return R.no(Error.COMMON_EXCEPTION.getMessage());
                            });
                })
                .orElse(R.no("验证码过期."));
    }

    @Override
    public R getEmailCodeForReg(final String email) {
        return getEmailCode(email, "注册");
    }

    public R getEmailCode(final String email, final String option) {
        final String randomNumbers = RandomUtil.randomNumbers(6);
        final int time = 60;
        try {
            emailService.sendHtmlMail(
                    email, "cilicili注册验证码测试邮件：", resolveHtmlTemplate(option, randomNumbers, time, "秒"));
            stringRedisTemplate.opsForValue().setIfAbsent(email, randomNumbers, Duration.of(time, ChronoUnit.SECONDS));
            return R.yes("验证码发送成功！");
        } catch (IllegalArgumentException e) {
            return R.no("验证码已发送！");
        } catch (Exception e) {
            return R.no("验证码发送失败！");
        }
    }

    private String resolveHtmlTemplate(String option, String code, Integer time, String unit) {
        Context context = new Context();
        context.setVariable("code", code);
        context.setVariable("option", option);
        context.setVariable("time", time);
        context.setVariable("unit", unit);
        return templateEngine.process("EmailCodeTemplate", context);
    }
}
