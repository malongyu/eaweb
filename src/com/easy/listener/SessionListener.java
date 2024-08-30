package com.easy.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName SessionListener
 * @Description 创建session监听器 session只有在创建session时 才生效
 * 可以用于在线人数的统计
 * @Author MLY
 * @Date 2024/8/25 23:39
 * @Version 1.0
 */

//@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("11");
        //人数加1
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
        //人数减1
    }
}
