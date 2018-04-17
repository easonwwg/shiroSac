package com.sac.aop;

import com.sac.exception.BusinessException;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:eason
 * @Description
 * @Date: 20:10,2018/4/17
 * @ModifiedBy
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理所有不可知的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    Map<String, String> handleException(Exception e) {
        Map<String, String> resultMaps = new HashMap<>();
        resultMaps.put("res", e.getClass().getName());
        return resultMaps;
    }

    /**
     * 处理所有业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    Map<String, String> handleBusinessException(BusinessException e) {
        Map<String, String> resultMaps = new HashMap<>();
        resultMaps.put("res", e.getLocalizedMessage());
        return resultMaps;
    }

    /**
     * 处理所有业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    Map<String, String> handleSQLException(SQLException e) {
        Map<String, String> resultMaps = new HashMap<>();
        resultMaps.put("res", e.getMessage());
        return resultMaps;
    }
}
