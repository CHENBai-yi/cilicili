package site.cilicili.backend.role.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.backend.role.domain.dto.*;
import site.cilicili.backend.role.domain.pojo.SysRoleEntity;
import site.cilicili.backend.role.service.SysRoleService;
import site.cilicili.common.util.R;

/**
 * (SysRole) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-14 03:55:22
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("role")
@Tag(name = "(SysRole) 表控制层")
public class SysRoleController {
    /**
     * 服务对象
     */
    private final SysRoleService sysRoleService;

    /**
     * 新增数据
     *
     * @param sysRole 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据对象Role",
            parameters = {@Parameter(description = "sysRole 实体")})
    @PostMapping("add-role")
    public R addRole(final @RequestBody SysRoleEntity sysRole) {
        return this.sysRoleService.insert(sysRole);
    }

    /**
     * 根据体条件查询
     *
     * @param queryParam 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "sysRole 筛选条件")})
    @PostMapping("get-role-list")
    public R queryRoleList(@RequestBody(required = false) final RoleListQueryParam queryParam) {
        return this.sysRoleService.queryRoleListByParam(queryParam);
    }

    /**
     * 根据体ID查询
     *
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "根据体ID查询",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("query-role-by-id")
    public R queryRoleById(@RequestBody final RoleListQueryParam sysRole) {
        return queryRoleList(sysRole);
    }

    /**
     * 根据体ID修改
     *
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "根据体ID修改",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("edit-role")
    public R editRole(@RequestBody @Validated final EditRequest sysRole) {
        return sysRoleService.editRole(sysRole);
    }

    /**
     * 根据体ID删除
     *
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "根据体ID删除",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("delete-role-by-id")
    public R deleteRoleById(@RequestBody @Validated final EditRequest sysRole) {
        return sysRoleService.deleteRoleById(sysRole);
    }

    /**
     * 修改权限
     *
     * @param editRoleDeptDataPermissionRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "根据体ID删除",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("edit-role-dept-data-permission")
    public R editRoleDeptDataPermission(
            @RequestBody @Validated final EditRoleDeptDataPermissionRequest editRoleDeptDataPermissionRequest) {
        return sysRoleService.editRoleDeptDataPermission(editRoleDeptDataPermissionRequest);
    }

    /**
     * 查询用户角色
     *
     * @param queryUserByRoleRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "查询用户角色",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("query-user-by-role")
    public R queryUserByRole(@RequestBody @Validated final QueryUserByRoleRequest queryUserByRoleRequest) {
        return sysRoleService.queryUserByRole(queryUserByRoleRequest);
    }

    /**
     * 修改按钮菜单权限
     *
     * @param editRoleMenuRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "查询用户角色",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("edit-role-menu")
    public R editRoleMenu(@RequestBody @Validated final EditRoleMenuRequest editRoleMenuRequest) {
        return sysRoleService.editRoleMenu(editRoleMenuRequest);
    }

    /**
     * 修改角色api权限
     *
     * @param editRoleApiRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "查询用户角色",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("edit-role-api")
    public R editRoleApi(@RequestBody @Validated final EditRoleApiRequest editRoleApiRequest) {
        return sysRoleService.editRoleApi(editRoleApiRequest);
    }

    /**
     * 角色添加用户列表
     *
     * @param addRoleUserRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "角色添加用户列表",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("add-role-user")
    public R addRoleUser(@RequestBody @Validated final AddRoleUserRequest addRoleUserRequest) {
        return sysRoleService.addRoleUser(addRoleUserRequest);
    }
}
