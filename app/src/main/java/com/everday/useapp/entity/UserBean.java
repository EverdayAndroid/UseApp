package com.everday.useapp.entity;

public class UserBean {
    private String bankNumber;
    private String createByName;
    private int shId;
    private String nickName;
    private String idCard;
    private String bankType;
    private int sign;
    private String remark;
    private String avatar;
    private int certification;
    private String createBy;
    private int balance;
    private String createTime;
    private int id;
    private String tele;
    private String shmc;
    private int status;
    private String password;

    public String getBankNumber() {
        return bankNumber == null ? "" : bankNumber;
    }

    public String getCreateByName() {
        return createByName == null ? "" : createByName;
    }

    public int getShId() {
        return shId;
    }

    public String getNickName() {
        return nickName == null ? "" : nickName;
    }

    public String getIdCard() {
        return idCard == null ? "" : idCard;
    }

    public String getBankType() {
        return bankType == null ? "" : bankType;
    }

    public int getSign() {
        return sign;
    }

    public String getRemark() {
        return remark == null ? "" : remark;
    }

    public String getAvatar() {
        return avatar == null ? "" : avatar;
    }

    public int getCertification() {
        return certification;
    }

    public String getCreateBy() {
        return createBy == null ? "" : createBy;
    }

    public int getBalance() {
        return balance;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public int getId() {
        return id;
    }

    public String getTele() {
        return tele == null ? "" : tele;
    }

    public String getShmc() {
        return shmc == null ? "" : shmc;
    }

    public int getStatus() {
        return status;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
