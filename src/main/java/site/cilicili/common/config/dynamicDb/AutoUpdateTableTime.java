package site.cilicili.common.config.dynamicDb;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

    private UserDetails authUserDetails;

    @Override
    public void insertFill(final MetaObject metaObject) {
        this.setFieldValByName("createdAt", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
        this.setFieldValByName("delete", 0, metaObject);
        this.setFieldValByName("updatedBy", authUserDetails.getUsername(), metaObject);
        this.setFieldValByName("createdBy", authUserDetails.getUsername(), metaObject);
    }

    @Override
    public void updateFill(final MetaObject metaObject) {
        this.setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updatedBy", authUserDetails.getUsername(), metaObject);
        this.setFieldValByName("updatedBy", authUserDetails.getUsername(), metaObject);
    }
}
