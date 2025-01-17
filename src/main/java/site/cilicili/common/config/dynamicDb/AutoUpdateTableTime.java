package site.cilicili.common.config.dynamicDb;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.common.util.AddressUtils;
import site.cilicili.common.util.IpUtil;
import site.cilicili.frontend.comments.domain.pojo.VideoCommentsEntity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.config.dynamicDb
 * Date:2023/12/9 13:19
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Component
@RequiredArgsConstructor
public class AutoUpdateTableTime implements MetaObjectHandler {
    private final HttpServletRequest httpServletRequest;

    @Override
    public void insertFill(final MetaObject metaObject) {
        if (metaObject.getOriginalObject() instanceof VideoCommentsEntity) {
            this.setFieldValByName(
                    "address",
                    String.format("来自%1$s", AddressUtils.getRealAddressByIP(IpUtil.getRemoteIp(httpServletRequest))),
                    metaObject);
        }
        Optional.of(Objects.isNull(this.getFieldValByName("createdAt", metaObject)))
                .filter(f -> f)
                .ifPresent(s -> this.setFieldValByName("createdAt", LocalDateTime.now(), metaObject));
        this.setFieldValByName("logicalDelete", 1, metaObject);
        Optional.ofNullable(getAuthUserDetails())
                .ifPresent(authUserDetails ->
                        this.setFieldValByName("createdBy", authUserDetails.getUsername(), metaObject));
    }

    @Override
    public void updateFill(final MetaObject metaObject) {
        if (metaObject.getOriginalObject() instanceof VideoCommentsEntity) {
            this.setFieldValByName(
                    "address",
                    String.format("来自%1$s", AddressUtils.getRealAddressByIP(IpUtil.getRemoteIp(httpServletRequest))),
                    metaObject);
        }
        this.setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
        Optional.ofNullable(getAuthUserDetails())
                .ifPresent(authUserDetails ->
                        this.setFieldValByName("updatedBy", authUserDetails.getUsername(), metaObject));
    }

    public AuthUserDetails getAuthUserDetails() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof AuthUserDetails authUserDetails) {
                        return authUserDetails;
                    }
                    final String s = "" + authentication.getPrincipal();
                    return AuthUserDetails.builder().username(s).realName(s).build();
                })
                .orElse(null);
    }
}
