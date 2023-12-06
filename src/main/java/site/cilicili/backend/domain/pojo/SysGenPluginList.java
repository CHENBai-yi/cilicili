package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysGenPluginList)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:40
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysGenPluginList实体类")
public class SysGenPluginList implements Serializable {
    private static final long serialVersionUID = -57307197477598086L;
    @Schema(description = "id")
    private Long id;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private Date deletedAt;
    @Schema(description = "排序")
    private Long sort;
    @Schema(description = "系统内置")
    private String stable;
    @Schema(description = "状态")
    private String status;
    @Schema(description = "备注描述")
    private String memo;
    @Schema(description = "插件排序")
    private Long pluginSort;
    @Schema(description = "插件编码")
    private String pluginCode;
    @Schema(description = "插件名称")
    private String pluginName;
    @Schema(description = "插件位置")
    private String pluginFile;
}


