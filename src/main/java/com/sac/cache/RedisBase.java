package com.sac.cache;

/**
 * Created by EAISON on 2017/10/16.
 */
public interface RedisBase<K, V> {

    /**
     * 获取value
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 赋值
     * @param key
     * @param value
     * @param offset 超值时间
     */
    void set(K key, V value,long offset);


}
