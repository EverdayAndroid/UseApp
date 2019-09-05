package com.everday.useapp.entity;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/5
 * description: 用户信息
 */
public class UserInfoBean {

    /**
     * msg : OK
     * code : 200
     * data : {"appAccount":{"bankNumber":null,"createByName":"15536668712","shId":1,"nickName":null,"idCard":null,"bankType":null,"sign":0,"remark":null,"avatar":"x.jpg","certification":0,"createBy":null,"balance":0,"createTime":"2019-09-02 18:44:41","id":3,"tele":"15536668712","shmc":"111","status":2},"accessToken":"ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKQlJWTWlmUT09LmV5SnpkV0lpT201MWJHd3NJbUYxWkNJNmJuVnNiQ3dpWlhod1JHRjBZU0k2ZXlKcFpDSTZNeXdpZEdWc1pTSTZJakUxTlRNMk5qWTROekV5SWl3aWMzUmhkSFZ6SWpveWZTd2libUptSWpwdWRXeHNMQ0pwYzNNaU9pSmpibXBqYzI5bWRDSXNJbVY0Y0NJNklqTXdJaXdpYVdGMElqb2lNVFUyTnpReU1qWTVOVFV4TVNJc0ltcDBhU0k2Ym5Wc2JIMD0uWVlJa3Vmem5Od3k3Q2FmYW40bVZwQU9WaS8yNXpicEw2S2dhcHRNWVVMZDNMWCtQMUFUU1NXSUhYU0pnYjljRWxxU1RBR3Q0RjU4bWpnei9vSDQ4MWc9PQ=="}
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
         * appAccount : {"bankNumber":null,"createByName":"15536668712","shId":1,"nickName":null,"idCard":null,"bankType":null,"sign":0,"remark":null,"avatar":"x.jpg","certification":0,"createBy":null,"balance":0,"createTime":"2019-09-02 18:44:41","id":3,"tele":"15536668712","shmc":"111","status":2}
         * accessToken : ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKQlJWTWlmUT09LmV5SnpkV0lpT201MWJHd3NJbUYxWkNJNmJuVnNiQ3dpWlhod1JHRjBZU0k2ZXlKcFpDSTZNeXdpZEdWc1pTSTZJakUxTlRNMk5qWTROekV5SWl3aWMzUmhkSFZ6SWpveWZTd2libUptSWpwdWRXeHNMQ0pwYzNNaU9pSmpibXBqYzI5bWRDSXNJbVY0Y0NJNklqTXdJaXdpYVdGMElqb2lNVFUyTnpReU1qWTVOVFV4TVNJc0ltcDBhU0k2Ym5Wc2JIMD0uWVlJa3Vmem5Od3k3Q2FmYW40bVZwQU9WaS8yNXpicEw2S2dhcHRNWVVMZDNMWCtQMUFUU1NXSUhYU0pnYjljRWxxU1RBR3Q0RjU4bWpnei9vSDQ4MWc9PQ==
         */

        private UserBean appAccount;
        private String accessToken;

        public UserBean getAppAccount() {
            return appAccount;
        }

        public void setAppAccount(UserBean appAccount) {
            this.appAccount = appAccount;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }


    }
}
