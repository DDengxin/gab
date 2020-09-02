package com.tengzhi.business.dynamicrow.cache;

import com.tengzhi.base.ehcache.config.ManagerCacheConfig;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.dynamicrow.model.dynamicrow;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

/**
 * @author 鱼游浅水
 * @create 2020-08-01
 * 缓存层
 */
@CacheConfig(cacheNames = "ehCacheConfig")
public interface rowCache {

    @Cacheable(cacheManager= ManagerCacheConfig.CacheManagerName.EHCACHE_CACHE_MAANGER,keyGenerator="keyGeneratorMap")
    Result AllRowObj(Map<String,Object> map);

    @CacheEvict(cacheManager=ManagerCacheConfig.CacheManagerName.EHCACHE_CACHE_MAANGER,keyGenerator="keyGeneratorObject")
    Result SavaAndUpdate(dynamicrow dynamicrow);

    @CacheEvict(cacheManager=ManagerCacheConfig.CacheManagerName.EHCACHE_CACHE_MAANGER,keyGenerator="keyGeneratorObject")
    Result Delete(String menuId);

    Result Delete(Map<String,Object> map);

    Result isAdmin();
}
