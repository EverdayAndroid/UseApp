package com.everday.useapp.entity;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/5
 * description: 验证码
 */
public class CodeInfoBean {

    /**
     * msg : OK
     * code : 200
     * data : {"code":"021563","bizId":"551314467494630934^0","state":"ok"}
     */

    private String msg;
    private String code;
    private CodeBean data;

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public CodeBean getData() {
        return data;
    }
}
