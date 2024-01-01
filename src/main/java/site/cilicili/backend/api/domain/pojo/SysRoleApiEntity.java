package site.cilicili.backend.api.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-30 15:22:10
 */

/**
 * (SysRoleApi)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:22:10
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysRoleApi 实体类")
@TableName("sys_role_api")
public class SysRoleApiEntity implements Serializable {
    private static final long serialVersionUID = -86523138557907913L;
    @Schema(description = "角色编码")
    private String roleCode;
    @Schema(description = "Api分组")
    private String apiGroup;
    @Schema(description = "请求方法")
    private String apiMethod;
    @Schema(description = "Api地址")
    private String apiPath;
}


