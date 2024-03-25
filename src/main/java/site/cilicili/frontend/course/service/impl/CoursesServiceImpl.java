package site.cilicili.frontend.course.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;
import site.cilicili.frontend.bars.domain.mapper.BarsMapper;
import site.cilicili.frontend.bars.domain.pojo.BarsEntity;
import site.cilicili.frontend.bars.service.BarsService;
import site.cilicili.frontend.catalogs.domain.mapper.CatalogMapper;
import site.cilicili.frontend.catalogs.domain.pojo.CatalogsEntity;
import site.cilicili.frontend.catalogs.service.CatalogsService;
import site.cilicili.frontend.course.domain.dto.*;
import site.cilicili.frontend.course.domain.pojo.CoursesEntity;
import site.cilicili.frontend.course.mapper.CoursesMapper;
import site.cilicili.frontend.course.service.CoursesService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final HttpServletRequest httpServletRequest;

    public static String formatDateTime(long mss) {
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        if (days > 0) {
            DateTimes = days + "天" + hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        } else if (hours > 0) {
            DateTimes = hours + "小时" + minutes + "分钟"
                    + seconds + "秒";
        } else if (minutes > 0) {
            DateTimes = minutes + "分钟"
                    + seconds + "秒";
        } else {
            DateTimes = seconds + "秒";
        }

        return DateTimes;
    }

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

    @Transactional(readOnly = true)
    @Override
    public R getCourseInfo(final QueryCourseInfoRequest courses) {
        final List<GetCourseInfoResponse.CourseList> courseLists = baseMapper.selectCourseByParam(courses);
        log.warn(courseLists.toString());
        return R.yes("Success")
                .setData(GetCourseInfoResponse.builder()
                        .records(courseLists)
                        .page(1)
                        .pageSize(10)
                        .total(100)
                        .build());
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
                .map(flag -> R.yes("操作成功."))
                .orElse(R.no("操作失败!"));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R reAudit(final CoursesEntity courses) {
        return Optional.of(baseMapper.update(courses))
                .filter(f -> f > 0)
                .map(r -> R.yes("Success."))
                .orElse(R.no("Fail."));
    }

    @Transactional(readOnly = true)
    @Override
    public R getChildrenBar(final CoursesEntity courses) {

        return Optional.ofNullable(courses.getCourseId())
                .map(id -> baseMapper.getChildrenBar(id))
                .map(data -> {
                    log.debug(data.toString());
                    return R.yes("Success.").setRecords(data);
                })
                .orElse(R.no("Fail."));
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
                    boolean isOk = false;
                    for (final AddCourseRequest.Catalog catalog : c.getCatalog()) {
                        catalog.setCourseId(c.getCourseId());
                        if (catalogsService.saveOrUpdate(catalog)) {
                            catalog.getBar().forEach(item -> item.setCatalogId(catalog.getCatalogId()));
                            if (barsService.saveOrUpdateBatch(catalog.getBar())) {
                                isOk = true;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }

                    return isOk ? R.yes("添加成功.") : null;
                })
                .orElse(R.no(Error.COMMON_EXCEPTION.getMessage()));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R coursesUpdate(final List<GetChildrenBarResponse.Catalog> courses) {
        return Optional.of(catalogsService.updateBatchById(
                        CatalogMapper.CATALOG_MAPPER.cataLogsToCatalogsEntities(courses)))
                .filter(f -> f)
                .filter(f -> {
                    final List<GetChildrenBarResponse.Bar> collect = courses.stream()
                            .flatMap(item -> item.getBar().stream())
                            .toList();
                    return barsService.updateBatchById(BarsMapper.BARS_MAPPER.barsToBarsEntities(collect));
                })
                .map(r -> R.yes("Success."))
                .orElseThrow(() -> new AppException("修改视频数据失败！"));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R updateVideoUrl(final BarsEntity bars) {
        return Optional.ofNullable(bars.getUrl())
                .map(id -> barsService.updateById(bars))
                .filter(f -> f)
                .filter(f -> {
                    final BarsEntity barsEntity = barsService.getById(bars.getBarId());
                    final List<BarsEntity> barsEntities = barsService.list(new QueryWrapper<BarsEntity>().eq("catalog_id", barsEntity.getCatalogId()));
                    return updateVideoSize(barsEntity, barsEntities);
                })
                .map(r -> R.yes("Success."))
                .orElseThrow(() -> new AppException("url不能为空"));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Boolean updateVideoSize(final BarsEntity barsEntity, final List<BarsEntity> barsEntities) {

        final Long totalSecond = barsEntities.stream().map(BarsEntity::getSize).reduce(0L, Long::sum);
        final CatalogsEntity catalogsEntity = new CatalogsEntity();
        catalogsEntity.setCatalogId(barsEntity.getCatalogId());
        catalogsEntity.setTotal(totalSecond);
        catalogsEntity.setTotalTime(formatDateTime(totalSecond));
        Boolean ff = catalogsService.updateById(catalogsEntity);
        final CatalogsEntity catalogs = catalogsService.getOne(new QueryWrapper<CatalogsEntity>().eq("catalog_id", barsEntity.getCatalogId()));
        final Long catalogsTotalSecond = catalogsService.list(new QueryWrapper<CatalogsEntity>().eq("course_id", catalogs.getCourseId())).stream().map(CatalogsEntity::getTotal).reduce(0L, Long::sum);
        final CoursesEntity coursesEntity = new CoursesEntity();
        coursesEntity.setCourseId(catalogs.getCourseId());
        coursesEntity.setTotalTime(formatDateTime(catalogsTotalSecond));
        Boolean fff = baseMapper.update(coursesEntity) > 0;
        return ff && fff;
    }

    @Transactional(readOnly = true)
    @Override
    public R getCourseList(final QueryCourseInfoRequest queryCourseInfoRequest) {
        return Optional.ofNullable(baseMapper.getCourseList(queryCourseInfoRequest))
                .map(courseList -> courseList.stream().map(item -> {
                    final GetCourseListResponse getCourseListResponse = new GetCourseListResponse();
                    getCourseListResponse.setId(item.getCourseId());
                    getCourseListResponse.setTitle(item.getName());
                    getCourseListResponse.setAuthor(item.getTeacher());
                    getCourseListResponse.setTime(DateUtil.format(item.getCreatedAt(), "MM-dd"));
                    final GetCourseListResponse.Detail detail = new GetCourseListResponse.Detail();
                    final GetCourseListResponse.Video video = new GetCourseListResponse.Video();
                    final List<GetCourseListResponse.HighLight> highLights = new ArrayList<>();
                    final String url = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "") + "/";
                    video.setPic(url + item.getPoster());
                    video.setUrl(url + item.getFirstBarUrl());
                    highLights.add(new GetCourseListResponse.HighLight(20L, "这是第 20 秒"));
                    highLights.add(new GetCourseListResponse.HighLight(120L, "这是 2 分钟"));
                    detail.setVideo(video);
                    detail.setHighlight(highLights);
                    getCourseListResponse.setDetail(detail);
                    return getCourseListResponse;
                }).toList())
                .map(r -> R.yes("Success.").setRecords(r)).orElse(R.no("Fail."));
    }

    @Transactional(readOnly = true)
    @Override
    public R getCourseInfoById(final CoursesEntity courses) {
        return Optional.ofNullable(baseMapper.queryByParams(courses))
                .map(r -> R.yes("Success.").setRecords(GetCourseInfoByIdResponse.builder().into(r.getDescription()).learnMore(Arrays.asList(r.getTag().split(","))).build()))
                .orElseThrow(() -> new AppException("暂无该课程信息."));
    }

    @Transactional(readOnly = true)
    @Override
    public R getCourseVideoInfoById(final CoursesEntity courses) {
        //
        final String url = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "") + "/";
        final GetCourseVideoInfoByIdResponse courseVideoInfoById = baseMapper.getCourseVideoInfoById(courses);
        log.debug(courseVideoInfoById.toString());
        return Optional.of(courseVideoInfoById)
                .map(GetCourseVideoInfoByIdResponse::getVideoList)
                .map(videoLists ->
                        videoLists.stream().map(GetCourseVideoInfoByIdResponse.VideoList::getContent).map(GetCourseVideoInfoByIdResponse.Content::getVideo).peek(video -> video.setUrl(url + video.getUrl())).collect(Collectors.toList())
                )
                .map(videos -> courseVideoInfoById.getVideo())
                .map(video -> {
                    video.setPic(url + video.getPic());
                    video.setUrl(url + video.getUrl());
                    video.setThumbnails(url + video.getThumbnails());
                    return video;
                })
                .map(data -> R.yes("Success").setRecords(courseVideoInfoById))
                .orElseThrow(() -> new AppException("暂无该课程信息."));
    }

    @Transactional(readOnly = true)
    @Override
    public R getSubjectCategories() {
        final GetSubjectCategoriesResponse subjectCategories = baseMapper.getSubjectCategories();
        final String url = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "") + "/";
        subjectCategories.getCourses().forEach(item -> item.setPic(url + item.getPic()));
        log.debug(subjectCategories.toString());
        return R.yes("Success.")
                .setData(subjectCategories);
    }
}
