package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysRole)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysRole实体类")
public class SysRole implements Serializable {
    private static final long serialVersionUID = -70247240916348994L;
    @Schema(description = "id")
    private Long id;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private Date deletedAt;
    @Schema(description = "排序")
    private Long sort;
    @Schema(description = "系统内置")
    private String stable;
    @Schema(description = "状态")
    private String status;
    @Schema(description = "备注描述")
    private String memo;
    @Schema(description = "角色编码")
    private String roleCode;
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "部门数据权限分类")
    private String deptDataPermissionType;
    @Schema(description = "自定义部门数据权限")
    private String deptDataPermissionCustom;
    @Schema(description = "默认首页")
    private String defaultPage;
}


