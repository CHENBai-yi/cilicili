package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * (SysRoleButton)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysRoleButton实体类")
public class SysRoleButton implements Serializable {
    private static final long serialVersionUID = -45919930661973232L;
    private String sysRoleRoleCode;
    private String sysButtonButtonCode;
}


