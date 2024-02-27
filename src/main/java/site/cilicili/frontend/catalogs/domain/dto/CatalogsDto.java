package site.cilicili.frontend.catalogs.domain.dto;

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
 * @since 2024-02-27 00:01:01
 */

/**
 * (Catalogs)实体类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:01
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "Catalogs数据传输类")
public class CatalogsDto implements Serializable {
    private static final long serialVersionUID = -69556515283795026L;
    List<Long> ids;
    @Schema(description = "目录ID")
    private Integer catalogId;
    @Schema(description = "关联的课程ID，外键参考courses表")
    private Integer courseId;
    @Schema(description = "章节ID")
    private Integer id;
    @Schema(description = "目录标题")
    private String title;
    @Schema(description = "目录描述")
    private String description;
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
