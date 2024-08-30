package com.easy.servlet;

import com.easy.bean.Staff;
import com.easy.service.IStaffService;
import com.easy.service.impl.StaffServiceImpl;
import com.easy.util.Sys;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * 使用二级路径存储操作信息
 * 解析二级路径中的参数，并根据参数执行相应的操作
 */
@WebServlet(name = "staffServlet", value = "/staff/*")
public class staffServlet extends SuperServlet {
    IStaffService staffservice=new StaffServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        Object action = req.getAttribute(Sys.ACCTION_NAME);
        if("add".equals(action)){

        }else if("delete".equals(action)){

        }else if("edit".equals(action)){

        }else if("list".equals(action)){
            List<Staff> staffs=staffservice.list(req);
            //将数据存入req 转发给jsp
            req.setAttribute("stafflist",staffs);
            req.getRequestDispatcher("/jsp/stafflist").forward(req,resp);
        }


    }
}
