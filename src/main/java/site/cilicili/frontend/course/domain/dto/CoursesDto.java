package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-02-27 00:01:28
 */

/**
 * (Courses)实体类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:28
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "Courses数据传输类")
public class CoursesDto implements Serializable {
    private static final long serialVersionUID = -49791560637850816L;
    List<Long> ids;
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
    /**
     * 页
     */
    private Integer pageNum;
    /**
     * 条
     */
    private Integer pageSize;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }
}
