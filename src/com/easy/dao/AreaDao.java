package com.easy.dao;

import com.easy.bean.Area;
import com.easy.util.JDBCUtil;

import java.util.List;

/**
 * @ClassName AreaDao
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/26 17:04
 * @Version 1.0
 */


public class AreaDao {
    public List<Area> list(String id) throws Exception {
        String sql="select * from area where parent_id = ?";
        return JDBCUtil.query(Area.class,sql,id);
    }
}
