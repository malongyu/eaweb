package com.easy.bean;

import com.easy.util.One;

/**
 * @ClassName Goods
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/29 20:20
 * @Version 1.0
 */


public class Goods {
    private String name;
    private Integer id;
    @One(columnName = "type_id",sql="select * from type where id=?")
    private Type type;

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", type=" + type +
                '}';
    }

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
