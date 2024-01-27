package site.cilicili.backend.chart.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import site.cilicili.backend.chart.service.ChartService;
import site.cilicili.common.util.R;

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
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public R getInfo() {
        // 获取redis缓存完整信息
        // Properties info = redisTemplate.getRequiredConnectionFactory().getConnection().info();
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());

        // 获取redis缓存命令统计信息
        // Properties commandStats = redisTemplate.getRequiredConnectionFactory().getConnection().info("commandstats");
        Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));

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
}
