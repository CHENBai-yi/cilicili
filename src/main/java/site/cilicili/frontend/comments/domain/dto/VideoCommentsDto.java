package site.cilicili.frontend.comments.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
 * @since 2024-04-16 22:09:14
 */

/**
 * (VideoComments)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-16 22:09:14
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "VideoComments数据传输类")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VideoCommentsDto implements Serializable {
    private static final long serialVersionUID = -59949174454521019L;
    List<Long> ids;
    private Integer id;
    private Integer parentid;
    private Integer uid;
    private String address;
    private String content;
    private Integer likes;
    private String contentimg;
    private Date createtime;
    private String username;
    private String avatar;
    private Integer level;
    private Integer attention;
    private Integer follower;
    private String homelink;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private Date deletedAt;
    /**
     * 页
     */
    @JsonIgnore
    private Integer pageNum;
    /**
     * 条
     */
    @JsonIgnore
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
