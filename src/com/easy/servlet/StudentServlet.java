package com.easy.servlet;

import com.alibaba.fastjson2.JSON;
import com.easy.bean.LayuiData;
import com.easy.bean.Staff;
import com.easy.bean.Student;
import com.easy.dao.StudentDao;
import com.easy.service.IStudentService;
import com.easy.service.impl.StudentServiceImpl;
import com.easy.util.Sys;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/student/*")
public class StudentServlet extends SuperServlet {
    IStudentService service = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        Object action = req.getAttribute(Sys.ACCTION_NAME);
        if ("add".equals(action)) {
            String sname = req.getParameter("name");
            String sid = req.getParameter("id");
            String sage = req.getParameter("age");
            String ssex = req.getParameter("sex");
            int result = service.add(sid, sname, sage, ssex);
            System.out.println(result);
            resp.getWriter().write(result);
        } else if ("delete".equals(action)) {
            String id = req.getParameter("sid");
            int result = service.delete(id);
            resp.getWriter().write(result + "");

        } else if ("edit".equals(action)) {
            String sid = req.getParameter("sid");
            String sname = req.getParameter("sname");
            String sage = req.getParameter("sage");
            String ssex = req.getParameter("ssex");
            System.out.println(sid + " " + sname + " " + sage + " " + ssex);
            int result = service.edit(sid, sname, sage, ssex);
            resp.getWriter().write(result + "");

        } else if ("list".equals(action)) {
            List<Student> stus = service.list();
            //将数据存入req 转发给jsp
            req.setAttribute("studentlist", stus);
            req.getRequestDispatcher("/jsp/studentlist").forward(req, resp);
        } else if ("ajaxlist".equals(action)) {
            List<Student> stus = service.list();
            String result = JSON.toJSONString(stus);
            resp.getWriter().write(result);
        } else if ("layuilist".equals(action)) {
            String page = req.getParameter("page");
            String limit = req.getParameter("limit");
            String sid = req.getParameter("sid");
            String sname = req.getParameter("sname");
            int count = 0;
            if ((sid != "" || sname != "") && (sid != null || sname != null)) {
                LayuiData layui_list = service.list_layui(sid, sname, page, limit);
                String json = JSON.toJSONString(layui_list);
                resp.getWriter().write(json);
            } else {
                //查询数据数量
                count = service.count();
                /**如果是本页最后一个数据 删除后跳转前一个页面
                 * */
                if (count % Integer.parseInt(limit) == 0 && count / Integer.parseInt(limit) < Integer.parseInt(page)) {
                    page = String.valueOf(Integer.parseInt(page) - 1);
                    req.getRequestDispatcher("student/layuilist?page=" + page + "&limit=" + limit).forward(req, resp);
                }
                List<Student> stus = service.list(sid, sname, page, limit);
                //返回四个属性的对象 layui才能正确解析
                LayuiData result = new LayuiData(count, stus);
                String json = JSON.toJSONString(result);
                resp.getWriter().write(json);
            }
        }
    }
}
