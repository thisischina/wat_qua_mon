package com.hd.ibus.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.ibus.pojo.IbusGateway;
import com.hd.ibus.pojo.IbusGroup;
import com.hd.ibus.pojo.IbusNode;
import com.hd.ibus.service.IbusGatewayService;
import com.hd.ibus.service.IbusGroupService;
import com.hd.ibus.service.IbusNodeService;
import com.hd.ibus.util.DateUtils;
import com.hd.ibus.util.Helper;

@Controller
@RequestMapping("/main")
public class ShowController {
	@Resource
	private IbusNodeService ibusNodeService;
	@Resource
	private IbusGatewayService ibusGatewayService;
	
	@Resource
	private IbusGroupService ibusGroupService;
	
	@RequestMapping("/index2")
	public String index2(HttpServletRequest request,Model model){
		return "nodedata/index2";
	}
	
	
	@RequestMapping("/systemManage")
	public String systemManage(HttpServletRequest request,Model model){
		return "system/systemManage";
	}
	
	@RequestMapping("/control")
	public String main(HttpServletRequest request, Model model) {

		List<IbusGateway> gateways = this.ibusGatewayService.findAll();
		model.addAttribute("gateways", gateways);
		return "control/control";
	}
	
	@RequestMapping("/report")
	public String report(HttpServletRequest request,Model model){
		return "statistics/report";
	}
	
	@RequestMapping("/warn")
	public String warn(HttpServletRequest request, Model model) {
		String gatewayId = request.getParameter("gatewayId");
		String nodeId = request.getParameter("nodeId");
		String port = request.getParameter("port");
		String nodeAddr = request.getParameter("nodeAddr");
		model.addAttribute("port", port);
		model.addAttribute("nodeAddr", nodeAddr);
		model.addAttribute("gatewayId", gatewayId);
		model.addAttribute("nodeId", nodeId);
		return "system/warn";
	}
	
	@RequestMapping("/image")
	public String image(HttpServletRequest request, Model model) {
		String groupId = request.getParameter("groupId");
		String port = request.getParameter("port");
		if(groupId!=null&&groupId!=""){
			IbusGroup ibusGroup = this.ibusGroupService.findById(Integer.parseInt(groupId));
			if(ibusGroup!=null){
				model.addAttribute("file_url",ibusGroup.getFileUrl());
			}
		}
		model.addAttribute("groupId", groupId);
		model.addAttribute("port", port);
		return "system/GroupImage";
	}
	
	@RequestMapping("/chart")
	public String chart(HttpServletRequest request,Model model){
		String nodeId = request.getParameter("nodeId");
		String port = request.getParameter("port");
		String nodeAddr = request.getParameter("nodeAddr");
		String gatewayId = request.getParameter("gatewayId");
		String name = request.getParameter("name");
		try {
			name=java.net.URLDecoder.decode(name,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IbusNode ibusNode = this.ibusNodeService.findById(Integer.parseInt(nodeId));
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endTime = sdf.format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		String startTime = sdf.format(cal.getTime());*/
		
		Date endTime=new Date();
		Date startTime=DateUtils.addHour(endTime, -1);
		model.addAttribute("endTime",DateUtils.formatDateToString(endTime, "yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("startTime",DateUtils.formatDateToString(startTime, "yyyy-MM-dd HH:mm:ss"));
		
		
		model.addAttribute("name", name);
		model.addAttribute("nodeId", nodeId);
		model.addAttribute("port", port);
		model.addAttribute("nodeAddr", nodeAddr);
		model.addAttribute("gatewayId", gatewayId);
		System.out.println("nodeType2=============="+ibusNode);
		if(ibusNode!=null){
			model.addAttribute("nodeType2",ibusNode.getNodeType2());
		}else{
			model.addAttribute("nodeType2",-1);
		}
		
		System.out.println("nodeAddr:"+nodeAddr+"gatewayId:"+gatewayId);
		return "nodedata/chart";
	}
	
	@RequestMapping("/test")
	public @ResponseBody String test(HttpServletRequest request,Model model){
		System.out.println("---------------tpMax"+Helper.map.get("1_1_tpMax"));
		return "true";
	}
}
