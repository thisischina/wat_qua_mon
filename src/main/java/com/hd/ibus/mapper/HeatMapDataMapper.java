package com.hd.ibus.mapper;

import com.hd.ibus.pojo.HeatmapPoint;
import com.hd.ibus.pojo.Station;

import java.util.List;

/**
 * Created by CarloJones on 2017/7/18.
 */
public interface HeatMapDataMapper {
    public List<HeatmapPoint> getHeatMapPoints();
}
