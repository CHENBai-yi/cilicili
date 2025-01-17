package site.cilicili.frontend.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.common.util.R;
import site.cilicili.frontend.bars.domain.pojo.BarsEntity;
import site.cilicili.frontend.chart.domain.CiliDataBoardResponse;
import site.cilicili.frontend.course.domain.dto.AddCourseRequest;
import site.cilicili.frontend.course.domain.dto.GetChildrenBarResponse;
import site.cilicili.frontend.course.domain.dto.GetSubjectCategoriesRequest;
import site.cilicili.frontend.course.domain.dto.QueryCourseInfoRequest;
import site.cilicili.frontend.course.domain.pojo.CoursesEntity;

import java.util.List;

/**
 * (Courses)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:27
 */
public interface CoursesService extends IService<CoursesEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    R queryById(final Integer courseId);

    /**
     * 全查询
     *
     * @param courses 筛选条件
     * @return 查询结果
     */
    R queryAll(final CoursesEntity courses);

    /**
     * 新增数据
     *
     * @param courses 实例对象
     * @return 实例对象
     */
    R insert(final CoursesEntity courses);

    /**
     * 修改数据
     *
     * @param courses 实例对象
     * @return 实例对象
     */
    R update(final CoursesEntity courses);

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 是否成功
     */
    R deleteById(final Integer courseId);

    R addCourse(AddCourseRequest courses, final AuthUserDetails authUserDetails);

    R getCourseInfo(QueryCourseInfoRequest courses, final AuthUserDetails authUserDetails);

    R getCoursesCount(CoursesEntity courses, final AuthUserDetails authUserDetails);

    R deleteCourseInfoById(CoursesEntity courses);

    R reAudit(CoursesEntity courses);

    R getChildrenBar(CoursesEntity courses);

    R coursesUpdate(List<GetChildrenBarResponse.Catalog> courses);

    R updateVideoUrl(BarsEntity bars);

    Boolean updateVideoSize(BarsEntity barsEntity, List<BarsEntity> barsEntities);

    R getCourseList(QueryCourseInfoRequest queryCourseInfoRequest, final AuthUserDetails authUserDetails);

    R getCourseInfoById(CoursesEntity courses);

    R getCourseVideoInfoById(CoursesEntity courses, final AuthUserDetails authUserDetails);

    R getSubjectCategories(
            final GetSubjectCategoriesRequest getSubjectCategoriesRequest, final AuthUserDetails authUserDetails);

    // 课程热搜与最近搜索实现
    R listHotSearch();

    /**
     * 最近搜索列表
     *
     * @return
     */
    R listRecentSearch(final AuthUserDetails authUserDetails);

    R recentAndHotSearch(AuthUserDetails authUserDetails);

    R becomeMemberShip(final AuthUserDetails authUserDetails);

    List<CiliDataBoardResponse.Main1> getCoursesCountBySubject();
}
