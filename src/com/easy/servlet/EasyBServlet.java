package com.easy.servlet;

import com.sun.media.jfxmediaimpl.HostUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EasyBServlet", value = "/easyb")
public class EasyBServlet extends HttpServlet {
    //servlet根据用户请求的方式不同，分别调用doGet()和doPost()方法处理请求。


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doget");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("name"));
    }
}
