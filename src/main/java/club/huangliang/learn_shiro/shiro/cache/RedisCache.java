package club.huangliang.learn_shiro.shiro.cache;

import club.huangliang.learn_shiro.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

/**
 * 自定义redis缓存的实现
 *
 * 泛型 k ,v一般代表了redis中的键和值
 */
public class RedisCache<K,V> implements Cache<K,V> {

    private String cacheName;
    // 提供有参和无参构造(这样做主要是想做使用redis内置的hash模型，将cacheName设置为总键值)
    public RedisCache(){

    }

    public  RedisCache(String cacheName){
        this.cacheName=cacheName;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("k值为"+k);
        //调用方法拿出 redis对应key的值
        return (V) getRdistemplate().opsForHash().get(this.cacheName,k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("k值为"+k);
        System.out.println("V值为"+v);
        /*
        * 通过工厂类拿到spring容器里面的redistemplate这个类（只要配置了配置文件，spring容器就会默认生成这个类）
        * 在同过redistemplate这个类设置类的key值序列化方式
        * 在通过这个类向redis数据库添加数据
        * */
        getRdistemplate().opsForHash().put(this.cacheName,k.toString(),v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return (V) getRdistemplate().opsForHash().delete(this.cacheName,k.toString());
    }

    @Override
    public void clear() throws CacheException {
        getRdistemplate().opsForHash().delete(this.cacheName);
    }

    @Override
    public int size() {

        return getRdistemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<K> keys() {

        return getRdistemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<V> values() {

        return getRdistemplate().opsForHash().values(this.cacheName);
    }
    /*
    * 封装获取redistempalte
    * */
    private RedisTemplate getRdistemplate(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //序列化hash的键值
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }


}
