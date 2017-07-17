package com.hd.ibus.result;

import com.hd.ibus.pojo.Equipment;

import java.util.List;

/**
 * Created by ThinkPad on 2017-07-13.
 */
public class TreeResultInfo {

    private Integer id;//监测站id
    private String name;//监测站名称
    private List<Equipment> children;//检测设备信息

    public List<Equipment> getChildren() {
        return children;
    }

    public void setChildren(List<Equipment> children) {
        this.children = children;
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
        this.name = name;
    }
}
