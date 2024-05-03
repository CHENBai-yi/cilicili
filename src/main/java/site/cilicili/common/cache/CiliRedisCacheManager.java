package site.cilicili.common.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.cache
 * Date:2024/1/25 14:02
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public class CiliRedisCacheManager extends RedisCacheManager {
    public CiliRedisCacheManager(
            final RedisCacheWriter cacheWriter, final RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    /**
     * @param name               {@link String name} for the {@link RedisCache}; must not be {@literal null}.
     * @param cacheConfiguration {@link RedisCacheConfiguration} used to configure the {@link RedisCache};
     *                           resolves to the {@link #getDefaultCacheConfiguration()} if {@literal null}.
     * @return
     */
    @Override
    protected RedisCache createRedisCache(final String name, RedisCacheConfiguration cacheConfiguration) {
        final int lastIndexOf = StringUtils.lastIndexOf(name, '#');
        if (lastIndexOf > -1) {
            final String ttl = StringUtils.substring(name, lastIndexOf + 1);
            final Duration duration = Duration.ofSeconds(Long.parseLong(ttl));
            cacheConfiguration = cacheConfiguration.entryTtl(duration);
            final String cacheName = StringUtils.substring(name, 0, lastIndexOf);
            return super.createRedisCache(cacheName, cacheConfiguration);
        }
        return super.createRedisCache(name, cacheConfiguration);
    }
}
