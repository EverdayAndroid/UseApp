package com.everday.useapp.entity;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/5
 * description: 忘记密码
 */
public class ForgetPasswordBean {
    private String msg;
    private String state;

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public String getState() {
        return state == null ? "" : state;
    }
}
