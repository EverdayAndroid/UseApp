package com.everday.useapp.entity;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/5
 * description: 商户信息
 */
public class MerchantInfoBean {

    /**
     * msg : OK
     * code : 200
     * data : [{"shId":25,"shmc":"山西科技有限公司"}]
     */

    private String msg;
    private String code;
    private MerchantBean data;

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public MerchantBean getData() {
        return data;
    }
}
