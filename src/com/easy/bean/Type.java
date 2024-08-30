package com.easy.bean;


import com.easy.util.Lists;

import java.util.List;

/**
 * @ClassName Type
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/29 20:48
 * @Version 1.0
 */


public class Type {
    private String name;
    private Integer id;
    @Lists(className="com.easy.bean.Goods",columnName="id",sql="select * from goods where type_id = ?")
    List<Goods> list;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Goods> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", list=" + list +
                '}';
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }
}
