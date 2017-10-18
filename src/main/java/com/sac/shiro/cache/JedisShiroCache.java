package com.sac.shiro.cache;

import com.sac.commons.SerializeUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;

/**
 * Created by EAISON on 2017/10/18.
 */

public class JedisShiroCache<K,V>  implements Cache<K, V> {

    /**
     * 操作Redis工具类
     */
    private JedisManager jedisManager;

    /**
     * cache的key值
     */
    private String name;

    /**
     * 为了不和其他的缓存混淆，采用追加前缀方式以作区分
     */
    private static final String REDIS_SHIRO_CACHE = "shiro-demo-cache:";
    /**
     * Redis 分片(分区)，也可以在配置文件中配置
     */
    private static final int DB_INDEX = 2;

    /**
     * 构造函数
     * @param name
     * @param jedisManager
     */
    public JedisShiroCache(String name, JedisManager jedisManager) {
        this.name = name;
        this.jedisManager = jedisManager;
    }

    /**
     * 自定义relm中的授权/认证的类名加上授权/认证英文名字
     */
    public String getName() {
        if (name == null)
            return "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * 获取cache 根据key
     * @param key
     * @return
     * @throws CacheException
     */
    @Override
    public V get(K key) throws CacheException {
        System.out.println("要获取缓存的key是"+key);
        byte[] byteKey = SerializeUtil.serialize(buildCacheKey(key));
        byte[] byteValue = new byte[0];
        try {
            byteValue = jedisManager.getValueByKey(DB_INDEX, byteKey);
        } catch (Exception e) {
           // LoggerUtils.error(SELF, "get value by cache throw exception",e);
        }
        System.out.println("获取的缓存结果是"+(V) SerializeUtil.deserialize(byteValue));
        return (V) SerializeUtil.deserialize(byteValue);
    }

    /**
     * 保存
     * @param key
     * @param value
     * @return
     * @throws CacheException
     */
    @Override
    public V put(K key, V value) throws CacheException {
        System.out.println("保存的key是"+key);
        V previos = get(key);
        try {
            jedisManager.saveValueByKey(DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)),
                    SerializeUtil.serialize(value), -1);
        } catch (Exception e) {
            //LoggerUtils.error(SELF, "put cache throw exception",e);
        }
        return previos;
    }

    /**
     * 删除 根据key
     * @param key
     * @return
     * @throws CacheException
     */
    @Override
    public V remove(K key) throws CacheException {
        V previos = get(key);
        try {
            jedisManager.deleteByKey(DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)));
        } catch (Exception e) {
            //LoggerUtils.error(SELF, "remove cache  throw exception",e);
        }
        return previos;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        if (keys() == null)
            return 0;
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    /**
     * 返回cache要拼接的key
     * @param key
     * @return
     */
    private String buildCacheKey(Object key) {
        return REDIS_SHIRO_CACHE + getName() + ":" + key;
    }
}

