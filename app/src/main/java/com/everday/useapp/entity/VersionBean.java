package com.everday.useapp.entity;
/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/7
 * description: 版本更新
 */
public class VersionBean {
    private String note;
    //下载地址
    private String android;
    private String state;
    private String iOS;
    // 0 不需要升级  1 需要升级
    private int isUpdate;
    // 0不强制更新，1为强制更新
    private int force;
    private String versionName;
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAndroid() {
        return android;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIOS() {
        return iOS;
    }

    public void setIOS(String iOS) {
        this.iOS = iOS;
    }

    public int getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }

    public int getForce() {
        return force;
    }

    public String getTitle() {
        return versionName == null ? "" : versionName;
    }
}
