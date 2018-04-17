package com.sac.aop;

import com.sac.exception.BaseException;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:eason
 * @Description：通用异常接口 dao serivce controller抛出的异常可以在这直接捕获
 * @Date: 14:05,2017/11/7
 * @ModifiedBy
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

    private Logger logger = Logger.getLogger(MyExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        logger.error(ex.getMessage(), ex);
        ModelAndView modelAndView = new ModelAndView();
        //跳转到自定义错误页面
        modelAndView.setViewName("/sysError");
        if (ex instanceof BaseException) {
            modelAndView.addObject("msg", ex.getMessage());
        } else {
            map.put("msg", "系统错误!");
        }
        return modelAndView;
    }
}
