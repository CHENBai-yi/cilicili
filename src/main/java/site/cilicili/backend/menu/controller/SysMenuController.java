package site.cilicili.backend.menu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.backend.menu.domain.dto.GetMenuListRequest;
import site.cilicili.backend.menu.service.SysMenuService;
import site.cilicili.common.util.R;

/**
 * (SysMenu) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-14 04:01:59
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("menu")
@Tag(name = "(SysMenu) 表控制层")
public class SysMenuController {
    /**
     * 服务对象
     */
    private final SysMenuService sysMenuService;

    /**
     * 获取菜单列表
     *
     * @param sysMenuListRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "获取菜单列表",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("get-menu-list")
    public R getRoleMenuList(@RequestBody final GetMenuListRequest sysMenuListRequest) {
        return sysMenuService.getRoleMenuList(sysMenuListRequest);
    }
}
