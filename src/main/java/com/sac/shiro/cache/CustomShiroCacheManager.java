package com.sac.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * 自定义ShiroCacheManager
 * Created by EAISON on 2017/10/18.
 */
public class CustomShiroCacheManager implements CacheManager, Destroyable {

    private ShiroCacheManager shiroCacheManager;

    public ShiroCacheManager getShiroCacheManager() {
        return shiroCacheManager;
    }

    public void setShiroCacheManager(ShiroCacheManager shiroCacheManager) {
        this.shiroCacheManager = shiroCacheManager;
    }

    /**
     *
     * @param name cache的key
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        System.out.println("要获取到缓存的key是-------"+name);
        return getShiroCacheManager().getCache(name);
    }

    @Override
    public void destroy() throws Exception {
        shiroCacheManager.destroy();
    }
}
