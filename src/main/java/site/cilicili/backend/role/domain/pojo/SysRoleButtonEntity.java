package site.cilicili.backend.role.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * PROJECT: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-29 21:20:55
 */

/**
 * (SysRoleButton)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:20:55
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysRoleButton 实体类")
@TableName("sys_role_button")
public class SysRoleButtonEntity implements Serializable {
    private static final long serialVersionUID = 172774694717568380L;

    @TableId
    private String sysRoleRoleCode;

    private String sysButtonButtonCode;
}
