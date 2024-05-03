package site.cilicili.backend.user.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-31 14:58:47
 */

/**
 * (SysUserRole)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-31 14:58:47
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysUserRole 实体类")
@TableName("sys_user_role")
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRoleEntity implements Serializable {
    private static final long serialVersionUID = 464445815074406289L;

    @Schema(description = "'角色编码'")
    private String sysRoleRoleCode;

    @TableId
    @Schema(description = "'用户名'")
    private String sysUserUsername;
}
