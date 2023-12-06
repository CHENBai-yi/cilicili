package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * (SysRoleMenu)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:46
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysRoleMenu实体类")
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 718647191603446409L;
    private String sysRoleRoleCode;
    private String sysMenuName;
}


