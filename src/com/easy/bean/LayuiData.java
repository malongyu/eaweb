package com.easy.bean;

import java.util.List;

/**
 * @ClassName LayuiDatra
 * @Description TODO
 * @Author MLY
 * @Date 2024/8/26 21:05
 * @Version 1.0
 */


public class LayuiData {
    private int code=0;
    private String msg="";
    private int count;
    private List data;;

    public LayuiData(int count, List data) {
        this.count = count;
        this.data = data;
    }

    public LayuiData() {
    }

    @Override
    public String toString() {
        return "LayuiData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
