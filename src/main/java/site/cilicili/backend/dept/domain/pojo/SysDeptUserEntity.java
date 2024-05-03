package site.cilicili.backend.dept.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-30 16:36:52
 */

/**
 * (SysDeptUser)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-30 16:36:52
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysDeptUser 实体类")
@TableName("sys_dept_user")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysDeptUserEntity implements Serializable {
    private static final long serialVersionUID = 405586698921658966L;

    @Schema(description = "'部门编码'")
    @TableId
    private String sysDeptDeptCode;

    @Schema(description = "'用户名'")
    private String sysUserUsername;
}
