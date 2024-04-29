package site.cilicili.frontend.advertising.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-29 14:46:16
 */

/**
 * (VideoAdvertising)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:46:16
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "VideoAdvertising数据传输类")
public class VideoAdvertisingDto implements Serializable {
    private static final long serialVersionUID = 283647449419855606L;
    List<Long> ids;
    private Long id;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private Date deletedAt;
    private Long sort;
    private String stable;
    private String status;
    private String memo;
    private String adverType;
    private String adverUrl;
    private String adverContent;
    private String adverLink;
    private Date start;
    private Date end;
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
