package com.hd.ibus.result;

import com.hd.ibus.pojo.Equipment;

import java.util.List;

/**
 * Created by ThinkPad on 2017-07-13.
 */
public class TreeResultInfo {

    private Integer id;//监测站id
    private String name;//监测站名称
    private List<Equipment> equipmentList;//检测设备信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
