package site.cilicili.backend.menu.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.entity.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * (SysMenu)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-14 04:05:52
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysMenu实体类")
@TableName("sys_menu")
public class SysMenuEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -47068264463880790L;

    @Schema(description = "排序")
    private Long sort;

    @Schema(description = "系统内置")
    private String stable;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "备注描述")
    private String memo;

    @Schema(description = "父菜单Name")
    private String parentCode;

    @Schema(description = "菜单Name")
    private String name;

    @Schema(description = "菜单地址")
    private String path;

    @Schema(description = "前端组件")
    private String component;

    @Schema(description = "重定向地址")
    private String redirect;

    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "是否在列表隐藏")
    private String hidden;

    @Schema(description = "是否缓存")
    private String keepAlive;

    @Schema(description = "是否外链")
    private String isLink;
}
