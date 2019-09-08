package com.everday.useapp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/5
 * description: 商户信息
 */
public class MerchantBean {
    private String state;
    private List<Merchant> list;

    public String getState() {
        return state == null ? "" : state;
    }

    public List<Merchant> getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }
}
