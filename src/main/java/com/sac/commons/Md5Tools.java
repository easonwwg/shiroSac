package com.sac.commons;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by 99079 on 2017/9/28.
 */
public class Md5Tools {

    public  static String  GetMd5(String str,String pwd,String salt,int
                                  count){
   SimpleHash simpleHash=new SimpleHash(str,pwd,salt,count);
        return  simpleHash.toString();
    }
}
