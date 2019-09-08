package com.everday.useapp.entity;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/2
 * description: APP版本信息
 */
public class VersionInfoBean {
    private VersionBean data;
    private String msg;
    private int code;

    public VersionBean getData() {
        return data;
    }

    public String getMessage() {
        return msg == null ? "" : msg;
    }

    public int getResultCode() {
        return code;
    }

}
