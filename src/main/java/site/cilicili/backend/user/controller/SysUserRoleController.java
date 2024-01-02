package site.cilicili.backend.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.user.domain.dto.RemoveRoleUserRequest;
import site.cilicili.backend.user.domain.pojo.SysUserRoleEntity;
import site.cilicili.backend.user.service.SysUserRoleService;
import site.cilicili.common.util.R;

/**
 * (SysUserRole) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-31 14:58:48
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("role")
@Tag(name = "(SysUserRole) 表控制层")
public class SysUserRoleController {
    /**
     * 服务对象
     */
    private final SysUserRoleService sysUserRoleService;

    /**
     * 全查询
     *
     * @param sysUserRole 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "sysUserRole 筛选条件")})
    @GetMapping
    public R queryAll(final SysUserRoleEntity sysUserRole) {
        return this.sysUserRoleService.queryAll(sysUserRole);
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
    public R queryById(final @PathVariable("id") String id) {
        return this.sysUserRoleService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "sysUserRole 实体")})
    @PostMapping
    public R add(final @RequestBody SysUserRoleEntity sysUserRole) {
        return this.sysUserRoleService.insert(sysUserRole);
    }

    /**
     * 编辑数据
     *
     * @param sysUserRole 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "sysUserRoleEntity 实体")})
    @PutMapping
    public R edit(final @RequestBody SysUserRoleEntity sysUserRole) {
        return this.sysUserRoleService.update(sysUserRole);
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
    public R deleteById(final String id) {
        return this.sysUserRoleService.deleteById(id);
    }

    /**
     * 删除角色关联的用户
     *
     * @param removeRoleUserRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "删除角色关联的用户",
            parameters = {@Parameter(description = "RemoveRoleUserRequest 筛选条件")})
    @PostMapping("remove-role-user")
    public R removeRoleUser(@RequestBody @Validated final RemoveRoleUserRequest removeRoleUserRequest) {
        return sysUserRoleService.removeRoleUser(removeRoleUserRequest);
    }
}
