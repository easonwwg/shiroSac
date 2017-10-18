package com.sac.cache;

/**
 * Created by EAISON on 2017/10/16.
 */

//@Repository(value = "redisDao")
public class RedisDao extends RedisGeneratorDao<String, String> {

    /**
     * 具体的方法可以工具业务抽象未接口
     *
     * @param key
     * @param value
     */
    public void add(String key, String value) {
  /*  RedisSerializer<String> serializer = getRedisTemplate().getStringSerializer();
    getRedisTemplate().opsForValue().set(key,value);*/
        redisTemplate.opsForValue().set(key, value);
    }

    public String Get(String key) {
        return redisTemplate.opsForValue().get(key);
       /* return   getRedisTemplate().opsForValue().get(key);*/
    }

}
