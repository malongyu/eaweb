package com.easy.bean;

import com.easy.util.ID;
import com.easy.util.Table;

/**
 * @ClassName Staff
 * @Description TODO
 * @Author MLY
 * @Date 2024/6/13 16:46
 * @Version 1.0
 */


@Table(tablename = "t_staff")
public class Staff {
    @ID
    private Integer id;
    private String name;
    private Integer age;
    private Double height;
    private String tel;

    /**
     *
     * @param id
     * @param name
     * @param age
     * @param height
     * @param tel
     */
    public Staff(Integer id, String name, Integer age, Double height, String tel) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.tel = tel;
    }

    public Staff() {
        super();
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", tel='" + tel + '\'' +
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
