package com.me.daydaystudy.bean;

/**
 * @author :   郗琛
 * @date :   2017/1/16
 */

public class LoginBean {

    private int status;
    private Object data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
