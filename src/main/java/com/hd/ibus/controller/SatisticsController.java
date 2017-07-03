package com.hd.ibus.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date; 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.hd.ibus.pojo.IbusGateway;
import com.hd.ibus.mapper.IbusSatisticsMapper;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusGatewayService;
import com.hd.ibus.service.SatisticsService;
import com.hd.ibus.util.DateUtils;

@Controller
@RequestMapping("statistics")
public class SatisticsController  {
	
	@Resource
	private SatisticsService satisticsService;

	
	@Resource
	private IbusGatewayService ibusGatewayService;
	

	@Resource
	private IbusSatisticsMapper satisticsMapper;

	@RequestMapping("/report")
	public String report(HttpServletRequest request,Model model){
		
		return "statistics/report";
	}
	@RequestMapping("/alarmData")
	public String alarmData(HttpServletRequest request,Model model){
		
		Date enddate=new Date();
		//String startdate=DateUtils.addMonth(enddate, -1, "yyyy-MM-dd HH:mm:ss");
		Date startdate=DateUtils.addHour(enddate, -1);
		model.addAttribute("enddate",DateUtils.formatDateToString(enddate, "yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("startdate",DateUtils.formatDateToString(startdate, "yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("gateway",this.satisticsService.findAllGateway());
		
		return "statistics/alarmData";
	}
	
	@RequestMapping("/monitorData")
	public String monitorData(HttpServletRequest request,Model model){
		Date enddate=new Date();
		//String startdate=DateUtils.addMonth(enddate, -1, "yyyy-MM-dd HH:mm:ss");
		Date startdate=DateUtils.addHour(enddate, -1);
		model.addAttribute("enddate",DateUtils.formatDateToString(enddate, "yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("startdate",DateUtils.formatDateToString(startdate, "yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("gateway",this.satisticsService.findAllGateway());
		return "statistics/monitorData";
	}
	
	@RequestMapping("/monitorData2")
	public String monitorData2(HttpServletRequest request,Model model){
		List<IbusGateway> list = this.ibusGatewayService.findAll();
		model.addAttribute("list",list);
		return "statistics/monitorData2";
	}
	
	
	@RequestMapping("/findAllGroup")
	public @ResponseBody List<Map> findAllGroup(HttpServletRequest request,Model model,int gateway_id){
		return this.satisticsService.findAllGroup(gateway_id);
	}
	
	@RequestMapping("/findAllGateway")
	public List<Map> findAllGateway(HttpServletRequest request,Model model){
		return this.satisticsService.findAllGateway();
	}
	
	@RequestMapping("/findAllNode")
	public @ResponseBody List<Map> findAllNode(HttpServletRequest request,Model model,int group_id){
		return this.satisticsService.findAllNode(group_id);
	}
	//分页查询监测数据
	@RequestMapping("/dataGrid")
	public @ResponseBody DataGridResultInfo nodePageData(HttpServletRequest request,Model model) throws Exception {
		System.out.println("request.getParameter(pageNow):"+request.getParameter("pageNow"));
		Integer pageNow =("".equals(request.getParameter("pageNow"))||request.getParameter("pageNow")==null)?1:Integer.parseInt(request.getParameter("pageNow"));
		//Integer pageSize =!"".equals(request.getParameter("pageSize"))?5:Integer.parseInt(request.getParameter("pageSize"));
		
		System.out.println("pageNow:"+pageNow);
		Integer pageSize =10;
		String nodeAddress =request.getParameter("nodeAddress");
		String gatewayid =request.getParameter("gatewayid");
		String startdate =request.getParameter("startdate");
		String enddate =request.getParameter("enddate");
		System.out.println("pageNow"+pageNow+"pageSize"+pageSize+"nodeAddress"+nodeAddress+"gatewayid"+gatewayid+"datatime"+",startdate:"+startdate+",enddate:"+enddate);
		System.out.println(satisticsService);
		return this.satisticsService.nodePageData(pageNow, pageSize, nodeAddress, gatewayid, startdate,enddate); 
	}
	//分页查询报警数据
	@RequestMapping("/dataGrid1")
	public @ResponseBody DataGridResultInfo warnPageData(HttpServletRequest request,Model model) throws Exception {
		Integer pageNow =("".equals(request.getParameter("pageNow"))||request.getParameter("pageNow")==null)?1:Integer.parseInt(request.getParameter("pageNow"));
		//Integer pageSize ="".equals(request.getParameter("pageSize"))?5:Integer.parseInt(request.getParameter("pageSize"));
		Integer pageSize =10;
		String nodeAddress =request.getParameter("nodeAddress");
		String gatewayid =request.getParameter("gatewayid");
		String startdate =request.getParameter("startdate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(startdate)); 
		cal.add(Calendar.HOUR, 1);
		Date date = cal.getTime();
		String enddate = sdf.format(date);
		System.out.println(startdate+"-------------"+enddate);
//		String enddate =request.getParameter("enddate");
		System.out.println("pageNow"+pageNow+"pageSize"+pageSize+"nodeAddress"+nodeAddress+"gatewayid"+gatewayid+"datatime"+",startdate:"+startdate+",enddate:"+enddate);
		System.out.println(satisticsService);
		return this.satisticsService.warnPageData(pageNow, pageSize, nodeAddress, gatewayid, startdate,enddate); 
	}
	
	
	//预警的节点页面
	@RequestMapping("/alarmNode")
	public String alarmNode(HttpServletRequest request,Model model){
		Integer count =satisticsMapper.findCount();
		model.addAttribute("count", count);
		return "statistics/alarmNode";
	}
	
	//预警的节点列表页面
		@RequestMapping("/alarmNodeContent")
		public String alarmNodeContent(HttpServletRequest request,Model model){
			model.addAttribute("gateway",this.satisticsService.findAllGateway());
			return "statistics/alarmNodeContent";
		}
	//所有预警节点列表
		@RequestMapping("/findAllWarn")
		public @ResponseBody DataGridResultInfo findAllWarn(HttpServletRequest request,Model model) throws Exception {
			Integer pageNow =("".equals(request.getParameter("pageNow"))||request.getParameter("pageNow")==null)?1:Integer.parseInt(request.getParameter("pageNow"));
			//Integer pageSize ="".equals(request.getParameter("pageSize"))?5:Integer.parseInt(request.getParameter("pageSize"));
			Integer pageSize =10;
			String gatewayid =request.getParameter("gatewayid");
			return this.satisticsService.findAllWarn(pageNow, pageSize, gatewayid); 
		}	
    //更新信息状态
		@RequestMapping("/updateState")
		public @ResponseBody String updateState(HttpServletRequest request,Model model,Integer id) throws Exception {
			return this.satisticsService.updateState(id); 
		}
		@RequestMapping("/findCount")
		public @ResponseBody Integer findCount(HttpServletRequest request,Model model,Integer id) throws Exception {
			Integer count =satisticsService.findCount();
			//System.out.println("预警监测数据----findCount："+count);
			return count;
		}
		
}
