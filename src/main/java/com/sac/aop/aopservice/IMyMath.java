package com.sac.aop.aopservice;

import com.sac.pojo.PojoValidate;

import javax.validation.constraints.Min;

/**
 * @author:eason
 * @Description
 * @Date: 20:23,2018/4/24
 * @ModifiedBy
 */
public interface IMyMath {


    // int add(PojoValidate pojoValidate);


    int add(@Min(50) int age);


}
