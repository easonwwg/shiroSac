package com.sac.shiro.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by EAISON on 2017/10/26.
 */
public class DispatcherDemoFilter  implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----Filter初始化----");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("----调用service之前执行一段代码----");
        filterChain.doFilter(servletRequest, servletResponse); // 执行目标资源，放行
        System.out.println("----调用service之后执行一段代码----");
    }

    @Override
    public void destroy() {
        System.out.println("----Filter销毁----");
    }
}
