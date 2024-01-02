package site.cilicili.backend.button.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.button.domain.pojo.SysButtonEntity;
import site.cilicili.backend.button.service.SysButtonService;
import site.cilicili.common.util.R;

/**
 * (SysButton) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:46:34
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sysButton")
@Tag(name = "(SysButton) 表控制层")
public class SysButtonController {
    /**
     * 服务对象
     */
    private final SysButtonService sysButtonService;

    /**
     * 全查询
     *
     * @param sysButton 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "sysButton 筛选条件")})
    @GetMapping
    public R queryAll(final SysButtonEntity sysButton) {
        return this.sysButtonService.queryAll(sysButton);
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
        return this.sysButtonService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysButton 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "sysButton 实体")})
    @PostMapping
    public R add(final @RequestBody SysButtonEntity sysButton) {
        return this.sysButtonService.insert(sysButton);
    }

    /**
     * 编辑数据
     *
     * @param sysButton 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "sysButtonEntity 实体")})
    @PutMapping
    public R edit(final @RequestBody SysButtonEntity sysButton) {
        return this.sysButtonService.update(sysButton);
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
        return this.sysButtonService.deleteById(id);
    }
}
