package com.easy.servlet;

import com.easy.bean.User;
import com.easy.service.ILoginService;
import com.easy.service.impl.LoginServiceImpl;
import com.easy.util.Sys;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String userpass = req.getParameter("userpass");
        System.out.println(username + " " + userpass);
        ILoginService service = new LoginServiceImpl();
        User user = service.login(username, userpass);
        if (user != null) {
            req.getRequestDispatcher("jsp/loginsuccess").forward(req, resp);
            System.out.println(user);
            req.getSession().setAttribute(Sys.LOGIN_USER, user);
        } else

            //resp.sendRedirect("login.jsp");
        resp.getWriter().write("阿萨");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
