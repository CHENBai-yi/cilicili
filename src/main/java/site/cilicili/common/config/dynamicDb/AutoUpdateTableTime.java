package site.cilicili.common.config.dynamicDb;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;

import java.time.LocalDateTime;
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
public class AutoUpdateTableTime implements MetaObjectHandler {

    @Override
    public void insertFill(final MetaObject metaObject) {
        Optional.ofNullable(getAuthUserDetails())
                .ifPresent(authUserDetails -> {
                    this.setFieldValByName("createdAt", LocalDateTime.now(), metaObject);
                    this.setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
                    this.setFieldValByName("logical_delete", 0, metaObject);
                    this.setFieldValByName("updatedBy", authUserDetails.getUsername(), metaObject);
                    this.setFieldValByName("createdBy", authUserDetails.getUsername(), metaObject);
                });

    }

    @Override
    public void updateFill(final MetaObject metaObject) {
        Optional.ofNullable(getAuthUserDetails())
                .ifPresent(authUserDetails -> {
                    this.setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
                    this.setFieldValByName("updatedBy", authUserDetails.getUsername(), metaObject);
                    this.setFieldValByName("updatedBy", authUserDetails.getUsername(), metaObject);
                });

    }

    public AuthUserDetails getAuthUserDetails() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(authentication -> (AuthUserDetails) authentication.getPrincipal()).orElseThrow(() -> new AppException(Error.LOGIN_INFO_INVALID));
    }
}
