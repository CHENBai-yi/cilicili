package site.cilicili.backend.chart.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RHyperLogLog;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.chart.domain.CiliDataBoardBackendResponse;
import site.cilicili.backend.chart.service.ChartBackendService;
import site.cilicili.backend.log.service.SysLogOperationService;
import site.cilicili.backend.log.service.impl.SysLogLoginServiceImpl;
import site.cilicili.common.util.R;
import site.cilicili.frontend.course.service.CoursesService;

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
public class ChartBackendServiceImpl implements ChartBackendService {
    private final RedisTemplate<String, String> redisTemplate;
    private final SysLogOperationService sysLogOperationService;
    private final SysLogLoginServiceImpl sysLogLoginService;

    private final RHyperLogLog<Object> ipAddressHyperLogLog;
    private final CoursesService coursesService;

    @Override
    public R getInfo() {
        // 获取redis缓存完整信息
        // Properties info = redisTemplate.getRequiredConnectionFactory().getConnection().info();
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());

        // 获取redis缓存命令统计信息
        // Properties commandStats = redisTemplate.getRequiredConnectionFactory().getConnection().info("commandstats");
        Properties commandStats = (Properties)
                redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));

        // 获取redis缓存中可用键Key的总数
        // Long dbSize = redisTemplate.getRequiredConnectionFactory().getConnection().dbSize();
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());

        Map<String, Object> result = new HashMap<>(3);
        result.put("info", info);
        result.put("dbSize", dbSize);

        List<Map<String, String>> pieList = new ArrayList<>();
        commandStats.stringPropertyNames().forEach(key -> {
            Map<String, String> data = new HashMap<>(2);
            String property = commandStats.getProperty(key);
            data.put("name", StringUtils.removeStart(key, "cmdstat_"));
            data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
            pieList.add(data);
        });
        result.put("commandStats", pieList);
        return R.yes("Success").setData(result);
    }

    @Transactional(readOnly = true)
    /**
     * @return
     */
    @Override
    public R getCiliDataBoard() {
        final Date end = DateUtil.date();
        final DateTime start = DateUtil.offsetDay(end, -6);
        final List<String> dateTimes = DateUtil.rangeToList(start, end, DateField.DAY_OF_YEAR).stream().map(item -> DateUtil.format(item, "M/dd")).toList();
        final CiliDataBoardBackendResponse.Main2 main2 = getMain2(end, start, dateTimes);
        return R.yes("Success")
                .setData(CiliDataBoardBackendResponse.builder()
                        .main2(main2)
                        .build());
    }

    private CiliDataBoardBackendResponse.Main2 getMain2(final Date end, final DateTime start, final List<String> dateTimes) {
        List<Map<String, Object>> valueList = sysLogLoginService.getIpData(start, end);
        List<Map<String, Object>> valueList3 = sysLogLoginService.getUvData(start, end);
        List<Map<String, Object>> valueList2 = sysLogOperationService.getPvData(start, end);

        final List<Long> ip = dateTimes.stream().map(item -> {
            for (final Map<String, Object> map : valueList) {
                if (item.equals(map.get("date"))) {
                    return (Long) map.get("count");
                }
            }
            return 0L;
        }).toList();
        final List<Long> pv = dateTimes.stream().map(item -> {
            for (final Map<String, Object> map : valueList2) {
                if (item.equals(map.get("date"))) {
                    return (Long) map.get("count");
                }
            }
            return 0L;
        }).toList();
        final List<Long> uv = dateTimes.stream().map(item -> {
            for (final Map<String, Object> map : valueList3) {
                if (item.equals(map.get("date"))) {
                    return (Long) map.get("count");
                }
            }
            return 0L;
        }).toList();

        final List<CiliDataBoardBackendResponse.Main2.Title> titles = new ArrayList<>();
        titles.add(CiliDataBoardBackendResponse.Main2.Title.builder()
                .name("IP").value(ip.stream().reduce(0L, Long::sum))
                .build());
        titles.add(CiliDataBoardBackendResponse.Main2.Title.builder()
                .name("PV").value(pv.stream().reduce(0L, Long::sum))
                .build());
        titles.add(CiliDataBoardBackendResponse.Main2.Title.builder()
                .name("UV").value(uv.stream().reduce(0L, Long::sum))
                .build());

        return CiliDataBoardBackendResponse.Main2.builder()
                .xData(dateTimes)
                .yData(CiliDataBoardBackendResponse.Main2.YData.builder()
                        .updateData(ip.stream().map(CiliDataBoardBackendResponse.Main2.YData.Value::new).toList())
                        .viewData(pv.stream().map(CiliDataBoardBackendResponse.Main2.YData.Value::new).toList())
                        .uploadData(uv.stream().map(CiliDataBoardBackendResponse.Main2.YData.Value::new).toList())
                        .build())
                .title(titles)
                .build();
    }


}
