package com.sac.shiro.listener;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by 99079 on 2017/10/23.
 */
public interface OnlineService {
    /**
     * session前缀
     */
    public final static String ONLINE_USER =  "sac_user";

    /**
     * sessionGet方法
     * @param cacheName
     * @return
     */
    public LinkedHashMap<String, Serializable> get(String cacheName);

    /**
     * sessionSet方法
     * @param cacheName
     * @param list
     */
    public void set(String cacheName,LinkedHashMap<String, Serializable> list);
}
