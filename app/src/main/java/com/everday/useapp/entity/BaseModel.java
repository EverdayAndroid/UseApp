package com.everday.useapp.entity;

public class BaseModel {
    private Object data;
    private String message;
    private int code;

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public int getResultCode() {
        return code;
    }
}
