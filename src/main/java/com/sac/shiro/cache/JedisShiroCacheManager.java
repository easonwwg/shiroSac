package com.sac.shiro.cache;

import org.apache.shiro.cache.Cache;

/**
 * 实现了自定义
 * Created by EAISON on 2017/10/18.
 */
public class JedisShiroCacheManager implements ShiroCacheManager {
    private JedisManager jedisManager;

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        return new JedisShiroCache<K, V>(name, getJedisManager());
    }

    @Override
    public void destroy() {
        //如果和其他系统，或者应用在一起就不能关闭
        //getJedisManager().getJedis().shutdown();
    }

    /**
     * 通过get和set方法注入对象
     * @return
     */
    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
