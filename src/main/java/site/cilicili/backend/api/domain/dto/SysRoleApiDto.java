package site.cilicili.backend.api.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-30 15:22:11
 */

/**
 * (SysRoleApi)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:22:11
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysRoleApi数据传输类")
public class SysRoleApiDto implements Serializable {
    private static final long serialVersionUID = -48256538475860019L;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "Api分组")
    private String apiGroup;

    @Schema(description = "请求方法")
    private String apiMethod;

    @Schema(description = "Api地址")
    private String apiPath;
}
