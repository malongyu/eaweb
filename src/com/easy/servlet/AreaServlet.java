package com.easy.servlet;

import com.alibaba.fastjson2.JSON;
import com.easy.bean.Area;
import com.easy.bean.Student;
import com.easy.service.IAreaService;
import com.easy.service.impl.AreaServiceImpl;
import com.easy.util.Sys;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AreaServlet", value = "/area/*")
public class AreaServlet extends SuperServlet {
    IAreaService service=new AreaServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        Object action = req.getAttribute(Sys.ACCTION_NAME);
        if ("add".equals(action)) {
        } else if ("delete".equals(action)) {

        } else if ("edit".equals(action)) {

        } else if ("list".equals(action)) {
            String parent_id=req.getParameter("parent");
            List<Area> list=null;
            String result=null;
            try {
                 list=service.list(parent_id);
                  result=JSON.toJSONString(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //将数据存入req 转发给jsp
            resp.getWriter().write(result);
        } else if ("ajaxlist".equals(action)) {

        }
    }
}
