package com.easy.servlet;

import com.easy.util.Sys;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SuperServlet", value = "/SuperServlet")
public class SuperServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path=req.getRequestURI();
        int index=path.lastIndexOf("/");
        String action=path.substring(index+1);
        req.setAttribute(Sys.ACCTION_NAME,action);
    }
}
