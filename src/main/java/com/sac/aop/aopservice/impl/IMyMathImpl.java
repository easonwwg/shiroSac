package com.sac.aop.aopservice.impl;

import com.sac.aop.aopservice.IMyMath;
import com.sac.pojo.PojoValidate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;

/**
 * @author:eason
 * @Description
 * @Date: 20:24,2018/4/24
 * @ModifiedBy
 */
@Service
public class IMyMathImpl implements IMyMath {

    /*@Override
    public int add(PojoValidate pojoValidate) {
        return pojoValidate.getUserName().length();
    }*/

    @Override
    public int add(@Min(50)int age) {
        return age;
    }
}
