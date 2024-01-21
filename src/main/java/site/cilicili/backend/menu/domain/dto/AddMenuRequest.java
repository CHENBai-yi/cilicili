package site.cilicili.backend.menu.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import site.cilicili.backend.menu.domain.pojo.SysMenuEntity;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.menu.domain.dto
 * Date:2024/1/14 15:43
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Getter
@Setter
public class AddMenuRequest extends SysMenuEntity {
    @NotNull(message = "菜单编码不能为空")
    private String name;

    @NotNull(message = "菜单排序不能为空")
    @Positive(message = "排序不能为负数")
    private Long sort;

    @NotNull(message = "菜单名称不能为空")
    private String title;
}
