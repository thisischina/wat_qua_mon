package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.EquipmentMapper;
import com.hd.ibus.mapper.StationMapper;
import com.hd.ibus.pojo.Equipment;
import com.hd.ibus.pojo.Station;
import com.hd.ibus.result.TreeResultInfo;
import com.hd.ibus.service.InitTreeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询所有的监测站和监测设备，并装配成指定json格式，发送前台生成树状图
 * Created by ThinkPad on 2017-07-13.
 */
@Service
public class InitTreeServiceImpl implements InitTreeService{

    @Resource
    StationMapper stationMapper;

    @Resource
    EquipmentMapper equipmentMapper;

    public List<TreeResultInfo> queryAll() {
        List<TreeResultInfo> list = new ArrayList<TreeResultInfo>();
        List<Station> stations = stationMapper.queryAll();
        List<Equipment> equipments = equipmentMapper.queryAll();
        for (Station station:stations){
            TreeResultInfo treeResultInfo = new TreeResultInfo();
            treeResultInfo.setId(station.getId());
            treeResultInfo.setName(station.getName());
            List<Equipment> equipmentList = new ArrayList<Equipment>();//每个station生成一个新的equipment列表
            treeResultInfo.setChildren(equipmentList);
            for (Equipment equipment : equipments){
                if (station.getId().equals(equipment.getStationId())){
                    treeResultInfo.getChildren().add(equipment);
                }
            }
            list.add(treeResultInfo);
        }
        return list;
    }
}
