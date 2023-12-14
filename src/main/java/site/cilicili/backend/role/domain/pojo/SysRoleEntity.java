package site.cilicili.backend.role.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JacksonInject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * (SysRole)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-14 03:55:21
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysRole实体类")
@TableName("sys_role")
public class SysRoleEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 711942741828963742L;
    @Schema(description = "排序")
    private Long sort;
    @Schema(description = "系统内置")
    @JacksonInject("stable")
    private String stable;
    @Schema(description = "状态")
    @JacksonInject("status")
    private String status;
    @Schema(description = "备注描述")
    private String memo;
    @Schema(description = "角色编码")
    private String roleCode;
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "部门数据权限分类")
    @JacksonInject("deptDataPermissionType")
    private String deptDataPermissionType;
    @Schema(description = "自定义部门数据权限")
    private String deptDataPermissionCustom;
    @Schema(description = "默认首页")
    @JacksonInject("defaultPage")
    private String defaultPage;
}


