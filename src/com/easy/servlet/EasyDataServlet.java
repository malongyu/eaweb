package com.easy.servlet;

import com.alibaba.fastjson2.JSON;
import com.easy.bean.Staff;
import com.easy.util.JDBCUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EasyDataServlet", value = "/data")
public class EasyDataServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Staff> list=null;
        try {
            list= JDBCUtil.query(Staff.class,"select * from t_staff");
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json= JSON.toJSONString(list);

        resp.getWriter().write(json);
    }
}
