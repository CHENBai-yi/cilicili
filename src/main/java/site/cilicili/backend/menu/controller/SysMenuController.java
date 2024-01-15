package site.cilicili.backend.menu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.menu.domain.dto.AddMenuRequest;
import site.cilicili.backend.menu.domain.dto.FindOrDeleteRequest;
import site.cilicili.backend.menu.domain.dto.GetMenuListRequest;
import site.cilicili.backend.menu.domain.dto.SysMenuDto;
import site.cilicili.backend.menu.service.SysMenuService;
import site.cilicili.common.util.R;

import java.util.stream.Collectors;

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
        return sysMenuService.getMenuList(sysMenuListRequest);
    }

    /**
     * 根据id查询菜单
     *
     * @param findOrDeleteRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "根据id查询菜单",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("query-menu-by-id")
    public R queryMenuById(@RequestBody @Validated final FindOrDeleteRequest findOrDeleteRequest) {
        return sysMenuService.queryMenuById(findOrDeleteRequest);
    }

    /**
     * 根据id删除菜单
     *
     * @param findOrDeleteRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "根据id删除菜单",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("delete-menu-by-id")
    public R deleteMenuById(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody @Validated final FindOrDeleteRequest findOrDeleteRequest) {
        return sysMenuService.deleteMenuById(authUserDetails, findOrDeleteRequest);
    }

    /**
     * 添加菜单
     *
     * @param addMenuRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "添加菜单",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("add-menu")
    public R addMenu(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody @Validated final AddMenuRequest addMenuRequest, Errors errors) {
        if (!errors.getFieldErrors().isEmpty()) {
            return R.no(errors.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining()));
        }
        return sysMenuService.addMenu(authUserDetails, addMenuRequest);
    }

    /**
     * 编辑菜单
     *
     * @param addMenuRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "编辑菜单",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("edit-menu")
    public R editMenu(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody @Validated final SysMenuDto.EditMenuRequest addMenuRequest) {
        return sysMenuService.editMenu(authUserDetails, addMenuRequest);
    }
}
