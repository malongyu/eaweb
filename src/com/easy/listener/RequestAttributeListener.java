package com.easy.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName RequestAttributeListener
 * @Description 监听对数据的操作
 * @Author MLY
 * @Date 2024/8/25 23:16
 * @Version 1.0
 */

//@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {

        String name=srae.getName();
        Object value=srae.getValue();
        System.out.println("添加"+"name:"+name+" value:"+value);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        ServletRequestAttributeListener.super.attributeRemoved(srae);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        String name=srae.getName();
        Object value=srae.getValue();
        ServletRequest req=srae.getServletRequest();
        System.out.println("替换hou"+"name:"+req.getAttribute("name"));
        System.out.println("替换"+"name:"+name+" value:"+value);
    }
}
