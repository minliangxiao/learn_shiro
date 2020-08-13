package club.huangliang.learn_shiro.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * 自定的shiro缓存管理器
 */
public class RedisCacheManager implements CacheManager {
    /**
     * @param cacheName  参数s 表示传来的授权缓存器名字 或者 认证缓存器的名字
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {

        return new RedisCache<K,V>(cacheName);
    }
}
