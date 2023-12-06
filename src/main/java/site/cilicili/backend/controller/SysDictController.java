package site.cilicili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.domain.pojo.SysDict;
import site.cilicili.backend.service.SysDictService;
import site.cilicili.common.util.R;

/**
 * (SysDict)表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:40
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sysDict")
@Tag(name = "(SysDict)表控制层")
public class SysDictController {
    /**
     * 服务对象
     */

    private final SysDictService sysDictService;

    /**
     * 全查询
     *
     * @param sysDict 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysDict 筛选条件")
    })
    @GetMapping
    public R queryAll(final SysDict sysDict) {
        return this.sysDictService.queryAll(sysDict);
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
        return this.sysDictService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysDict 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "sysDict 实体")
    })
    @PostMapping
    public R add(final @RequestBody SysDict sysDict) {
        return this.sysDictService.insert(sysDict);
    }

    /**
     * 编辑数据
     *
     * @param sysDict 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "sysDict 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SysDict sysDict) {
        return this.sysDictService.update(sysDict);
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
        return this.sysDictService.deleteById(id);
    }
}



