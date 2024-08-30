package com.easy.service;

import com.easy.bean.User;

public interface ILoginService {
    User login(String username, String userpass);
}
