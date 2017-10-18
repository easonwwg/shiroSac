package com.sac.cache;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by 99079 on 2017/10/16.
 */
public abstract class RedisGeneratorDao<K, V> {
   // @Autowired
    protected RedisTemplate<K,V> redisTemplate ;

  //  protected RedisTemplate<K,V> getRedisTemplate() {
       // return this.redisTemplate;
   // }
}
