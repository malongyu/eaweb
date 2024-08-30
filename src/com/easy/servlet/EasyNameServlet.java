package com.easy.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EasyNameServlet", value = "/easyname")
public class EasyNameServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String name = req.getParameter("username");
        System.out.println(name);
        System.out.println(name.equals("n"));
        if(name.equals("n"))
            resp.getWriter().write("0");
        else
            resp.getWriter().write("1");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
