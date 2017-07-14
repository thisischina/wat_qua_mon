package com.hd.ibus.pojo;

import java.util.Date;

public class News {
    private Integer id;

    private String title;

    private String content;

    private Integer sendUserId;

    private Date sendTime;

    private String receiveUserIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiveUserIds() {
        return receiveUserIds;
    }

    public void setReceiveUserIds(String receiveUserIds) {
        this.receiveUserIds = receiveUserIds == null ? null : receiveUserIds.trim();
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", sendUserId=" + sendUserId +
                ", sendTime=" + sendTime +
                ", receiveUserIds='" + receiveUserIds + '\'';
    }
}