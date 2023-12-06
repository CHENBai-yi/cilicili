package site.cilicili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.domain.pojo.SysUser;
import site.cilicili.backend.service.SysUserService;
import site.cilicili.common.util.R;

/**
 * (SysUser)表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:47
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sysUser")
@Tag(name = "(SysUser)表控制层")
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
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysUser 筛选条件")
    })
    @GetMapping
    public R queryAll(final SysUser sysUser) {
        return this.sysUserService.queryAll(sysUser);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据", parameters = {
            @Parameter(description = "id 主键")
    })
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

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "sysUser 实体")
    })
    @PostMapping
    public R add(final @RequestBody SysUser sysUser) {
        return this.sysUserService.insert(sysUser);
    }

    /**
     * 编辑数据
     *
     * @param sysUser 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "sysUser 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SysUser sysUser) {
        return this.sysUserService.update(sysUser);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @Operation(summary = "删除数据", parameters = {
            @Parameter(description = "id 主键")
    })
    @DeleteMapping
    public R deleteById(final Long id) {
        return this.sysUserService.deleteById(id);
    }
}



