package com.everday.useapp.entity;

public class PublicBean {
    private String state;
    private String url;

    public String getState() {
        return state == null ? "" : state;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }
}
