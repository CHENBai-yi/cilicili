package site.cilicili.frontend.bars.domain.pojo;

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
 * @since 2024-02-27 00:00:40
 */

/**
 * (Bars)实体类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:00:40
 */
@Slf4j
@Getter
@Setter
@Schema(description = "Bars 实体类")
@TableName("bars")
public class BarsEntity implements Serializable {
    private static final long serialVersionUID = -90162275262060559L;

    @TableId
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
    @Schema(description = "视频长度信息")
    private String length;
    @Schema(description = "视频分钟数")
    private Long size;

}
