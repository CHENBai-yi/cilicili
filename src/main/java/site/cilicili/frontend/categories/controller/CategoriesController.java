package site.cilicili.frontend.categories.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.common.util.R;
import site.cilicili.frontend.categories.domain.dto.AddCategoryRequest;
import site.cilicili.frontend.categories.domain.dto.GetCategoryListRequest;
import site.cilicili.frontend.categories.domain.pojo.CategoriesEntity;
import site.cilicili.frontend.categories.service.CategoriesService;
import site.cilicili.frontend.subjects.domain.dto.EditCategoryRequest;

/**
 * (Categories) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:16
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("category")
@Tag(name = "(Categories) 表控制层")
public class CategoriesController {
    /**
     * 服务对象
     */
    private final CategoriesService categoriesService;

    /**
     * 全查询
     *
     * @param categories 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "categories 筛选条件")})
    @GetMapping
    public R queryAll(final CategoriesEntity categories) {
        return this.categoriesService.queryAll(categories);
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
        return this.categoriesService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param categories 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "categories 实体")})
    @PostMapping
    public R add(final @RequestBody CategoriesEntity categories) {
        return this.categoriesService.insert(categories);
    }

    /**
     * 编辑数据
     *
     * @param categories 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "categoriesEntity 实体")})
    @PutMapping
    public R edit(final @RequestBody CategoriesEntity categories) {
        return this.categoriesService.update(categories);
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
        return this.categoriesService.deleteById(id);
    }

    /**
     * 新增数据
     *
     * @param categories 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "categories 实体")})
    @PostMapping("get-category-list")
    public R getCategoryList(final @RequestBody GetCategoryListRequest categories) {
        return this.categoriesService.getCategoryList(categories);
    }

    /**
     * 编辑数据
     *
     * @param categories 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "categories 实体")})
    @PostMapping("edit-category")
    public R editCategory(final @RequestBody EditCategoryRequest categories) {
        return this.categoriesService.editCategory(categories);
    }

    /**
     * 删除数据
     *
     * @param categories 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "categories 实体")})
    @PostMapping("delete-category-by-id")
    public R deleteCategoryById(final @RequestBody CategoriesEntity categories) {
        return this.categoriesService.deleteCategoryById(categories);
    }

    /**
     * 新增数据
     *
     * @param categories 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "categories 实体")})
    @PostMapping("add-category")
    public R addCategory(final @RequestBody AddCategoryRequest categories) {
        return this.categoriesService.addCategory(categories);
    }
}
