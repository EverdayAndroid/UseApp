package com.everday.useapp.entity;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/22
 * description: 银行卡信息
 */
public class BankCard {

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bankName : 交通银行·太平洋借记卡
         * state : ok
         */

        private String bankName;
        private String state;

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
