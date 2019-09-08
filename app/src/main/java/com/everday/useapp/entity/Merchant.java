package com.everday.useapp.entity;

public class Merchant {
    private int shId;
    private String shmc;

    public int getShId() {
        return shId;
    }

    public String getShmc() {
        return shmc == null ? "" : shmc;
    }
}
