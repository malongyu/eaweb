package com.easy.dao;

import com.easy.bean.Staff;
import com.easy.util.JDBCUtil;

import java.util.List;

/**
 * @ClassName StaffDao
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/26 11:13
 * @Version 1.0
 */


public class StaffDao {
    public List<Staff> list()  {
        String sql="select * from t_staff";
        try {
            List<Staff> staffList= JDBCUtil.query(Staff.class,sql);
            return staffList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败");
        }
        return null;
    }
}
