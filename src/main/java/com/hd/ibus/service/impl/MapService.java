package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.EquipmentMapper;
import com.hd.ibus.mapper.HeatMapDataMapper;
import com.hd.ibus.mapper.StationMapper;
import com.hd.ibus.pojo.Equipment;
import com.hd.ibus.pojo.HeatmapPoint;
import com.hd.ibus.pojo.Station;
import com.hd.ibus.service.BaseService;
import com.hd.ibus.service.IMapService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CarloJones on 2017/7/18.
 */
@Service
public class MapService implements IMapService,BaseService {
    @Resource
    private HeatMapDataMapper heatMapDataMapper;

    @Resource
    private EquipmentMapper equipmentMapper;

    @Resource
    private StationMapper stationMapper;

    public List<HeatmapPoint> getHeatmapPoints() {
        return heatMapDataMapper.getHeatMapPoints();
    }

    public List<Station> getStationList(){
        List<Station> stations = stationMapper.queryAll();
        return stations;
    }

    public List<Equipment> getEquipmentList() {
        List<Equipment> equipments = equipmentMapper.queryAll();
        return equipments;
    }
}
