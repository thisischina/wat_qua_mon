package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.HeatMapDataMapper;
import com.hd.ibus.pojo.HeatmapPoint;
import com.hd.ibus.service.BaseService;
import com.hd.ibus.service.IHeatmapService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CarloJones on 2017/7/18.
 */
@Service
public class HeapmapService implements IHeatmapService,BaseService {
    @Resource
    private HeatMapDataMapper heatMapDataMapper;

    public List<HeatmapPoint> getHeatmapPoints() {
        return heatMapDataMapper.getHeatMapPoints();
    }
}
