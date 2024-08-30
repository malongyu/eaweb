package com.easy.service;

import com.easy.bean.Staff;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IStaffService {
    List<Staff> list(HttpServletRequest req);
}
