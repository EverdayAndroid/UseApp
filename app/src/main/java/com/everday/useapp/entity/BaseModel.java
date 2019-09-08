package com.everday.useapp.entity;

public class BaseModel {
    private Object data;
    private String msg;
    private int code;

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return msg == null ? "" : msg;
    }

    public int getResultCode() {
        return code;
    }
}
