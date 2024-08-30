package com.easy.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 用于访问page中的页面
 */
@WebServlet(name = "PageServlet", value = "/jsp/*")
public class PageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String uri=req.getRequestURI();
     int index=uri.lastIndexOf("/");
     String page=uri.substring(index+1);
     String url="../WEB-INF/page/"+page+".jsp";
     req.getRequestDispatcher(url).forward(req,resp);
    }
}
