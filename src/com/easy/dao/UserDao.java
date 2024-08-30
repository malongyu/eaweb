package com.easy.dao;

import com.easy.bean.User;
import com.easy.util.JDBCUtil;

import java.util.List;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/25 12:22
 * @Version 1.0
 */


public class UserDao {
    public List<User> list(String sql, Object... objects) {
        try {
            return JDBCUtil.query(User.class, sql, objects);
        } catch (Exception e) {
            return null;
        }
    }

    public User queryOne(String sql, Object... objects) {
        try {
            List<User> list = JDBCUtil.query(User.class, sql, objects);
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
