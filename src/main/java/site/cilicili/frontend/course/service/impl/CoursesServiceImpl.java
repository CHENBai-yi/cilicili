package site.cilicili.frontend.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;
import site.cilicili.frontend.bars.service.BarsService;
import site.cilicili.frontend.catalogs.service.CatalogsService;
import site.cilicili.frontend.course.domain.dto.AddCourseRequest;
import site.cilicili.frontend.course.domain.dto.GetCourseInfoResponse;
import site.cilicili.frontend.course.domain.dto.QueryCourseInfoRequest;
import site.cilicili.frontend.course.domain.pojo.CoursesEntity;
import site.cilicili.frontend.course.mapper.CoursesMapper;
import site.cilicili.frontend.course.service.CoursesService;

import java.util.List;
import java.util.Optional;

/**
 * (Courses) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:27
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("coursesService")
public class CoursesServiceImpl extends ServiceImpl<CoursesMapper, CoursesEntity> implements CoursesService {

    private final BarsService barsService;
    private final CatalogsService catalogsService;

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    @Override
    public R queryById(Integer courseId) {
        return R.ok().setData(baseMapper.queryById(courseId));
    }

    /**
     * 全查询
     *
     * @param courses 筛选条件
     * @return 查询结果
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R queryAll(CoursesEntity courses) {
        return R.yes("Success.").setData(baseMapper.queryAll(courses));
    }

    /**
     * 新增数据
     *
     * @param courses 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(CoursesEntity courses) {
        baseMapper.insert(courses);
        return R.ok().setData(courses);
    }

    /**
     * 修改数据
     *
     * @param courses 实例对象
     * @return 实例对象
     */
    @Override
    public R update(CoursesEntity courses) {
        baseMapper.update(courses);
        return R.ok().setData(this.queryById(courses.getCourseId()));
    }

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(Integer courseId) {
        boolean del = baseMapper.deleteById(courseId) > 0;
        return R.ok().setData(del);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R addCourse(final AddCourseRequest courses) {
        return Optional.ofNullable(courses)
                .filter(b -> {
                    courses.setTag(String.join(",", courses.getTags()));
                    return saveOrUpdate(courses);
                })
                .map(c -> {
                    for (final AddCourseRequest.Catalog catalog : c.getCatalog()) {
                        catalog.setCourseId(c.getCourseId());
                        if (catalogsService.saveOrUpdate(catalog)) {
                            catalog.getBar().forEach(item -> item.setCatalogId(catalog.getCatalogId()));
                            if (barsService.saveOrUpdateBatch(catalog.getBar())) {
                                return R.yes("添加成功.");
                            }
                        }
                    }
                    return null;
                }).orElse(R.no(Error.COMMON_EXCEPTION.getMessage()));
    }

    @Transactional(readOnly = true)
    @Override
    public R getCourseInfo(final QueryCourseInfoRequest courses) {
        final List<GetCourseInfoResponse.CourseList> courseLists = baseMapper.selectCourseByParam(courses);
        log.warn(courseLists.toString());
        return R.yes("Success").setData(GetCourseInfoResponse.builder().records(courseLists).page(1).pageSize(10).total(100).build());
    }

    @Transactional(readOnly = true)
    @Override
    public R getCoursesCount(final CoursesEntity courses) {
        return R.yes("Success").setData(baseMapper.getCoursesCount(courses));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R deleteCourseInfoById(final CoursesEntity courses) {
        return Optional.of(baseMapper.logicalDeleteCourseInfoById(courses))
                .filter(flag -> flag > 0)
                .map(flag -> R.yes("操作成功.")).orElse(R.no("操作失败!"));
    }

}


