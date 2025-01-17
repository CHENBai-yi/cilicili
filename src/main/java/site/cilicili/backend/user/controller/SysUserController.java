package site.cilicili.backend.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.menu.service.SysMenuService;
import site.cilicili.backend.role.domain.pojo.SysRoleEntity;
import site.cilicili.backend.user.domain.dto.*;
import site.cilicili.backend.user.domain.pojo.SysUserEntity;
import site.cilicili.backend.user.service.SysUserService;
import site.cilicili.common.util.R;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (SysUser) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-14 21:53:23
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
@Tag(name = "(SysUser) 表控制层")
public class SysUserController {
    /**
     * 服务对象
     */
    private final SysUserService sysUserService;
    /**
     * 获取用户菜单
     *
     * @param resetPasswordAndDeleteUserRequest 请求参数
     * @return 用户列表
     */
    private final SysMenuService sysMenuService;

    /**
     * 全查询
     *
     * @param sysUser 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "sysUser 筛选条件")})
    @GetMapping
    public R queryAll(final SysUserEntity sysUser) {
        return this.sysUserService.queryAll(sysUser);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(
            summary = "通过主键查询单条数据",
            parameters = {@Parameter(description = "id 主键")})
    @GetMapping("{id}")
    public R queryById(final @PathVariable("id") Long id) {
        return this.sysUserService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "sysUser 实体")})
    @PostMapping
    public R add(final @RequestBody SysUserEntity sysUser) {
        return this.sysUserService.insert(sysUser);
    }

    /**
     * 编辑数据
     *
     * @param sysUser 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "sysUserEntity 实体")})
    @PutMapping
    public R edit(final @RequestBody SysUserEntity sysUser) {
        return this.sysUserService.update(sysUser);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @Operation(
            summary = "删除数据",
            parameters = {@Parameter(description = "id 主键")})
    @DeleteMapping
    public R deleteById(final Long id) {
        return this.sysUserService.deleteById(id);
    }

    /**
     * 获取用户列表
     *
     * @param getUserListRequest 请求参数
     * @return 用户列表
     */
    @Operation(
            summary = "获取用户列表",
            parameters = {@Parameter(description = "sysUser 实体")})
    @PostMapping("get-user-list")
    public R getUserList(@RequestBody final GetUserListRequest getUserListRequest) {
        return sysUserService.getUserList(getUserListRequest);
    }

    /**
     * 添加用户
     *
     * @param addUserRequest 请求参数
     * @return 用户列表
     */
    @Operation(
            summary = "添加用户",
            parameters = {@Parameter(description = "sysUser 实体")})
    @PostMapping("add-user")
    public R addUser(@RequestBody final AddUserRequest.Records addUserRequest) {
        return sysUserService.addUser(addUserRequest);
    }

    /**
     * 根据ID删除用户
     *
     * @param resetPasswordAndDeleteUserRequest 请求参数
     * @return 用户列表
     */
    @Operation(
            summary = "删除用户",
            parameters = {@Parameter(description = "sysUser 实体")})
    @PostMapping("delete-user-by-id")
    public R deleteUserById(
            @RequestBody @Validated final ResetPasswordAndDeleteUserRequest resetPasswordAndDeleteUserRequest) {
        return sysUserService.deleteUserById(resetPasswordAndDeleteUserRequest);
    }

    /**
     * 根据ID获取用户
     *
     * @param resetPasswordAndDeleteUserRequest 请求参数
     * @return 用户列表
     */
    @Operation(
            summary = "根据ID获取用户",
            parameters = {@Parameter(description = "sysUser 实体")})
    @PostMapping("query-user-by-id")
    public R queryUserById(
            @RequestBody @NotNull final ResetPasswordAndDeleteUserRequest resetPasswordAndDeleteUserRequest) {
        return sysUserService.queryUserById(resetPasswordAndDeleteUserRequest);
    }

    /**
     * 编辑用户信息
     *
     * @param editUserRequest 请求参数
     * @return 用户列表
     */
    @Operation(
            summary = "编辑用户信息",
            parameters = {@Parameter(description = "sysUser 实体")})
    @PostMapping("edit-user")
    public R editUser(@RequestBody final AddUserRequest.Records editUserRequest) {
        return sysUserService.editUser(editUserRequest);
    }

    /**
     * 重置用户密码
     *
     * @param resetPasswordAndDeleteUserRequest 请求参数
     * @return 用户列表
     */
    @Operation(
            summary = "重置用户密码",
            parameters = {@Parameter(description = "sysUser 实体")})
    @PostMapping("reset-password")
    public R resetPassword(
            @RequestBody @Validated final ResetPasswordAndDeleteUserRequest resetPasswordAndDeleteUserRequest) {
        return sysUserService.resetPassword(resetPasswordAndDeleteUserRequest);
    }

    @Operation(
            summary = "获取用户菜单",
            parameters = {@Parameter(description = "sysUser 实体")})
    @PostMapping("get-user-menu")
    public R getUserMenu(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return sysMenuService.getUserMenu(authUserDetails);
    }

    /**
     * 修改用户名
     *
     * @param changeNicknameRequest 请求参数
     * @return R
     */
    @Operation(
            summary = "修改用户名",
            parameters = {@Parameter(description = "sysUser 实体")})
    @PostMapping("change-nickname")
    public R changeNickname(
            @AuthenticationPrincipal AuthUserDetails authUserDetails,
            @RequestBody @Validated ChangeNicknameRequest changeNicknameRequest) {
        return sysUserService.changeNickname(authUserDetails, changeNicknameRequest);
    }

    /**
     * 修改用户密码
     *
     * @param changePasswordRequest 请求参数
     * @return R
     */
    @Operation(
            summary = "修改用户密码",
            parameters = {@Parameter(description = "sysUser 实体")})
    @PostMapping("change-password")
    public R changePassword(
            @AuthenticationPrincipal AuthUserDetails authUserDetails,
            @RequestBody @Validated ChangePasswordRequest changePasswordRequest) {
        return sysUserService.changePassword(authUserDetails, changePasswordRequest);
    }

    /**
     * 修改前端用户信息
     *
     * @param sysUserEntity 请求参数
     * @return R
     */
    @Operation(
            summary = "修改用户信息",
            parameters = {@Parameter(description = "sysUser 实体")})
    @PostMapping("change-info")
    public R changeInfo(
            @AuthenticationPrincipal AuthUserDetails authUserDetails,
            @RequestBody @Validated SysUserEntity sysUserEntity,
            Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(sysUserService.changeInfo(authUserDetails, sysUserEntity));
    }

    /**
     * 查询所有角色关联的教师用户
     *
     * @param
     * @return 单条数据
     */
    @Operation(
            summary = "查询所有角色关联的教师用户",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("get-user-list-by_role")
    public R getTeacherList(final @RequestBody SysRoleEntity sysRole) {
        return sysUserService.getTeacherList(sysRole);
    }
}
