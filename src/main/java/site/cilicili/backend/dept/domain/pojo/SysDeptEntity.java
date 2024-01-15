package site.cilicili.backend.dept.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JacksonInject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-30 16:36:32
 */

/**
 * (SysDept)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-30 16:36:32
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysDept 实体类")
@TableName("sys_dept")
public class SysDeptEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -83111392698342481L;

    @Schema(description = "排序")
    private Long sort;

    @JacksonInject("stable")
    @Schema(description = "系统内置")
    private String stable;

    @JacksonInject("status")
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
