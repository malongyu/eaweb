package com.easy.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AttributeListenerServlet", value = "/easyj")
public class AttributeListenerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name", "EasyJ1");
        req.setAttribute("name", "EasyJ2");
        req.removeAttribute("name");
    }
}
