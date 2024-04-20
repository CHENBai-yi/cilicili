package site.cilicili.frontend.comments.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-19 00:00:06
 */

/**
 * (VideoCommentsUserInfo)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-19 00:00:06
 */
@Slf4j
@Getter
@Setter
@Schema(description = "VideoCommentsUserInfo 实体类")
@TableName("video_comments_user_info")
public class VideoCommentsUserInfoEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 470123989893046862L;
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
    @JsonIgnore
    private String likeIds;
    @TableField(exist = false)
    @JsonProperty("likeIds")
    private List<Integer> likeIdsArr;

    public List<Integer> getLikeIdsArr() {
        return Optional.ofNullable(likeIds).map(likeIds -> Arrays.stream(likeIds.split(",")).map(Integer::valueOf).toList()).orElse(Collections.emptyList());
    }

}


