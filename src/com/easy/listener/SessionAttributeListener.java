package com.easy.listener;

import com.easy.util.Sys;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @ClassName SessionAttributeListener
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/25 23:46
 * @Version 1.0
 */

//@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {

        //如果添加的时Sys.Login 对应的在线人数+1
        String name = se.getName();
        ServletContext con = se.getSession().getServletContext();
        if (Sys.LOGIN_USER.equals(name)) {
            Object obj = con.getAttribute("ON_LINE_NUM");
            if (obj == null)
                con.setAttribute("ON_LINE_NUM", 1);
            else {
                Integer num =(Integer)con.getAttribute("ON_LINE_NUM");
                con.setAttribute("ON_LINE_NUM", num + 1);
            }
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        HttpSessionAttributeListener.super.attributeRemoved(se);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        HttpSessionAttributeListener.super.attributeReplaced(se);
    }
}
