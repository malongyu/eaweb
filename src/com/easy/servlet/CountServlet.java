package com.easy.servlet;

import com.easy.util.Sys;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CountServlet", value = "/easyk")
public class CountServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute(Sys.LOGIN_USER,"");
        req.getRequestDispatcher("online.jsp").forward(req,resp);
    }
}
