package site.cilicili.backend.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.user.domain.dto.AddUserRequest;
import site.cilicili.backend.user.domain.dto.GetUserListRequest;
import site.cilicili.backend.user.domain.dto.ResetPasswordAndDeleteUserRequest;
import site.cilicili.backend.user.domain.pojo.SysUserEntity;
import site.cilicili.backend.user.service.SysUserService;
import site.cilicili.common.util.R;

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
}
