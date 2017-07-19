package com.hd.ibus.controller;

import com.alibaba.fastjson.JSONObject;
import com.hd.ibus.pojo.HeatmapPoint;
import com.hd.ibus.service.impl.HeapmapService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CarloJones on 2017/7/18.
 */
@Controller
@RequestMapping("map")
public class MapController {
    @Resource
    private HeapmapService heapmapService;

    @RequestMapping("heatmap_points")
    @ResponseBody
    public String getHeatMapPoints(){
        List<HeatmapPoint> points = heapmapService.getHeatmapPoints();

        JSONObject Jpoints = new JSONObject();
        Jpoints.put("points",points);
        String JpointsString = Jpoints.toString();
        return JpointsString;
    }
}
