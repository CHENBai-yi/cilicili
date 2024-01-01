package site.cilicili.backend.button.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
/**
 * PROJECT: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-29 21:46:33
 */

/**
 * (SysButton)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:46:33
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysButton 实体类")
@TableName("sys_button")
public class SysButtonEntity implements Serializable {
    private static final long serialVersionUID = 685912279623161346L;
    @Schema(description = "菜单Name")
    private String menuName;
    @Schema(description = "按钮名称")
    private String buttonName;
    @Schema(description = "按钮编码")
    private String buttonCode;
}


