package site.cilicili.backend.dict.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * (SysDict)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-16 12:44:32
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysDict 实体类")
@TableName("sys_dict")
public class SysDictEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -87633705785916626L;

    @Schema(description = "排序")
    private Long sort;

    @Schema(description = "系统内置")
    private String stable;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "备注描述")
    private String memo;

    @Schema(description = "父字典编码")
    private String parentCode;

    @Schema(description = "字典编码")
    private String dictCode;

    @Schema(description = "字典名称")
    private String dictLabel;

    @Schema(description = "字典扩展项1")
    private String dictExt1;

    @Schema(description = "字典扩展项2")
    private String dictExt2;

    @Schema(description = "字典扩展项3")
    private String dictExt3;

    @Schema(description = "字典扩展项4")
    private String dictExt4;

    @Schema(description = "字典扩展项5")
    private String dictExt5;
}
