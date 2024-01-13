package site.cilicili.backend.dept.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.dept.domain.dto.*;
import site.cilicili.backend.dept.domain.pojo.SysDeptEntity;
import site.cilicili.backend.dept.service.SysDeptService;
import site.cilicili.backend.user.domain.dto.GetUserListRequest;
import site.cilicili.backend.user.service.SysUserService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysDept) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-30 16:36:33
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("dept")
@Tag(name = "(SysDept) 表控制层")
public class SysDeptController {
    /**
     * 服务对象
     */
    private final SysDeptService sysDeptService;
    private final SysUserService sysUserService;

    /**
     * 全查询
     *
     * @param sysDept 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "sysDept 筛选条件")})
    @GetMapping
    public R queryAll(final SysDeptEntity sysDept) {
        return this.sysDeptService.queryAll(sysDept);
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
        return this.sysDeptService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysDept 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "sysDept 实体")})
    @PostMapping
    public R add(final @RequestBody SysDeptEntity sysDept) {
        return this.sysDeptService.insert(sysDept);
    }

    /**
     * 编辑数据
     *
     * @param sysDept 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "sysDeptEntity 实体")})
    @PutMapping
    public R edit(final @RequestBody SysDeptEntity sysDept) {
        return this.sysDeptService.update(sysDept);
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
        return this.sysDeptService.deleteById(id);
    }

    /**
     * 获取部门列表
     *
     * @param deptListRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "查询用户角色",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("get-dept-list")
    public R getDeptList(@RequestBody @Validated final GetDeptListRequest deptListRequest) {
        return sysDeptService.getDeptList(deptListRequest);
    }

    /**
     * 添加部门或者修改部门
     *
     * @param addDeptRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "添加部门或者修改部门",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping(path = {"add-dept", "edit-dept"})
    public R addDept(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody final AddDeptRequest addDeptRequest) {
        return sysDeptService.addDept(addDeptRequest);
    }

    /**
     * 根据Id删除部门
     *
     * @param queryAndDeleteDeptByIdRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "根据Id删除部门",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("delete-dept-by-id")
    public R deleteDeptById(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody @Validated final QueryAndDeleteDeptByIdRequest queryAndDeleteDeptByIdRequest) {
        return sysDeptService.deleteDeptById(authUserDetails, queryAndDeleteDeptByIdRequest);
    }

    /**
     * 根据Id查询部门
     *
     * @param queryAndDeleteDeptByIdRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "根据Id查询部门",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("query-dept-by-id")
    public R queryDeptById(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody @Validated final QueryAndDeleteDeptByIdRequest queryAndDeleteDeptByIdRequest) {
        return sysDeptService.queryDeptById(authUserDetails, queryAndDeleteDeptByIdRequest);
    }

    /**
     * 获取部门用户
     *
     * @param getUserListRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "获取部门用户",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("query-user-by-dept")
    public R queryUserByDept(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody final GetUserListRequest getUserListRequest) {
        return Optional.ofNullable(authUserDetails).map(r -> sysUserService.getUserList(getUserListRequest)).orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    /**
     * 添加部门用户
     *
     * @param addDeptUserRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "添加部门用户",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("add-dept-user")
    public R addDeptUser(@RequestBody @Validated final AddDeptUserRequest addDeptUserRequest) {
        return sysDeptService.addDeptUser(addDeptUserRequest);
    }

    /**
     * 移除部门用户
     *
     * @param removeDeptUserRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "移除部门用户",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("remove-dept-user")
    public R removeDeptUser(@RequestBody @Validated final RemoveDeptUserRequest removeDeptUserRequest) {
        return sysDeptService.removeDeptUser(removeDeptUserRequest);
    }

}
