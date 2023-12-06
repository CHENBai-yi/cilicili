package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysMenu)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:42
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysMenu实体类")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = -62559929664524723L;
    @Schema(description = "id")
    private Long id;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private Date deletedAt;
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


