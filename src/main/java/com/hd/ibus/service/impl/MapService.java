package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.HeatMapDataMapper;
import com.hd.ibus.mapper.StationMapper;
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
    private StationMapper stationMapper;

    public List<HeatmapPoint> getHeatmapPoints() {
        return heatMapDataMapper.getHeatMapPoints();
    }

    /**
     * Created by Carlos
     * 用于首页地图显示，
     * 返回所有监测站信息
     * */
    public List<Station> getStationList(){
        List<Station> stations = stationMapper.queryAll();
        return stations;
    }
}
