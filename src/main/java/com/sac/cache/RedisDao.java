package com.sac.cache;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by EAISON on 2017/10/16.
 */

public class RedisDao<K,V> implements RedisBase<K,V> {


    private RedisTemplate<K,V> redisTemplate;

    public  RedisDao(RedisTemplate<K,V> redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    @Override
    public V get(K key) {
        V value= redisTemplate.opsForValue().get(key);
        return  value;
    }

    @Override
    public void set(K key, V value, long offset) {
        redisTemplate.opsForValue().set(key,value,offset);
    }
}
