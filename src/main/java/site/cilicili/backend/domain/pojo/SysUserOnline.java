package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * (SysUserOnline)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:47
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysUserOnline实体类")
public class SysUserOnline implements Serializable {
    private static final long serialVersionUID = -58175195565607443L;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "token")
    private String token;
}


