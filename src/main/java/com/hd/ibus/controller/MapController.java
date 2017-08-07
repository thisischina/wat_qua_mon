package com.hd.ibus.controller;

import com.alibaba.fastjson.JSONObject;
import com.hd.ibus.mapper.StationMapper;
import com.hd.ibus.pojo.HeatmapPoint;
import com.hd.ibus.pojo.Station;
import com.hd.ibus.service.IMapService;
import com.hd.ibus.service.impl.MapService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by CarloJones on 2017/7/18.
 */
@Controller
@RequestMapping("map")
public class MapController {
    @Resource
    private IMapService mapService;

    @RequestMapping("heatmap_points")
    @ResponseBody
    public String getHeatMapPoints(){
        List<HeatmapPoint> points =  mapService.getHeatmapPoints();
        JSONObject Jpoints = new JSONObject();
        Jpoints.put("points",points);
        String JpointsString = Jpoints.toString();
        return JpointsString;
    }

    @RequestMapping("getStationList")
    @ResponseBody
    public String getStationList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", mapService.getStationList());
        String jsonString = jsonObject.toString();
        return jsonString;
    }

    @RequestMapping("getEquipmentList")
    @ResponseBody
    public String getEquipmentList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", mapService.getEquipmentList());
        String jsonString = jsonObject.toString();
        return jsonString;
    }

    @RequestMapping("addStationFromPage")
    @ResponseBody
    public JSONObject addStationFromPage(Station station){
        return mapService.insertStationFromPage(station);
    }

    @RequestMapping("getStation")
    @ResponseBody
    public JSONObject getStation(Station station){
        return  mapService.getStation(station);
    }

    @RequestMapping("deleteStation")
    @ResponseBody
    public JSONObject deleteStation(Station station){
        return mapService.deleteStation(station);
    }

    @RequestMapping("editStation")
    @ResponseBody
    public JSONObject editStation(Station station){
        return mapService.updateStation(station);
    }

    @RequestMapping("testAjax")
    public String test(){
        return "index/TestAjax";
    }
}
