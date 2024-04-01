package site.cilicili.frontend.common.pojo;

import cn.hutool.json.JSONUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import site.cilicili.frontend.course.domain.dto.GetCourseListResponse;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.common.pojo
 * Date:2024/3/31 15:28
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Component
@RequiredArgsConstructor
public class SearchRedisHelper {
    /**
     * 热搜的KEY
     */
    public static final String HOT_SEARCH = "course_hot_search";
    /**
     * 最近搜索的KEY
     */
    public static final String RECENT_SEARCH = "course_recent_search";
    /**
     * 最近搜索的大小
     */
    public static final Integer CURRENT_SEARCH_SIZE = 3;
    /**
     * 最热搜索KEY过期时间
     */
    public static final Integer HOT_SEARCH_EXPIRE_TIME = 3;
    private final RedisTemplate redisTemplate;

    /**
     * 设置redis的过期时间
     * expire其实是懒加载，不设置key的时候是不会执行的
     */
    @PostConstruct
    public void setHotSearchExpireTime() {
        redisTemplate.expire(HOT_SEARCH, HOT_SEARCH_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    /**
     * redis添加最近搜索
     *
     * @param query
     * @param userId
     */
    public void addRedisRecentSearch(String query, final Long userId) {
        UserRecentSearch userRecentSearch = new UserRecentSearch();
        // 用户id，当前用户id
        userRecentSearch.setUnionId(userId);
        // 搜索信息
        userRecentSearch.setSearchInfo(query);
        // score为一个分值，需要把最近浏览的商品id的分值设为最大值，
        // 此处我们可以设置为当前时间Instant.now().getEpochSecond()
        // 这样最近浏览的商品id的分值一定最大，排在Zset集合最前面
        ZSetOperations<String, UserRecentSearch> zSet = redisTemplate.opsForZSet();
        // 由于zSet的集合特性当插入已经存在的V值（商品id）时只会更新score值，
        zSet.add(RECENT_SEARCH, userRecentSearch, Instant.now().getEpochSecond());

        // 获取到全部用户的最近搜索记录，用reverseRangeWithScores方法，可以获取到根据score排序之后的集合
        Set<ZSetOperations.TypedTuple<UserRecentSearch>> typedTuples = zSet.reverseRangeWithScores(RECENT_SEARCH, 0, -1);

        // 只得到当前用户的最近搜索记录,注意这里必须保证set集合的顺序
        Set<UserRecentSearch> userRecentSearches = listRecentSearch(userId);

        if (userRecentSearches.size() > CURRENT_SEARCH_SIZE) {
            // 获取到最开始浏览的第一条
            UserRecentSearch userRecentSearchLast = userRecentSearches.stream().reduce((first, second) -> second).orElse(null);
            // 删除最开始浏览的第一条
            zSet.remove(RECENT_SEARCH, userRecentSearchLast);
        }
    }

    /**
     * 热搜列表
     *
     * @return
     */
    public Set<HotSearch> listHotSearch() {
        // 0 5 表示0-5下标对应的元素
        return (Set<HotSearch>) Optional.ofNullable(redisTemplate.opsForZSet().reverseRangeWithScores(HOT_SEARCH, 0, 5))
                .map(set -> {
                    return set.stream().map(item -> {
                        if (item instanceof DefaultTypedTuple<?> tuple) {
                            return HotSearch.builder().score(tuple.getScore()).courseListResponse(JSONUtil.toBean(tuple.getValue() + "", GetCourseListResponse.class)).build();
                        }
                        return item;
                    }).collect(Collectors.toSet());
                })
                .orElse(Collections.emptySet());
    }

    /**
     * redis添加热搜
     *
     * @param productList
     */
    public void addRedisHotSearch(List<GetCourseListResponse> productList) {
        // 1：表示每调用一次，当前product的分数+1
        productList.forEach(product -> redisTemplate.opsForZSet().incrementScore(HOT_SEARCH, JSONUtil.toJsonStr(product), 1D));
    }

    /**
     * 最近搜索列表
     *
     * @return
     */
    public Set<UserRecentSearch> listRecentSearch(final Long userId) {
        Set<ZSetOperations.TypedTuple<UserRecentSearch>> typedTuples = redisTemplate.opsForZSet().reverseRangeWithScores(RECENT_SEARCH, 0, -1);
        return Optional.ofNullable(typedTuples)
                .map(tuples -> tuples.stream()
                        .map(ZSetOperations.TypedTuple::getValue)
                        .filter(Objects::nonNull)
                        .filter(userRecentSearch -> Objects.equals(userRecentSearch.getUnionId(), userId))
//                         .filter(userRecentSearch -> Objects.equals(userRecentSearch.getUnionId(), 100434L))
                        .collect(Collectors.collectingAndThen(
                                Collectors.toCollection(LinkedHashSet::new), LinkedHashSet::new)))
                .orElse(null);
    }

}

