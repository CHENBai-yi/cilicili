package site.cilicili.frontend.subjects.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.common.util.R;
import site.cilicili.frontend.subjects.domain.dto.GetSubjectListRequest;
import site.cilicili.frontend.subjects.domain.pojo.SubjectsEntity;
import site.cilicili.frontend.subjects.service.SubjectsService;

/**
 * (Subjects) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:39
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("subject")
@Tag(name = "(Subject) 表控制层")
public class SubjectsController {
    /**
     * 服务对象
     */

    private final SubjectsService subjectsService;

    /**
     * 全查询
     *
     * @param subjects 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "subjects 筛选条件")
    })
    @GetMapping
    public R queryAll(final SubjectsEntity subjects) {
        return this.subjectsService.queryAll(subjects);
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
    public R queryById(final @PathVariable("id") Integer id) {
        return this.subjectsService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjects 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "subjects 实体")
    })
    @PostMapping
    public R add(final @RequestBody SubjectsEntity subjects) {
        return this.subjectsService.insert(subjects);
    }

    /**
     * 编辑数据
     *
     * @param subjects 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "subjectsEntity 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SubjectsEntity subjects) {
        return this.subjectsService.update(subjects);
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
    public R deleteById(final Integer id) {
        return this.subjectsService.deleteById(id);
    }

    // 自定义

    /**
     * 新增数据
     *
     * @param subjects 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "subjects 实体")
    })
    @PostMapping("add-subject")
    public R addSubject(final @RequestBody SubjectsEntity subjects) {
        return this.subjectsService.addSubject(subjects);
    }

    /**
     * 全查询
     *
     * @param subjects 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "subjects 筛选条件")
    })
    @PostMapping(path = {"get-subject-list", "query-subject-by-id"})
    public R getSubjectList(final @RequestBody GetSubjectListRequest subjects) {
        return this.subjectsService.getSubjectList(subjects);
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
    @PostMapping("delete-subject-by-id")
    public R deleteSubjectById(final @RequestBody SubjectsEntity id) {
        return this.subjectsService.deleteSubjectById(id);
    }

}



