package site.cilicili.backend.menu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.menu.domain.pojo.SysMenuEntity;
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
@RequestMapping("/public/menu")
@Tag(name = "(SysMenu) 表控制层")
public class SysMenuController {
    /**
     * 服务对象
     */

    private final SysMenuService sysMenuService;

    /**
     * 全查询
     *
     * @param sysMenu 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysMenu 筛选条件")
    })
    @GetMapping
    public R queryAll(final SysMenuEntity sysMenu) {
        return this.sysMenuService.queryAll(sysMenu);
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
        return this.sysMenuService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "sysMenu 实体")
    })
    @PostMapping
    public R add(final @RequestBody SysMenuEntity sysMenu) {
        return this.sysMenuService.insert(sysMenu);
    }

    /**
     * 编辑数据
     *
     * @param sysMenu 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "sysMenuEntity 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SysMenuEntity sysMenu) {
        return this.sysMenuService.update(sysMenu);
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
        return this.sysMenuService.deleteById(id);
    }
}


