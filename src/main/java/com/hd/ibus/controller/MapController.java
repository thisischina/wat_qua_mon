package com.hd.ibus.controller;

import com.alibaba.fastjson.JSONObject;
import com.hd.ibus.pojo.HeatmapPoint;
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
    private IMapService heapmapService;

    @RequestMapping("heatmap_points")
    @ResponseBody
    public String getHeatMapPoints(){
        List<HeatmapPoint> points = heapmapService.getHeatmapPoints();

        JSONObject Jpoints = new JSONObject();
        Jpoints.put("points",points);
        String JpointsString = Jpoints.toString();
        return JpointsString;
    }

    @RequestMapping("getStationList")
    @ResponseBody
    public String getStationList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",heapmapService.getStationList());
        String jsonString = jsonObject.toString();
        return jsonString;
    }

    @RequestMapping("testAjax")
    public String test(){
        return "index/TestAjax";
    }
}
