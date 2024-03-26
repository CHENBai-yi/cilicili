package site.cilicili.frontend.catalogs.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-02-27 00:01:00
 */

/**
 * (Catalogs)实体类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:00
 */
@Slf4j
@Getter
@Setter
@Schema(description = "Catalogs 实体类")
@TableName("catalogs")
public class CatalogsEntity implements Serializable {
    private static final long serialVersionUID = 963174757291460784L;

    @TableId
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

    @Schema(description = "总秒数")
    private Long total;

    @Schema(description = "总时间")
    private String totalTime;
}
