package com.everday.useapp.entity;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/2
 * description: APP版本信息
 */
public class VersionInfoBean {
    private VersionBean data;
    private String message;
    private int resultCode;

    public VersionBean getData() {
        return data;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public int getResultCode() {
        return resultCode;
    }

}
