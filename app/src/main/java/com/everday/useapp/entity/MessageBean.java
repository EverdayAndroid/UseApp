package com.everday.useapp.entity;

import java.util.List;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/2
 * description: 消息
 */
public class MessageBean {
    private int id;
    private String title;
    private String typeName;
    private String pubDate;
    private String content;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public String getTypeName() {
        return typeName == null ? "" : typeName;
    }

    public String getPubDate() {
        return pubDate == null ? "" : pubDate;
    }

    public String getContent() {
        return content == null ? "" : content;
    }
}
