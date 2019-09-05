package com.everday.useapp.entity;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/5
 * description: 忘记密码
 */
public class ForgetPasswordInfoBean {

    /**
     * msg : OK
     * code : 200
     * data : {"msg":"密码修改成功!","state":"ok"}
     */

    private String msg;
    private String code;
    private ForgetPasswordBean data;

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public ForgetPasswordBean getData() {
        return data;
    }
}
