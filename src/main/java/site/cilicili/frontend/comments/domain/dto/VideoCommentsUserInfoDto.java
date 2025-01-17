package site.cilicili.frontend.comments.domain.dto;

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
 * @since 2024-04-19 00:00:07
 */

/**
 * (VideoCommentsUserInfo)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-19 00:00:07
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "VideoCommentsUserInfo数据传输类")
public class VideoCommentsUserInfoDto implements Serializable {
    private static final long serialVersionUID = 861913508205913213L;
    List<Long> ids;
    private Long id;
    private Long uid;
    private String address;
    private String content;
    private Long like;
    private Long attention;
    private Long follower;
    private String username;
    private String avatar;
    private Integer level;
    private String homelink;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private Date deletedAt;
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
