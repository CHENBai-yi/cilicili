package site.cilicili.frontend.catalogs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.common.util.R;
import site.cilicili.frontend.catalogs.domain.pojo.CatalogsEntity;
import site.cilicili.frontend.catalogs.service.CatalogsService;

/**
 * (Catalogs) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:01
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("catalogs")
@Tag(name = "(Catalogs) 表控制层")
public class CatalogsController {
    /**
     * 服务对象
     */
    private final CatalogsService catalogsService;

    /**
     * 全查询
     *
     * @param catalogs 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "catalogs 筛选条件")})
    @GetMapping
    public R queryAll(final CatalogsEntity catalogs) {
        return this.catalogsService.queryAll(catalogs);
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
    public R queryById(final @PathVariable("id") Integer id) {
        return this.catalogsService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param catalogs 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "catalogs 实体")})
    @PostMapping
    public R add(final @RequestBody CatalogsEntity catalogs) {
        return this.catalogsService.insert(catalogs);
    }

    /**
     * 编辑数据
     *
     * @param catalogs 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "catalogsEntity 实体")})
    @PutMapping
    public R edit(final @RequestBody CatalogsEntity catalogs) {
        return this.catalogsService.update(catalogs);
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
    public R deleteById(final Integer id) {
        return this.catalogsService.deleteById(id);
    }
}
