package site.cilicili.backend.api.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-30 15:21:42
 */

/**
 * (SysApi)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:21:42
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysApi 实体类")
@TableName("sys_api")
public class SysApiEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -14245158898225425L;

    @Schema(description = "排序")
    private Long sort;

    @Schema(description = "系统内置")
    private String stable;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "备注描述")
    private String memo;

    @Schema(description = "Api分组")
    private String apiGroup;

    @Schema(description = "请求方法")
    private String apiMethod;

    @Schema(description = "Api地址")
    private String apiPath;
}
