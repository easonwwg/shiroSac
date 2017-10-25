package com.sac.shiro.cache;

import org.apache.shiro.subject.PrincipalCollection;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 99079 on 2017/10/23.
 */
public class SessionCache {

    /**
     * 线程安全 存放的是用户的session和shiro缓存key的值
     */
    private    static   Map<Serializable,PrincipalCollection> myMap = new ConcurrentHashMap<Serializable,PrincipalCollection>();

    public PrincipalCollection getMyMap(Serializable Serializable) {
        return myMap.get(Serializable);
    }

    public void setMyMap(Serializable serializable,PrincipalCollection principalCollection) {
       myMap.put(serializable,principalCollection);
    }

    public  Map<Serializable,PrincipalCollection> GetSessionCache(){
        return myMap;
    }

}
