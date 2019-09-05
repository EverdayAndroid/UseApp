package com.everday.useapp.entity;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/5
 * description: 商户信息
 */
public class MerchantBean {
    private int shId;
    private String shmc;

    public int getShId() {
        return shId;
    }

    public String getShmc() {
        return shmc == null ? "" : shmc;
    }
}
