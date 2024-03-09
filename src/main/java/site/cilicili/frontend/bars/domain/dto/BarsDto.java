package site.cilicili.frontend.bars.domain.dto;

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
 * @since 2024-02-27 00:00:41
 */

/**
 * (Bars)实体类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:00:41
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "Bars数据传输类")
public class BarsDto implements Serializable {
    private static final long serialVersionUID = -20488656213148048L;
    List<Long> ids;

    @Schema(description = "Bar ID")
    private Integer barId;

    @Schema(description = "关联的目录ID，外键参考catalogs表")
    private Integer catalogId;

    @Schema(description = "父级Bar ID")
    private Integer parentBarId;

    @Schema(description = "章节ID")
    private String id;

    @Schema(description = "Bar标题")
    private String title;

    @Schema(description = "Bar描述")
    private String description;

    @Schema(description = "视频URL")
    private String url;
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
