package com.gdut.artificialsystem.util;


public class Msg {
    private String message;
    private Object object;
    private Integer statusCode=1;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Msg(Integer status,String msg,Object obj){
        message=msg;
        object=obj;
        statusCode=status;
    }

    public Msg(String msg, Object obj){
        this.message=msg;
        this.object=obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
