package com.hd.ibus.pojo;

//单位表

public class Unit {
    private Integer unitId;

    private String name;

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' ;
    }
}