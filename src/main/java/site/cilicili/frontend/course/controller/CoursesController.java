package site.cilicili.frontend.course.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.common.util.R;
import site.cilicili.frontend.course.domain.dto.AddCourseRequest;
import site.cilicili.frontend.course.domain.dto.QueryCourseInfoRequest;
import site.cilicili.frontend.course.domain.pojo.CoursesEntity;
import site.cilicili.frontend.course.service.CoursesService;

/**
 * (Courses) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:28
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("courses")
@Tag(name = "(Courses) 表控制层")
public class CoursesController {
    /**
     * 服务对象
     */

    private final CoursesService coursesService;

    /**
     * 全查询
     *
     * @param courses 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "courses 筛选条件")
    })
    @GetMapping
    public R queryAll(final CoursesEntity courses) {
        return this.coursesService.queryAll(courses);
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
        return this.coursesService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param courses 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "courses 实体")
    })
    @PostMapping
    public R add(final @RequestBody CoursesEntity courses) {
        return this.coursesService.insert(courses);
    }

    /**
     * 编辑数据
     *
     * @param courses 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "coursesEntity 实体")
    })
    @PutMapping
    public R edit(final @RequestBody CoursesEntity courses) {
        return this.coursesService.update(courses);
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
        return this.coursesService.deleteById(id);
    }

    // 自定义逻辑

    /**
     * 新增数据
     *
     * @param courses 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "courses 实体")
    })
    @PostMapping("add")
    public R addCourse(final @RequestBody AddCourseRequest courses) {
        return this.coursesService.addCourse(courses);
    }

    @PostMapping("get-course-info")
    public R getCourseInfo(final @RequestBody QueryCourseInfoRequest courses) {
        return coursesService.getCourseInfo(courses);
    }

    @PostMapping("get-courses-count")
    public R getCoursesCount(final @RequestBody CoursesEntity courses) {
        return coursesService.getCoursesCount(courses);
    }

    @PostMapping("delete-course-info-by-id")
    public R deleteCourseInfoById(final @RequestBody CoursesEntity courses) {
        return coursesService.deleteCourseInfoById(courses);
    }
}



