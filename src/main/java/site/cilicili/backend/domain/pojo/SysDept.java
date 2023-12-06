package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysDept)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:39
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysDept实体类")
public class SysDept implements Serializable {
    private static final long serialVersionUID = -85885483711910886L;
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
    @Schema(description = "父部门DeptCode")
    private String parentCode;
    @Schema(description = "部门编码")
    private String deptCode;
    @Schema(description = "部门名称")
    private String deptName;
    @Schema(description = "部门负责人username")
    private String leader;
}


