package com.sac.service.business.Impl;

import com.sac.cache.RedisDao;
import com.sac.service.business.Interface.SessionTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by EAISON on 2017/10/16.
 */
@Service
public class SessionTestImpl<K,V> implements SessionTest {

    RedisDao<String,String> redisDao;
    public  SessionTestImpl(RedisTemplate<K,V> redisTemplate){

        redisDao=new RedisDao(redisTemplate);
    }

    @Override
    public void put(String key, String value) {
        redisDao.set(key,value,30000);
    }

    @Override
    public String get(String key) {
      return   redisDao.get(key);
    }
}
