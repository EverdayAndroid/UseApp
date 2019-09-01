package com.everday.useapp.entity;

public class UserBean {
    private int id;
    private String createBy;
    private String createByName;
    private String createTime;
    private String remark;
    private Integer status;
    private String aslt;
    private String tele;
    private String pwd;
    private String nickName;
    private String idCard;
    private Double balance;
    private String avatar;
    private String bankNumber;
    private String bankType;
    private Integer certification;
    private Integer sign;
    private Integer shld;
    private String shmc;

    public int getId() {
        return id;
    }

    public String getCreateBy() {
        return createBy == null ? "" : createBy;
    }

    public String getCreateByName() {
        return createByName == null ? "" : createByName;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public String getRemark() {
        return remark == null ? "" : remark;
    }

    public Integer getStatus() {
        return status;
    }

    public String getAslt() {
        return aslt == null ? "" : aslt;
    }

    public String getTele() {
        return tele == null ? "" : tele;
    }

    public String getPwd() {
        return pwd == null ? "" : pwd;
    }

    public String getNickName() {
        return nickName == null ? "" : nickName;
    }

    public String getIdCard() {
        return idCard == null ? "" : idCard;
    }

    public Double getBalance() {
        return balance;
    }

    public String getAvatar() {
        return avatar == null ? "" : avatar;
    }

    public String getBankNumber() {
        return bankNumber == null ? "" : bankNumber;
    }

    public String getBankType() {
        return bankType == null ? "" : bankType;
    }

    public Integer getCertification() {
        return certification;
    }

    public Integer getSign() {
        return sign;
    }

    public Integer getShld() {
        return shld;
    }

    public String getShmc() {
        return shmc == null ? "" : shmc;
    }
}
