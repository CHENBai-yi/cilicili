package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * (SysRoleApi)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysRoleApi实体类")
public class SysRoleApi implements Serializable {
    private static final long serialVersionUID = 128314439597269182L;
    @Schema(description = "角色编码")
    private String roleCode;
    @Schema(description = "Api分组")
    private String apiGroup;
    @Schema(description = "请求方法")
    private String apiMethod;
    @Schema(description = "Api地址")
    private String apiPath;
}


