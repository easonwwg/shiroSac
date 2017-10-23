package com.sac.shiro.listener;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 99079 on 2017/10/23.
 */
public class OnlineServiceMemImpl implements OnlineService {

    private static ConcurrentHashMap<String,Object> caches = new ConcurrentHashMap<String,Object>();

    @Override
    public LinkedHashMap<String, Serializable> get(String cacheName) {
        return null;
    }

    @Override
    public void set(String cacheName, LinkedHashMap<String, Serializable> list) {

    }
}
