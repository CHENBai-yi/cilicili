package site.cilicili.frontend.course.domain.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class CoursesEntity implements Serializable {
    private static final long serialVersionUID = 158204106956964287L;

    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime createdAt;

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

    @TableField(fill = FieldFill.INSERT)
    protected String createdBy;

    @JsonAlias("img")
    @Schema(description = "海报URL")
    private String poster;

    @Schema(description = "标签")
    private String tag;

    @Schema(description = "审核理由")
    private String reason;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime updatedAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected String updatedBy;

    @TableLogic(value = "1", delval = "0")
    @Schema(description = "逻辑删除")
    private Integer logicalDelete;

    @TableField(fill = FieldFill.UPDATE)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime deletedAt;

    @JsonIdentityReference(alwaysAsId = true)
    @TableId(type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Schema(description = "课程ID")
    @JsonProperty("id")
    private Integer courseId;

    @Schema(description = "第一小集url")
    private String firstBarUrl;

    @Schema(description = "标签")
    @JacksonInject("videoStatus")
    private String status;

    @Schema(description = "总时间")
    private String totalTime;
    @Schema(description = "总时间")
    private Long vis;
}
