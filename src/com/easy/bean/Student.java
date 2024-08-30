package com.easy.bean;

/**
 * @ClassName Student
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/26 14:45
 * @Version 1.0
 */


public class Student {
    private String Sid;
    private String Sname;
    private String Sage;
    private String Ssex;

    @Override
    public String toString() {
        return "Student{" +
                "Sid='" + Sid + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Sage='" + Sage + '\'' +
                ", Ssex='" + Ssex + '\'' +
                '}';
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSage() {
        return Sage;
    }

    public void setSage(String sage) {
        Sage = sage;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }
}
