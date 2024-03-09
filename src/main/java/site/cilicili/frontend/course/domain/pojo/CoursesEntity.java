package site.cilicili.frontend.course.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * @since 2024-02-27 00:01:27
 */

/**
 * (Courses)实体类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:27
 */
@Slf4j
@Getter
@Setter
@Schema(description = "Courses 实体类")
@TableName("courses")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CoursesEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 158204106956964287L;

    @Schema(description = "课程ID")
    @JsonProperty("id")
    private Integer courseId;

    @Schema(description = "课程名称")
    private String name;

    @Schema(description = "教师姓名")
    @JsonAlias("author")
    private String teacher;

    @Schema(description = "课程种类")
    private String kind;

    @Schema(description = "学科")
    private String subject;

    @Schema(description = "价格")
    private Double price;

    @Schema(description = "描述")
    private String description;
    @JsonAlias("img")
    @Schema(description = "海报URL")
    private String poster;

    @Schema(description = "标签")
    private String tag;
    @Schema(description = "审核理由")
    private String reason;

    @Schema(description = "标签")
    @JacksonInject("status")
    private String status;

    @TableLogic(value = "1", delval = "0")
    @Schema(description = "逻辑删除")
    private Integer logicalDelete;
}
