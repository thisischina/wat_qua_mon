package com.hd.ibus.service;

import com.hd.ibus.pojo.HeatmapPoint;
import com.hd.ibus.pojo.Station;

import java.util.List;

/**
 * Created by CarloJones on 2017/7/18.
 */
public interface IMapService {
    public List<HeatmapPoint> getHeatmapPoints();
    //查询所有检测站信息
    List<Station> getStationList();
}
