package com.easy.bean;

/**
 * @ClassName Area
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/26 17:03
 * @Version 1.0
 */


public class Area {
    private Integer id;
    private String name;
    private Integer parent_id;

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }
}
