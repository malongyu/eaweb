package com.easy.listener;

import com.sun.media.jfxmediaimpl.HostUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName RequestListener
 * @Description 监听请求操作
 * @Author MLY
 * @Date 2024/8/25 22:56
 * @Version 1.0
 */

//@WebListener
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest req=(HttpServletRequest)sre.getServletRequest();
        String uri=req.getRequestURI();
        System.out.println("有一个"+uri+"访问服务器");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("有一个请求被销毁");
    }
}
