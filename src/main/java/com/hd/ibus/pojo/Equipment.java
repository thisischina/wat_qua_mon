package com.hd.ibus.pojo;

import java.util.Date;

/**
 * 设备表
 */

public class Equipment {
    private Integer id;

    private String name;

    private String number;

    private Integer typeId;

    private Date lifetime;

    private Long max;

    private Long min;

    private Long samplingFrequency;

    private Date installTime;

    private Integer stationId;

    private Integer state;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", typeId=" + typeId +
                ", lifetime=" + lifetime +
                ", max=" + max +
                ", min=" + min +
                ", samplingFrequency=" + samplingFrequency +
                ", installTime=" + installTime +
                ", stationId=" + stationId +
                ", state=" + state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getLifetime() {
        return lifetime;
    }

    public void setLifetime(Date lifetime) {
        this.lifetime = lifetime;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getSamplingFrequency() {
        return samplingFrequency;
    }

    public void setSamplingFrequency(Long samplingFrequency) {
        this.samplingFrequency = samplingFrequency;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}