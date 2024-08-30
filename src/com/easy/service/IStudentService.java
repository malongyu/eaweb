package com.easy.service;

import com.easy.bean.LayuiData;
import com.easy.bean.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IStudentService {
    public List<Student> list( String  page,String limit);
    public int delete(String id);
    public List<Student> list();
    public int count();

    List<Student> list(String sid, String sname, String page, String limit);
    LayuiData list_layui(String sid, String sname, String page, String limit);

    int add(String sid, String sname, String sage, String ssex);

    int edit(String sid, String sname, String sage, String ssex);
}
