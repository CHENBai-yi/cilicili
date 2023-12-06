package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysConfigBackend)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:38
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysConfigBackend实体类")
public class SysConfigBackend implements Serializable {
    private static final long serialVersionUID = 706100474866390275L;
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
    @Schema(description = "配置项")
    private String configItem;
    @Schema(description = "默认值")
    private String itemDefault;
    @Schema(description = "自定义值")
    private String itemCustom;
}


