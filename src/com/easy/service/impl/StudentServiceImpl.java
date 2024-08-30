package com.easy.service.impl;

import com.easy.bean.LayuiData;
import com.easy.bean.Student;
import com.easy.dao.StudentDao;
import com.easy.service.IStudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/26 14:52
 * @Version 1.0
 */


public class StudentServiceImpl implements IStudentService {
    StudentDao dao = new StudentDao();

    @Override
    public List<Student> list(String page, String limit) {
        List<Student> list = null;
        if (page == null) {
            list = dao.list();
        } else {
            int start = (Integer.parseInt(page) - 1) * Integer.parseInt(limit);
            int end = Integer.parseInt(limit);
            list = dao.list(start, end);
        }
        return list;
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public List<Student> list() {
        List<Student> list = null;
        return dao.list();
    }

    @Override
    public int count() {
        return dao.count();
    }

    /**查询全部数据
     */
    @Override
    public List<Student> list(String sid, String sname, String page, String limit) {
        List<Student> list = null;
        if (page == null) {
            list = dao.list();
        } else {
            int start = (Integer.parseInt(page) - 1) * Integer.parseInt(limit);
            int end = Integer.parseInt(limit);
            list = dao.list(sid, sname, start, end);
        }
        return list;
    }
/**条件查询 查询部分数据
 * */
    @Override
    public LayuiData list_layui(String sid, String sname, String page, String limit) {
        List<Student> list = null;
        int count = 0;
        if (page == null) {
            list = dao.list();
        } else {
            int start = (Integer.parseInt(page) - 1) * Integer.parseInt(limit);
            int end = Integer.parseInt(limit);
            count = dao.count(sid, sname);
            if (count == 0)
                list = null;
            else
                list = dao.list(sid, sname, start, end);
        }
        LayuiData data = new LayuiData(count, list);
        return data;
    }

    @Override
    public int add(String sname, String sid, String sage, String ssex) {
        return dao.add(sid, sname, sage, ssex);
    }

    /**
     * 修改数据
     */
    @Override
    public int edit(String sid, String sname, String sage, String ssex) {
        return dao.edit(sid, sname, sage, ssex);
    }
}
