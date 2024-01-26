package site.cilicili.backend.todo.domain.pojo;

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
 * @since 2024-01-26 20:16:39
 */

/**
 * (SysTodo)实体类
 *
 * @author ChenBaiYi
 * @since 2024-01-26 20:16:39
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysTodo 实体类")
@TableName("sys_todo")
public class SysTodoEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 306046108497600374L;

    @JacksonInject("sort")
    @Schema(description = "排序")
    private Long sort;

    @JacksonInject("stable")
    @Schema(description = "系统内置")
    private String stable;

    @Schema(description = "状态")
    @JacksonInject("status")
    private String status;

    @Schema(description = "备注描述")
    private String memo;

    @Schema(description = "内容")
    private String todoDetail;

    @Schema(description = "状态")
    @JacksonInject("todoStatus")
    private String todoStatus;
}
