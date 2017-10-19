package com.sac.shiro.cache;

import org.apache.shiro.cache.Cache;

/**
 * 此接口为自定义cacheManager返回cache对象
 * 具体的实现类是JedisShiroCacheManager
 * Created by EAISON on 2017/10/18.
 */
public interface ShiroCacheManager {

    /**
     * 根据缓存id获取对应的缓存
     * @param name
     * @param <K>
     * @param <V>
     * @return
     */
    <K, V> Cache<K, V> getCache(String name);

    void destroy();
}
