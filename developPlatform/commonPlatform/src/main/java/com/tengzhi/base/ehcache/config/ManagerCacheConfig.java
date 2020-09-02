package com.tengzhi.base.ehcache.config;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 *
 * @ClassName   类名：CacheConfig
 * @Description 功能说明：缓存配置类
 * <p>
 * TODO
 *</p>
 ************************************************************************
 * @date        创建日期：2019年3月7日
 * @author      创建人：鱼游浅水
 * @version     版本号：V1.0
 *<p>
 ***************************修订记录*************************************
 *
 *   2020-08-04   鱼游浅水   创建该类功能。
 *
 ***********************************************************************
 *</p>
 */
@Configuration
@EnableCaching
public class ManagerCacheConfig {

    /**
     * Redis过期时间30分钟
     */
    private final Duration timeToLive = Duration.ofMinutes(30);

    /**
     * cacheManager名称
     */
    public interface CacheManagerName {
        /**
         * redis
         */
        String REDIS_CACHE_MANAGER = "redisCacheManager";

        /**
         * ehCache
         */
        String EHCACHE_CACHE_MAANGER = "ehCacheCacheManager";
    }

    /**
     *  定义 StringRedisTemplate ，指定序列号和反序列化的处理类  || 以及Redis缓存管理器
     * @param factory
     * @return
     */
    @Bean(ManagerCacheConfig.CacheManagerName.REDIS_CACHE_MANAGER)
    @Primary
    public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(timeToLive)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        return  RedisCacheManager.builder(factory).cacheDefaults(config).build();
    }


    /**
    * @Param: []
    * @return: org.springframework.cache.ehcache.EhCacheCacheManager
    * @Author: 鱼油浅水
    * @Date: 2020/8/4 10:50
    * @description: Ehcache缓存管理器
    */
    @Bean(ManagerCacheConfig.CacheManagerName.EHCACHE_CACHE_MAANGER)
    public EhCacheCacheManager EhcacheManager() {
        EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
        return ehCacheManager;
    }
}
