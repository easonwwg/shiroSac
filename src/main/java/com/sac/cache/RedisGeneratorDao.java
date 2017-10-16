package com.sac.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

/**
 * Created by 99079 on 2017/10/16.
 */
public abstract class RedisGeneratorDao<K, V> {
    @Autowired
    private RedisTemplate<K,V> redisTemplate ;

    protected RedisTemplate<K,V> getRedisTemplate() {
        return this.redisTemplate;
    }
}
