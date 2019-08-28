package com.everday.useapp.entity;

public class BaseModel {
    private Object data;
    private String message;
    private int resultCode;

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public int getResultCode() {
        return resultCode;
    }
}
