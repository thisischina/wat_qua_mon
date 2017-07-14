package com.hd.ibus.pojo;
/**
 * 系统日志
 */

import java.util.Date;

public class SystemLog {
    private Integer id;

    private Integer type;

    private String content;

    private Date oprationTime;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getOprationTime() {
        return oprationTime;
    }

    public void setOprationTime(Date oprationTime) {
        this.oprationTime = oprationTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}