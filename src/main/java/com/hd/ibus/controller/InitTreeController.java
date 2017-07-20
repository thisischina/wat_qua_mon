package com.hd.ibus.controller;

import com.alibaba.fastjson.JSONObject;
import com.hd.ibus.result.TreeResultInfo;
import com.hd.ibus.service.InitTreeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ThinkPad on 2017-07-12.
 */
@Controller
@RequestMapping("waterSys")
public class InitTreeController {

    @Resource
    InitTreeService initTreeService;

    @RequestMapping("to_list")
    public String getInfo(){
        return "waterSys/waterSys_list";
    }

    @RequestMapping("getList")
    public @ResponseBody List<TreeResultInfo> getTreeList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        return initTreeService.queryAll();
    }
}
