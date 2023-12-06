package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * (SysDeptUser)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:39
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysDeptUser实体类")
public class SysDeptUser implements Serializable {
    private static final long serialVersionUID = 256268554972600717L;
    @Schema(description = "'部门编码'")
    private String sysDeptDeptCode;
    @Schema(description = "'用户名'")
    private String sysUserUsername;
}


