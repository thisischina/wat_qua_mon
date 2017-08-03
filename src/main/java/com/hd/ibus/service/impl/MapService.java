package com.hd.ibus.service.impl;

import com.alibaba.fastjson.JSONObject;
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

    public JSONObject insertStationFromPage(Station station) {
        JSONObject jsonObject = new JSONObject();
        int result = stationMapper.insertAndReturn(station);
        if(result == 1) {
            jsonObject.put("data", "success");
            return jsonObject;
        }else{
            jsonObject.put("data", "error");
            return jsonObject;
        }
    }

    public JSONObject getStation(Station station) {
        JSONObject jsonObject = new JSONObject();
        Integer id = station.getId();
        Station s = stationMapper.selectByID(id);
        if(s != null) {
            jsonObject.put("data", s);
            return jsonObject;
        }else{
            jsonObject.put("data", "error");
            return jsonObject;
        }
    }

    public JSONObject deleteStation(Station station) {
        JSONObject jsonObject = new JSONObject();
        Integer id = station.getId();
        Integer r = stationMapper.delete(id);
        if(r == 1) {
            jsonObject.put("data", "success");
            return jsonObject;
        }else{
            jsonObject.put("data", "error");
            return jsonObject;
        }
    }

    public JSONObject updateStation(Station station) {
        JSONObject jsonObject = new JSONObject();
        int r = stationMapper.updateStation(station);
        if(r == 1) {
            jsonObject.put("data", "success");
            return jsonObject;
        }else{
            jsonObject.put("data", "error");
            return jsonObject;
        }
    }

}
