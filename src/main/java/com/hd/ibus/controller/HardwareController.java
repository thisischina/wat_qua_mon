package com.hd.ibus.controller;

import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.HardworeService;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 硬件信息接口
 * Created by ThinkPad on 2017-07-31.
 */
@Controller
@RequestMapping("hardware")
public class HardwareController {

    @Resource
    private HardworeService hardworeService;

    private PageHelp pageHelp = PageHelp.getInstance();

    @RequestMapping("to_list")
    public String toHardwareList(){
        return "hardwareInfo/hardwareInfo_list";
    }

    @RequestMapping("getList")
    public @ResponseBody DataGridResultInfo hardwareList(HttpServletRequest request, HttpServletResponse response, Integer pageNow) throws IOException{

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        return hardworeService.hardworeList(pageHelp,pageNow);
    }

}
