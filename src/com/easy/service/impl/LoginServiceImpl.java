package com.easy.service.impl;

import com.easy.bean.User;
import com.easy.dao.UserDao;
import com.easy.service.ILoginService;
import com.easy.util.RegUtil;

/**
 * @ClassName LoginServiceImpl
 * @Description 登录实现类
 * @Author MLY
 * @Date 2024/8/25 10:47
 * @Version 1.0
 */


public class LoginServiceImpl implements ILoginService {
    UserDao userD = new UserDao();

    /**
     * 验证登录
     */
    @Override
    public User login(String username, String userpass) {
        User user = null;
        //验证格式
        if (RegUtil.test(username, userpass)) {
            String sql = "select * from user where username=?";
            //查询数据库
            user = userD.queryOne(sql, username);
            if (user != null) {
                //验证密码
                if (userpass.equals(user.getUserpass())) {

                    return user;
                }
            }
            return null;
        }
        return null;
    }
}
