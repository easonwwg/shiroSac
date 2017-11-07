package com.sac.aop;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:eason
 * @Description：
 * @Date: 14:05,2017/11/7
 * @ModifiedBy
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    private Logger logger = Logger.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        logger.error(ex.getMessage(), ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/sysError");//跳转到自定义错误页面
        if (ex instanceof MaxUploadSizeExceededException) {
            modelAndView.addObject("msg", "文件大小超过限制!");
            return modelAndView;
        }
        map.put("msg", "系统错误!");
        return modelAndView;
    }
}
