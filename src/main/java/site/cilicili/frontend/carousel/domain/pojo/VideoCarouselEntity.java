package site.cilicili.frontend.carousel.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.entity.BaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-29 14:45:45
 */

/**
 * (VideoCarousel)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:45:45
 */
@Slf4j
@Getter
@Setter
@Schema(description = "VideoCarousel 实体类")
@TableName("video_carousel")
public class VideoCarouselEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 712870790852201207L;
    private String imageUrl;
    private String backgroundColor;
    private Long itemId;
    private Long catId;
    private String type;
    private Integer sort;
    private Boolean isShow;
    private String memo;
    private String status;
    private String stable;
    private String link;
    private String page;
    private Date start;
    private Date end;
}


