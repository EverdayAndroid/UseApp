package com.everday.useapp.entity;
/**
* data 2019/6/27
* author Everday
* email wangtaohandsome@gmail.com
* desc 消息通知信息
**/
public class PushInfo {
    private String title;
    private String contentText;
    private String contentInfo;

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentText() {
        return contentText == null ? "" : contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentInfo() {
        return contentInfo == null ? "" : contentInfo;
    }

    public void setContentInfo(String contentInfo) {
        this.contentInfo = contentInfo;
    }
}
