package site.cilicili.frontend.comments.domain.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2024-04-16 22:09:12
 */

/**
 * (VideoComments)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-16 22:09:12
 */
@Slf4j
@Getter
@Setter
@Schema(description = "VideoComments 实体类")
@TableName("video_comments")
public class VideoCommentsEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 129133568072969282L;

    private Integer parentId;
    private Integer courseId;
    private Integer uid;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String address;

    private String content;
    private Integer likes;
    private String contentImg;
    private Date createTime;
    private String username;
    private String avatar;
    private Integer level;
    private String homeLink;
}
