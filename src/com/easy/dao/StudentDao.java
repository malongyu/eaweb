package com.easy.dao;

import com.easy.bean.Student;
import com.easy.util.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StudentDao
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/26 14:46
 * @Version 1.0
 */


public class StudentDao {
    public List<Student> list() {

        String sql = "select * from student";
        try {
            List<Student> list = JDBCUtil.query(Student.class, sql);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int delete(String id) {
        String sql = "delete from student where Sid=?";
        try {
            return JDBCUtil.update(sql, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Student> list(int start, int end) {
        String sql = "select * from student limit ?,?";
        try {
            List<Student> list = JDBCUtil.query(Student.class, sql, start, end);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询数据条数
     */
    public int count() {
        String sql = "select count(*) from student";
        int count = 0;
        try {
            count = Integer.parseInt(JDBCUtil.queryOne(sql) + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Student> list(String sid, String sname, int start, int end) {
        String sql = "select * from student";
        String where_sql = "";
        List param=new ArrayList();
        if (sid != null && sid.length() > 0) {
            where_sql += " sid=? and";
            param.add(sid);
        }
        if (sname != null && sname.length() > 0) {
            where_sql +=  " sname like ? and";
            param.add("%"+sname+"%");
        }
        if (where_sql.length() > 0) {
            sql +=" where "+ where_sql.substring(0, where_sql.length() - 4);
        }
        sql += " limit ?,?";
        param.add(start);
        param.add(end);
        try {
            List<Student> list = JDBCUtil.query(Student.class, sql, param.toArray());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     *
     * 条件查询操作
     * 获取数量 刷新页面条数显示
     */
    public int count(String sid, String sname) {
        String sql="select count(*) from student ";
        String where_sql="";
        List param=new ArrayList();
        if(sid!=null&& sid.length() > 0){
            where_sql+="sid=? and";
            param.add(sid);
        }
        if(sname!=null&& sname.length() > 0){
            where_sql+=" sname like ? and";
            param.add("%"+sname+"%");
        }
        if(where_sql.length()>0){
            sql+="where "+where_sql.substring(0,where_sql.length()-4);
            System.out.println(sql);
        }
        int count=0;
        try {
            count=Integer.parseInt(JDBCUtil.queryOne(sql,param.toArray()) + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int add(String sid, String sname, String sage, String ssex) {
        String sql = "insert into student(Sid,Sname,Sage,Ssex) values(?,?,?,?)";
        try {
            return JDBCUtil.update(sql, sid, sname, sage, ssex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int edit(String sid, String sname, String sage, String ssex) {
        String sql="update student set Sname=?,Sage=?,Ssex=? where Sid=?";
        try {

            return JDBCUtil.update(sql, sname, sage, ssex, sid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

