package com.sac.shiro.filter;

import net.sf.json.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by EAISON on 2017/11/1.
 */
public class ShiroFilterUtils {
    /**
     * 是否是Ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request){
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }

     /**
     * 输出JSON
     * @param response
     * @param resultMap
     */
    public static void out(ServletResponse response, Map<String, String> resultMap){

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(JSONObject.fromObject(resultMap).toString());
        } catch (Exception e) {
        }finally{
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }
}
