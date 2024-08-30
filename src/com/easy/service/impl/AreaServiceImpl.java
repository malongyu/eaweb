package com.easy.service.impl;

import com.easy.bean.Area;
import com.easy.dao.AreaDao;
import com.easy.service.IAreaService;

import java.util.List;

/**
 * @ClassName AreaServiceImpl
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/26 17:06
 * @Version 1.0
 */


public class AreaServiceImpl implements IAreaService {
    AreaDao dao=new AreaDao();
    @Override
    public List<Area> list(String id) throws Exception {
        return dao.list(id);
    }
}
