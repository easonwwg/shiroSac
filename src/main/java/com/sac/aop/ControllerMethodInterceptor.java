package com.sac.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author:eason
 * @Description：定义控制器的方法拦截,保存日志
 * @Date: 11:07,2017/11/6
 * @ModifiedBy
 */
public class ControllerMethodInterceptor implements MethodInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private  final  String restPackageName="com.sac.rest";
    private  final  String webPackageName="com.sac.rest";
    private  final  String login="login";
    private  final  String  logOut="toLogOut";

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        logger.error("环绕前: interceptor name: {}", invocation.getMethod().getName());
        Object[] methodsArgs = invocation.getArguments();
        if (invocation.getMethod().getDeclaringClass().getPackage().getName().contains(restPackageName)) {
            System.out.println("记录用户请求接口的传入和返回的参数");
        } else if (invocation.getMethod().getDeclaringClass().getPackage().getName().contains(webPackageName)) {
            Object obj = Arrays.stream(methodsArgs).filter(s ->
                            s instanceof HttpServletRequest
            ).findAny().orElse(null);
            if (obj != null) {
                HttpServletRequest request = (HttpServletRequest) obj;
                if (login.equals(invocation.getMethod().getName())) {
                    System.out.println("登陆时间是" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                } else if (logOut.equals(invocation.getMethod().getName())) {
                    System.out.println("登出时间是" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                }
                System.out.println("用户请求的ip是" + request.getRemoteAddr());
            }
        }
        result = invocation.proceed();
        logger.error("环绕后: result: {}", result);
        return result;
    }
}
