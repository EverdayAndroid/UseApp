package com.everday.useapp.entity;

public class VersionBean {
    private int appCode;
    private String appVersionName;
    private String appDownloadPaht;
    private String appUpdateContent;
    private int appForce;
    public int getAppCode() {
        return appCode;
    }

    public String getAppVersionName() {
        return appVersionName == null ? "" : appVersionName;
    }

    public String getAppDownloadPaht() {
        return appDownloadPaht == null ? "" : appDownloadPaht;
    }

    public String getAppUpdateContent() {
        return appUpdateContent == null ? "" : appUpdateContent;
    }

    public int getForce() {
        return appForce;
    }
}
