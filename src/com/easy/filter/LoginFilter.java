package com.easy.filter;

import com.easy.util.Sys;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName LoginFilter
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/25 17:55
 * @Version 1.0
 */

//@WebFilter(filterName = "LoginFilter", urlPatterns = {"/jsp/*"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //验证用户是否有登陆的状态
        HttpServletRequest req=null;
        HttpServletResponse resp=null;
        if(servletRequest instanceof HttpServletRequest){
             req=(HttpServletRequest) servletRequest;
             resp=(HttpServletResponse) servletResponse;
        }
        //通过请求获取session对象
        HttpSession session=req.getSession();
        //检查是否存储了登陆的信息
        Object loginobj=session.getAttribute(Sys.LOGIN_USER);
        //如果登录了 就放行
        if(loginobj!=null){
            filterChain.doFilter(req,resp);
        }else{
            //如果没有登陆就回应对应数据
            System.out.println(req.getRequestURI());
            resp.sendRedirect("http://localhost:8080/eaweb/login.jsp");

        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
