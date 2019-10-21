package com.everday.useapp.entity;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/10/17
 * description: 修改密码
 */
public class CheckPasswrodInfoBean {
    /**
     * msg : OK
     * code : 200
     * data : {"msg":"密码修改成功!","state":"ok"}
     */

    private String msg;
    private String code;
    private Object data;

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public Object getData() {
        return data;
    }
}
