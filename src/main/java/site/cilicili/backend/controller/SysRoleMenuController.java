package site.cilicili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.domain.pojo.SysRoleMenu;
import site.cilicili.backend.service.SysRoleMenuService;
import site.cilicili.common.util.R;

/**
 * (SysRoleMenu)表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:46
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sysRoleMenu")
@Tag(name = "(SysRoleMenu)表控制层")
public class SysRoleMenuController {
    /**
     * 服务对象
     */

    private final SysRoleMenuService sysRoleMenuService;

    /**
     * 全查询
     *
     * @param sysRoleMenu 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysRoleMenu 筛选条件")
    })
    @GetMapping
    public R queryAll(final SysRoleMenu sysRoleMenu) {
        return this.sysRoleMenuService.queryAll(sysRoleMenu);
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
    public R queryById(final @PathVariable("id") String id) {
        return this.sysRoleMenuService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysRoleMenu 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "sysRoleMenu 实体")
    })
    @PostMapping
    public R add(final @RequestBody SysRoleMenu sysRoleMenu) {
        return this.sysRoleMenuService.insert(sysRoleMenu);
    }

    /**
     * 编辑数据
     *
     * @param sysRoleMenu 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "sysRoleMenu 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SysRoleMenu sysRoleMenu) {
        return this.sysRoleMenuService.update(sysRoleMenu);
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
    public R deleteById(final String id) {
        return this.sysRoleMenuService.deleteById(id);
    }
}



