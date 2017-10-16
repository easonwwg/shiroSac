package com.sac.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by EAISON on 2017/10/16.
 */

@Repository(value = "redisDao")
public class RedisDao extends RedisGeneratorDao<String,String> {

    /**
     * 具体的方法可以工具业务抽象未接口
     * @param key
     * @param value
     */
    public  void  add(String key,String value){
  /*  RedisSerializer<String> serializer = getRedisTemplate().getStringSerializer();
    getRedisTemplate().opsForValue().set(key,value);*/
        getRedisTemplate().opsForValue().set(key,value);
}
public  String  Get(String key){

      return   getRedisTemplate().opsForValue().get(key);
       /* return   getRedisTemplate().opsForValue().get(key);*/
}

}
