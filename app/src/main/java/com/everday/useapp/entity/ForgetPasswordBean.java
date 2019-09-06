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
    private String tele;
    private String password;
    private String checkCode;
    private String bizId;
    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public String getState() {
        return state == null ? "" : state;
    }

    public String getTele() {
        return tele == null ? "" : tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckCode() {
        return checkCode == null ? "" : checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getBizId() {
        return bizId == null ? "" : bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }
}
