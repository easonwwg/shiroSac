package com.sac.aop;

import com.sac.exception.BusinessException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author:eason
 * @Description
 * @Date: 20:22,2018/4/24
 * @ModifiedBy
 */
@Component
@Aspect
public class Advices {

    // 切点
    @Pointcut("execution(* com.sac.aop.aopservice.IMyMath.a*(..))") //接口代理测试
    // @Pointcut("execution(* com.sac.aop.aopservice.impl.IMyMathImpl.a*(..))") //类代理测试
    public void pointcut() {
    }

    @Pointcut("execution(* com.sac.web.UsersController.t*(..))")
    //@Pointcut("execution(* org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(..))")
    public void pointcut1() {
    }

    @Around("pointcut1() || pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());
        System.out.println("環繞前做校验");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Object po = proceedingJoinPoint.getArgs()[0];
        Set<ConstraintViolation<Object>> validates = validator.validate(po);
        for (ConstraintViolation<Object> item : validates) {
            System.out.println(item.getMessage());
            throw new BusinessException(item.getMessage());
        }
        Object object = proceedingJoinPoint.proceed();
        System.out.println("環繞后");
        return object;
    }
}
