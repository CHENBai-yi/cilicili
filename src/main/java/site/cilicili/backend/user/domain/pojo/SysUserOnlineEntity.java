package site.cilicili.backend.user.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-18 20:39:30
 */

/**
 * (SysUserOnline)实体类
 *
 * @author ChenBaiYi
 * @since 2024-01-18 20:39:30
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysUserOnline 实体类")
@TableName("sys_user_online")
public class SysUserOnlineEntity implements Serializable {
    private static final long serialVersionUID = -58085958790198870L;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "token")
    private String token;
}
