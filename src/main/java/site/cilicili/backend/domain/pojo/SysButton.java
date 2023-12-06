package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * (SysButton)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:37
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysButton实体类")
public class SysButton implements Serializable {
    private static final long serialVersionUID = -18069214188883267L;
    @Schema(description = "菜单Name")
    private String menuName;
    @Schema(description = "按钮名称")
    private String buttonName;
    @Schema(description = "按钮编码")
    private String buttonCode;
}


