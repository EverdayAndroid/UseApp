package com.everday.useapp.entity;

public class ProtocolInfoBean {

    /**
     * msg : OK
     * code : 200
     * data : {"agreement":"亲爱的用户,用人宝重视用户的隐私和个人信息保护，在您正常使用用人宝产品时,您的身份信息,联系方式等信息需依法搜集、使用、储存。用人宝一直采取行业领先的安全防护措施严格保护您的信息安全,具体内容详见《隐私政策》。您在点击同意下列协议前,请您务必审慎阅读,并充分理解协议条款内容。","state":"ok"}
     */

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
         * agreement : 亲爱的用户,用人宝重视用户的隐私和个人信息保护，在您正常使用用人宝产品时,您的身份信息,联系方式等信息需依法搜集、使用、储存。用人宝一直采取行业领先的安全防护措施严格保护您的信息安全,具体内容详见《隐私政策》。您在点击同意下列协议前,请您务必审慎阅读,并充分理解协议条款内容。
         * state : ok
         */

        private String agreement;
        private String state;

        public String getAgreement() {
            return agreement;
        }

        public void setAgreement(String agreement) {
            this.agreement = agreement;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
