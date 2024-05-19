package com.ruoyi.dylan.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class DylanCacheUtils {

    @Autowired
    private CacheManager cacheManager;

    public CacheManager cacheManager(){
        return cacheManager;
    }

    @Autowired
    private static DylanCacheUtils dylanCacheUtils;

    @PostConstruct
    public void init(){
        dylanCacheUtils = this;
    }

    /**
     * 刷新缓存
     */
    public static void evictCache(String cacheName){
        Cache liuliCache = dylanCacheUtils.cacheManager.getCache(cacheName);
        if(liuliCache != null){
            liuliCache.clear();
        }
    }
}
