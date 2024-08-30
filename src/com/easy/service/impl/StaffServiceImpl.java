package com.easy.service.impl;

import com.easy.bean.Staff;
import com.easy.dao.StaffDao;
import com.easy.service.IStaffService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName StaffServiceImpl
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/26 11:12
 * @Version 1.0
 */


public class StaffServiceImpl implements IStaffService {
    StaffDao dao=new StaffDao();
    @Override
    public List<Staff> list(HttpServletRequest req) {

        List<Staff> list=null;
        list=dao.list();
        return list;
    }
}
