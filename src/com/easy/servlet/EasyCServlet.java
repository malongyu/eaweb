package com.easy.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "EasyCServlet", value = "/easyc")
public class EasyCServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受参数
        //username
        String username=request.getParameter("username");
        System.out.println(username);
        response.getWriter().write("Hello, " + username + "!");

        //接受多个参数
        String[] hobbys=request.getParameterValues("hobby");
        //获取所有参数的集合
        Map<String, String[]> parameterMap=request.getParameterMap();
        //遍历所有参数

        for(Map.Entry<String, String[]> entry:parameterMap.entrySet()){
            String key=entry.getKey();
            String[] values=entry.getValue();
            for(String value:values){
                System.out.println(key+":"+value);
            }
        }
        for(String hobby:hobbys){
           response.getWriter().write(hobby+"<br>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
