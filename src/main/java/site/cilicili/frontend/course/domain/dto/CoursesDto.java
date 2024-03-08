package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
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
    private int id;
    private String name;
    private String img;
    private String desc;
    private String author;
    private int carbs;
    private int protein;
    private String kind;
    private double price;
    private String date;
    private String status;
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

}
