package site.cilicili.frontend.chart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.chart.domain.CiliDataBoardBackendResponse;
import site.cilicili.common.util.R;
import site.cilicili.frontend.catalogs.service.CatalogsService;
import site.cilicili.frontend.chart.domain.CiliDataBoardResponse;
import site.cilicili.frontend.chart.service.ChartService;
import site.cilicili.frontend.common.pojo.SearchRedisHelper;
import site.cilicili.frontend.course.service.impl.CoursesServiceImpl;
import site.cilicili.frontend.memberShip.service.impl.MemberShipServiceImpl;

import java.util.*;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.chart.service.impl
 * Date:2024/1/27 19:43
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {
    private final CoursesServiceImpl coursesService;
    private final MemberShipServiceImpl memberShipMapper;
    private final SearchRedisHelper searchRedisHelper;
    private final CatalogsService catalogsService;

    /**
     * @return
     */
    @Override
    @Transactional
    public R getCiliDataBoard() {
        final List<CiliDataBoardResponse.Main1> main1 = coursesService.getCoursesCountBySubject();
        final List<CiliDataBoardResponse.Main4> main4 = Optional.ofNullable(memberShipMapper.list(new QueryWrapper<>()))
                .map(memberShipEntities1 -> BeanUtil.copyToList(memberShipEntities1, CiliDataBoardResponse.Main4.class))
                .orElse(Collections.emptyList());

        final Date end = DateUtil.date();
        final DateTime start = DateUtil.offsetDay(end, -6);
        final List<String> dateTimes = DateUtil.rangeToList(start, end, DateField.DAY_OF_YEAR).stream()
                .map(item -> DateUtil.format(item, "M/dd"))
                .toList();
        final CiliDataBoardResponse.Main3 main3 = getMain2(end, start, dateTimes);
        final List<Map<String, Object>> main5 = coursesService.getMoreVis();
        List<CiliDataBoardResponse.Main6.Value> main6 = catalogsService.getCatalogTableData();

        return R.yes("Success.")
                .setData(CiliDataBoardResponse.builder()
                        .main1(main1)
                        .main4(main4)
                        .main3(main3)
                        .main5(CiliDataBoardResponse.Main5.builder()
                                .yData(main5.stream()
                                        .map(item -> item.get("vis"))
                                        .toList())
                                .xData(main5.stream()
                                        .map(item -> item.get("name"))
                                        .toList())
                                .build())
                        .main6(CiliDataBoardResponse.Main6.builder()
                                .uploadData(main6)
                                .build())
                        .build());
    }

    private CiliDataBoardResponse.Main3 getMain2(final Date end, final DateTime start, final List<String> dateTimes) {
        List<Map<String, Object>> courseTotalByWeek = coursesService.getCourseTotalByWeek(start, end);
        List<Map<String, Object>> courseOnByWeek = coursesService.getOnTotalByWeek(start, end);
        List<Map<String, Object>> coursePassByWeek = coursesService.getPassByWeek(start, end);

        final List<Long> totalByWeek = dateTimes.stream()
                .map(item -> {
                    for (final Map<String, Object> map : courseTotalByWeek) {
                        if (item.equals(map.get("date"))) {
                            return (Long) map.get("count");
                        }
                    }
                    return 0L;
                })
                .toList();
        final List<Long> onByWeek = dateTimes.stream()
                .map(item -> {
                    for (final Map<String, Object> map : courseOnByWeek) {
                        if (item.equals(map.get("date"))) {
                            return (Long) map.get("count");
                        }
                    }
                    return 0L;
                })
                .toList();

        final List<Long> passByWeek = dateTimes.stream()
                .map(item -> {
                    for (final Map<String, Object> map : coursePassByWeek) {
                        if (item.equals(map.get("date"))) {
                            return (Long) map.get("count");
                        }
                    }
                    return 0L;
                })
                .toList();

        final List<CiliDataBoardBackendResponse.Main2.Title> titles = new ArrayList<>();
        titles.add(CiliDataBoardBackendResponse.Main2.Title.builder()
                .name("数据总量")
                .value(totalByWeek.stream().reduce(0L, Long::sum))
                .build());
        titles.add(CiliDataBoardBackendResponse.Main2.Title.builder()
                .name("投稿量")
                .value(onByWeek.stream().reduce(0L, Long::sum))
                .build());
        titles.add(CiliDataBoardBackendResponse.Main2.Title.builder()
                .name("通过量")
                .value(passByWeek.stream().reduce(0L, Long::sum))
                .build());

        return CiliDataBoardResponse.Main3.builder()
                .xData(dateTimes)
                .yData(CiliDataBoardResponse.Main3.YData.builder()
                        .updateData(totalByWeek.stream()
                                .map(CiliDataBoardResponse.Main3.YData.Value::new)
                                .toList())
                        .viewData(onByWeek.stream()
                                .map(CiliDataBoardResponse.Main3.YData.Value::new)
                                .toList())
                        .uploadData(passByWeek.stream()
                                .map(CiliDataBoardResponse.Main3.YData.Value::new)
                                .toList())
                        .build())
                .title(titles)
                .build();
    }
}
