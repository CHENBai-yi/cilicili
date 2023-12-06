package site.cilicili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.domain.pojo.SysRole;
import site.cilicili.backend.service.SysRoleService;
import site.cilicili.common.util.R;

/**
 * (SysRole)表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sysRole")
@Tag(name = "(SysRole)表控制层")
public class SysRoleController {
    /**
     * 服务对象
     */

    private final SysRoleService sysRoleService;

    /**
     * 全查询
     *
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysRole 筛选条件")
    })
    @GetMapping
    public R queryAll(final SysRole sysRole) {
        return this.sysRoleService.queryAll(sysRole);
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
        return this.sysRoleService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysRole 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "sysRole 实体")
    })
    @PostMapping
    public R add(final @RequestBody SysRole sysRole) {
        return this.sysRoleService.insert(sysRole);
    }

    /**
     * 编辑数据
     *
     * @param sysRole 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "sysRole 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SysRole sysRole) {
        return this.sysRoleService.update(sysRole);
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
        return this.sysRoleService.deleteById(id);
    }
}



