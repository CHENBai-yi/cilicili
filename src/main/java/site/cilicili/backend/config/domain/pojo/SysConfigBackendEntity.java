package site.cilicili.backend.config.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * (SysConfigBackend)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:42
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysConfigBackend 实体类")
@TableName("sys_config_backend")
public class SysConfigBackendEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -55210611552054688L;

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
