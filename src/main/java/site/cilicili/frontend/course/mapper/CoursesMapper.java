package site.cilicili.frontend.course.mapper;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.frontend.chart.domain.CiliDataBoardResponse;
import site.cilicili.frontend.course.domain.dto.*;
import site.cilicili.frontend.course.domain.pojo.CoursesEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-02-27 00:01:27
 */

/**
 * (Courses)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:27
 */
@Mapper
public interface CoursesMapper extends BaseMapper<CoursesEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    CoursesEntity queryById(Integer courseId);

    CoursesEntity queryByParams(CoursesEntity courses);

    /**
     * 查询指定行数据
     *
     * @param courses 查询条件
     * @return 对象列表
     */
    List<CoursesEntity> queryAll(CoursesEntity courses);

    /**
     * 统计总行数
     *
     * @param courses 查询条件
     * @return 总行数
     */
    long count(CoursesEntity courses);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CoursesEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CoursesEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CoursesEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CoursesEntity> entities);

    /**
     * 修改数据
     *
     * @param courses 实例对象
     * @return 影响行数
     */
    int update(CoursesEntity courses);

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 影响行数
     */
    int deleteById(Integer courseId);

    List<GetCourseInfoResponse.CourseList> selectCourseByParam(QueryCourseInfoRequest courses);

    CetCoursesCountResponse getCoursesCount(CoursesEntity courses);

    int logicalDeleteCourseInfoById(CoursesEntity courses);

    GetChildrenBarResponse getChildrenBar(final Integer id);

    List<CoursesEntity> getCourseList(QueryCourseInfoRequest queryCourseInfoRequest);

    GetCourseVideoInfoByIdResponse getCourseVideoInfoById(CoursesEntity courses);

    GetSubjectCategoriesResponse getSubjectCategories(final GetSubjectCategoriesRequest getSubjectCategoriesRequest);

    Integer updateBatch(@Param("coursesEntities") List<CoursesEntity> coursesEntities);

    Integer countByParam(QueryCourseInfoRequest courses);

    List<CiliDataBoardResponse.Main1> getCoursesCountBySubject();

    List<Map<String, Object>> getCourseTotalByWeek(@Param("start") DateTime start, @Param("end") Date end);

    List<Map<String, Object>> getOnTotalByWeek(@Param("start") DateTime start, @Param("end") Date end);

    List<Map<String, Object>> getPassByWeek(@Param("start") DateTime start, @Param("end") Date end);

    List<Map<String, Object>> getMoreVis();
}
