package com.everday.useapp.entity;

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
