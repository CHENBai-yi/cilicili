package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * (SysUserRole)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:48
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysUserRole实体类")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -36807151135587207L;
    @Schema(description = "'角色编码'")
    private String sysRoleRoleCode;
    @Schema(description = "'用户名'")
    private String sysUserUsername;
}


