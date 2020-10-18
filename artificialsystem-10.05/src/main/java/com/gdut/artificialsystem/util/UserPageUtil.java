package com.gdut.artificialsystem.util;

import com.gdut.artificialsystem.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserPageUtil {
    private Integer code=0;
    private String msg="";
    private Integer count=0;
    private List<User> data;

    public UserPageUtil(){
        data =new ArrayList<>();
    }

    public UserPageUtil(List<User> resultlist,Integer count){
        data=resultlist;
        this.count=count;
    }

    public void add(User user){
        data.add(user);
        count= data.size();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
