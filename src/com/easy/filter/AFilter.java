package com.easy.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @ClassName AFilter
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/25 22:03
 * @Version 1.0
 */

@WebFilter(filterName = "AFilter", urlPatterns = "/easyi")
public class AFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("1");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("1/1");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
