package com.sac.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:eason
 * @Description：定义控制器的方法拦截,保存日志
 * @Date: 11:07,2017/11/6
 * @ModifiedBy
 */
public class ControllerMethodInterceptor implements MethodInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        logger.error("Before: interceptor name: {}", invocation.getMethod().getName());

        System.out.println(SecurityUtils.getSubject().getPrincipals());

        //判断类是被什么拦截的
        //invocation.getMethod().isAnnotationPresent(xxxx.class);
        logger.error("Arguments: {}", invocation.getArguments());

        Object result = invocation.proceed();

        logger.error("After: result: {}", result);

        return result;

    }
}
