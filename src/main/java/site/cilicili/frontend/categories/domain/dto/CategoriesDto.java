package site.cilicili.frontend.categories.domain.dto;

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
 * @since 2024-02-27 00:01:16
 */

/**
 * (Categories)实体类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:16
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "Categories数据传输类")
public class CategoriesDto implements Serializable {
    private static final long serialVersionUID = -54886948376450239L;
    List<Long> ids;
    @Schema(description = "ID")
    private Integer id;
    @Schema(description = "学科代码")
    private Integer subjectId;
    @Schema(description = "分类名称")
    private String categoryName;
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
