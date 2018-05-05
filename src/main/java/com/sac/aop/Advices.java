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
import javax.validation.executable.ExecutableValidator;
import java.lang.annotation.Annotation;
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

    @Pointcut("execution(* com.sac.web.UsersController.c*(..))")
    //@Pointcut("execution(* org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(..))")
    public void pointcut1() {
    }

    //@Around("pointcut1() || pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        Annotation annotations = method.getParameterAnnotations()[0][0];
        System.out.println(methodName + "---環繞前做校验");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Object po = proceedingJoinPoint.getArgs()[0];
        Set<ConstraintViolation<Object>> validates = validator.validate(po);
        {
            //2.获取校验方法参数的校验器
            ExecutableValidator validatorParam = validator.forExecutables();
            //获取要校验的方法
            Method validateMethod = method;
            ///传递校验参数的输入的参数
            Object[] paramObjects = new Object[]{po};
            Set<ConstraintViolation<Object>> objs = validatorParam.validateParameters(proceedingJoinPoint.getTarget(), validateMethod, paramObjects);
            for (ConstraintViolation<Object> item : objs) {
                System.out.println(item.getMessage());
                throw new BusinessException(item.getMessage());
            }

        }
        for (ConstraintViolation<Object> item : validates) {
            System.out.println(item.getMessage());
            throw new BusinessException(item.getMessage());
        }
        Object object = proceedingJoinPoint.proceed();
        System.out.println(methodName + "---環繞后");
        return object;
    }
}
