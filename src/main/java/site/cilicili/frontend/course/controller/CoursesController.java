package site.cilicili.frontend.course.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.common.util.R;
import site.cilicili.frontend.bars.domain.pojo.BarsEntity;
import site.cilicili.frontend.course.domain.dto.AddCourseRequest;
import site.cilicili.frontend.course.domain.dto.GetChildrenBarResponse;
import site.cilicili.frontend.course.domain.dto.GetSubjectCategoriesRequest;
import site.cilicili.frontend.course.domain.dto.QueryCourseInfoRequest;
import site.cilicili.frontend.course.domain.pojo.CoursesEntity;
import site.cilicili.frontend.course.service.CoursesService;

import java.util.List;

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

    private final RedisTemplate redisTemplate;

    /**
     * 全查询
     *
     * @param courses 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "courses 筛选条件")})
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
    @Operation(
            summary = "通过主键查询单条数据",
            parameters = {@Parameter(description = "id 主键")})
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
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "courses 实体")})
    @PostMapping
    public R add(final @RequestBody CoursesEntity courses) {
        return this.coursesService.insert(courses);
    }

    // 自定义逻辑

    /**
     * 编辑数据
     *
     * @param courses 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "coursesEntity 实体")})
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
    @Operation(
            summary = "删除数据",
            parameters = {@Parameter(description = "id 主键")})
    @DeleteMapping
    public R deleteById(final Integer id) {
        return this.coursesService.deleteById(id);
    }

    /**
     * 新增数据
     *
     * @param courses 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "courses 实体")})
    @PostMapping("add")
    public R addCourse(
            final @AuthenticationPrincipal AuthUserDetails authUserDetails,
            final @RequestBody AddCourseRequest courses) {
        return this.coursesService.addCourse(courses, authUserDetails);
    }

    @PostMapping("get-course-info")
    public R getCourseInfo(
            final @RequestBody QueryCourseInfoRequest courses,
            final @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return coursesService.getCourseInfo(courses, authUserDetails);
    }

    @PostMapping("get-courses-count")
    public R getCoursesCount(
            final @RequestBody CoursesEntity courses, final @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return coursesService.getCoursesCount(courses, authUserDetails);
    }

    @PostMapping("delete-course-info-by-id")
    public R deleteCourseInfoById(final @RequestBody CoursesEntity courses) {
        return coursesService.deleteCourseInfoById(courses);
    }

    @PostMapping("update-courses")
    public R reAudit(final @RequestBody CoursesEntity courses) {
        return coursesService.reAudit(courses);
    }

    @PostMapping("get-children-bar")
    public R getChildrenBar(final @RequestBody CoursesEntity courses) {
        return coursesService.getChildrenBar(courses);
    }

    @PostMapping("update")
    public R coursesUpdate(final @RequestBody List<GetChildrenBarResponse.Catalog> courses) {
        return coursesService.coursesUpdate(courses);
    }

    @PostMapping("update-video-url")
    public R updateVideoUrl(final @RequestBody BarsEntity bars) {
        return this.coursesService.updateVideoUrl(bars);
    }

    /**
     * 搜索
     *
     * @param queryCourseInfoRequest
     * @return
     */
    @PostMapping("get-course-list")
    public R getCourseList(
            final @RequestBody QueryCourseInfoRequest queryCourseInfoRequest,
            final @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return this.coursesService.getCourseList(queryCourseInfoRequest, authUserDetails);
    }

    @PostMapping("get-course-info-by-id")
    public R getCourseInfoById(final @RequestBody CoursesEntity courses) {
        return this.coursesService.getCourseInfoById(courses);
    }

    @PostMapping("get-course-video-info-by-id")
    public R getCourseVideoInfoById(
            final @RequestBody CoursesEntity courses, final @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return this.coursesService.getCourseVideoInfoById(courses, authUserDetails);
    }

    @PostMapping("get-subject-categories")
    public R getSubjectCategories(
            @RequestBody GetSubjectCategoriesRequest getSubjectCategoriesRequest,
            final @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return this.coursesService.getSubjectCategories(getSubjectCategoriesRequest, authUserDetails);
    }

    /**
     * 删除redis
     *
     * @param key
     * @return
     */
    @GetMapping("/w/remove")
    public R removeRedis(String key) {
        return R.yes(redisTemplate.delete(key) ? "Success" : "Fail");
    }

    /**
     * 热搜列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/r/list/hot/search")
    public R listHotSearch() {
        return coursesService.listHotSearch();
    }

    /**
     * 最近搜索列表
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/r/list/recent/search")
    public R recentHotSearch(final @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return coursesService.listRecentSearch(authUserDetails);
    }

    /**
     * 最近搜索列表
     *
     * @return
     */
    @PreAuthorize("isAnonymous()")
    @ResponseBody
    @GetMapping("search")
    public R recentAndHotSearch(final @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return coursesService.recentAndHotSearch(authUserDetails);
    }
}
