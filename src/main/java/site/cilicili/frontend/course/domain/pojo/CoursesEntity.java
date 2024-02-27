package site.cilicili.frontend.course.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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
public class CoursesEntity implements Serializable {
    private static final long serialVersionUID = 158204106956964287L;
    @TableId
    @Schema(description = "课程ID")
    private Integer courseId;
    @Schema(description = "课程名称")
    private String name;
    @Schema(description = "教师姓名")
    private String teacher;
    @Schema(description = "课程种类")
    private String kind;
    @Schema(description = "学科")
    private String subject;
    @Schema(description = "价格")
    private Double price;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "海报URL")
    private String poster;
    @Schema(description = "标签")
    private String tag;
}


