package com.everday.useapp.entity;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/5
 * description: 验证码
 */
public class CodeBean {
    private String checkCode;
    private String bizId;
    private String state;

    public String getCode() {
        return checkCode == null ? "" : checkCode;
    }

    public String getBizId() {
        return bizId == null ? "" : bizId;
    }

    public String getState() {
        return state == null ? "" : state;
    }
}
