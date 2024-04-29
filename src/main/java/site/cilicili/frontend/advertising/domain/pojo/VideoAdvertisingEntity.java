package site.cilicili.frontend.advertising.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-29 14:46:15
 */

/**
 * (VideoAdvertising)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:46:15
 */
@Slf4j
@Getter
@Setter
@Schema(description = "VideoAdvertising 实体类")
@TableName("video_advertising")
public class VideoAdvertisingEntity implements Serializable {
    private static final long serialVersionUID = -75835453629995802L;
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
}


