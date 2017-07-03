package com.hd.ibus.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.ibus.pojo.IbusNode;
import com.hd.ibus.pojo.IbusTpvi;
import com.hd.ibus.pojo.vo.IbusTpviVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusNodeService;
import com.hd.ibus.service.IbusTpviService;
import com.hd.ibus.util.DateUtils;
import com.hd.ibus.util.Helper;
import com.hd.ibus.util.dataUtil.CreateTable;

@Controller
@RequestMapping("tpvi")
public class TpviController {
	@Resource
	private IbusTpviService ibusTpviService;
	
	@Resource
	private IbusNodeService ibusNodeService;

	@RequestMapping("/directUpdateNode")
	public @ResponseBody String directUpdateNode(HttpServletRequest request,HttpServletResponse response){
		Date date = new Date();
		date = DateUtils.addDay(date, -1);
		Map<String,Object> map = new HashMap<String,Object>();
		String tableName = "ibus_tpvi_"+9001+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
		map.put("acTime", "'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date)+"'"); 
    	map.put("gatewayId", 1);
    	map.put("nodeId", 1);
    	map.put("tp1", 21.1);
    	map.put("tableName", tableName);
		return this.ibusTpviService.saveTpvi2(map);
	}
	
	@RequestMapping("/findOneByTable")
	public @ResponseBody Map findOneByTable(HttpServletRequest request,HttpServletResponse response){
		String port = request.getParameter("port");
		Integer gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
		Integer nodeId = Integer.parseInt(request.getParameter("nodeId"));
		Date date = new Date();
		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("gatewayId", gatewayId);
    	map.put("nodeId", nodeId);
		Map ibusTpvi = null;
		String tableName = "ibus_tpvi_"+port+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
		System.out.println("tableName:"+tableName);
		if(Helper.map.get(tableName)!=null){
			map.put("tableName", tableName);
			ibusTpvi = this.ibusTpviService.findOne(map);
		}
		int count = 0;
		while(ibusTpvi==null){
			if(count==10){
				return null;
			}
			date = DateUtils.addDay(date, -1);
			tableName = "ibus_tpvi_"+port+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
			Object obj = Helper.map.get(tableName);
			System.out.println("obj:"+obj);
			while(obj==null){
				if(count==10){
					return null;
				}
				date = DateUtils.addDay(date, -1);
	    		tableName = "ibus_tpvi_"+port+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
	    		obj = Helper.map.get(tableName);
	    		count++;
			}
    		map.put("tableName", tableName);
    		ibusTpvi = this.ibusTpviService.findOne(map);
    	}
    	return ibusTpvi;
	}
	
	@RequestMapping("/findStateByTable")
	public @ResponseBody DataGridResultInfo findStateByTable(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		String port = request.getParameter("port");
		Integer gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
		List<Map> nodeList = this.ibusNodeService.findNodeByGatewayId(gatewayId);
//		if(pageNow==null){
//			pageNow=1;
//		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
		String tableName = "ibus_tpvi_"+port+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
		if(Helper.map.get(tableName)==null){
			for(int i=0;i<nodeList.size();i++){
				Map<String,Object> aMap = new HashMap<String,Object>();
				aMap.put("nodeName", nodeList.get(i).get("node_name"));
				aMap.put("nodeAddress", nodeList.get(i).get("node_address")); 
				aMap.put("type", "false");
				returnList.add(aMap);
			}
			
		}else{
			Map<String,Object> pMap = new HashMap<String,Object>();
			pMap.put("tableName", tableName);
			List<Map> tpviList= this.ibusTpviService.findAllMaxTime(pMap);
			for(int i=0;i<nodeList.size();i++){
				Map<String,Object> aMap = null;
				for(int j=0;j<tpviList.size();j++){
					if(nodeList.get(i).get("node_address")==tpviList.get(j).get("node_id")){
						aMap = new HashMap<String,Object>();
						aMap.put("tpvi", tpviList.get(j)); 
						String acTime = (String) tpviList.get(j).get("acTime");
						Calendar sdate = Calendar.getInstance();
						sdate.setTime(sdf.parse(acTime));
						sdate.set(Calendar.MINUTE, sdate.get(Calendar.MINUTE)+5);
						long stime = sdate.getTimeInMillis();
						sdate.setTime(new Date());
						long nowTime = sdate.getTimeInMillis();
						if(stime<nowTime){
							aMap.put("type", "false");
						}else{
							aMap.put("type", "true");
						}
						aMap.put("nodeName", nodeList.get(i).get("node_name"));
						aMap.put("nodeAddress", nodeList.get(i).get("node_address"));
						returnList.add(aMap);
						break;
					}
					
				}
				if(aMap!=null){
					continue;
				}
				aMap = new HashMap<String,Object>();
				aMap.put("nodeName", nodeList.get(i).get("node_name"));
				aMap.put("nodeAddress", nodeList.get(i).get("node_address"));
				aMap.put("type", "false");
				returnList.add(aMap);
			}
			
			
			
		}
		/*for(int i=returnList.size();i>pageNow*10;i--){
			returnList.remove(i);
		}
		if(pageNow>1){
			for(int i=(pageNow-1)*10-1;i>=0;i--){
				returnList.remove(i);
			}
		}*/
		
		
		
		return new DataGridResultInfo(1,returnList);
		
	}
	
	
	@RequestMapping("/findAllByTable")
	public @ResponseBody Map findAllByTable(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Map<String ,Object> map = new HashMap<String ,Object>();
		String startTime = request.getParameter("startTime");
		//String endTime = request.getParameter("endTime");
		String port = request.getParameter("port");
		Integer gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
		Integer nodeId = Integer.parseInt(request.getParameter("nodeId"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(startTime)); 
		cal.add(Calendar.HOUR, 1);
		Date date = cal.getTime();
		String endTime = sdf.format(date);
		System.out.println(startTime+"----"+endTime);
		String tableName = CreateTable.GetTableName(startTime, endTime, port);
		System.out.println("---------tableName:"+tableName);
		if(tableName==null){
			return null;
		}
		map.put("startTime", "'"+startTime+"'");
		map.put("endTime", "'"+endTime+"'");
		map.put("gatewayId", gatewayId);
		map.put("nodeId", nodeId);
		map.put("tableName", tableName);
		if(tableName.length()==0){
			return null;
		}
		return this.ibusTpviService.findAll(map);
	}
	
	@RequestMapping("/findAlls")
	public @ResponseBody DataGridResultInfo findAlls(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Map<String ,Object> map = new HashMap<String ,Object>();
		String startTime = request.getParameter("startTime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(startTime));  
		cal.add(Calendar.HOUR, 1);
		Date date = cal.getTime();
		String endTime = sdf.format(date);
		//String endTime = request.getParameter("endTime");
		System.out.println(startTime+"---------------"+endTime);
		String port = request.getParameter("port");
		Integer gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
		Integer nodeId = Integer.parseInt(request.getParameter("nodeId"));
		int pageNow = 1;
		String page = request.getParameter("pageNow");
		if(page!=null){
			pageNow = Integer.parseInt(page);
		}
		
		int pageSize = 10;
		System.out.println(startTime+"----"+endTime);
		String tableName = CreateTable.GetTableName(startTime, endTime, port);
		System.out.println("tableName:"+tableName);
		if(tableName==null){
			return null;
		}
		System.out.println("pageNow:"+pageNow);
		IbusTpviVo ibusTpviVo = new IbusTpviVo();
		ibusTpviVo.setStartTime("'"+startTime+"'");
		ibusTpviVo.setEndTime("'"+endTime+"'");
		IbusTpvi ibusTpvi = new IbusTpvi();
		ibusTpvi.setGatewayId(gatewayId);
		ibusTpvi.setNodeId(nodeId);
		ibusTpviVo.setIbusTpvi(ibusTpvi);
		ibusTpviVo.setTableName(tableName);
		if(tableName.length()==0){
			return null;
		}
		return this.ibusTpviService.findAlls(ibusTpviVo, pageNow, pageSize); 
	}
	
	@RequestMapping("/findAll")
	public @ResponseBody Map findAll(HttpServletRequest request,HttpServletResponse response){
		String startTime = "2016-10-10 00:00:00";
		String endTime = "2016-11-11 10:10:10";
		String tableName = CreateTable.GetTableName(startTime, endTime,"9001");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", "'"+startTime+"'");
		map.put("endTime", "'"+endTime+"'");
    	map.put("gatewayId", 1);
    	map.put("nodeId", 1);
    	map.put("tp1", 21.1);
    	map.put("tableName", tableName);
		return this.ibusTpviService.findAll(map);
	}

	
	@RequestMapping("/findOne")
	public @ResponseBody Map findOne(HttpServletRequest request,HttpServletResponse response){
		Date date = new Date();
		String tableName = "ibus_tpvi_"+9001+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("gatewayId", 1);
    	map.put("nodeId", 1);
    	map.put("tableName", tableName);
    	Map ibusTpvi = this.ibusTpviService.findOne(map);
    	while(ibusTpvi==null){
    		date = DateUtils.addDay(date, -1);
    		tableName = "ibus_tpvi_"+9001+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
    		map.put("tableName", tableName);
    		ibusTpvi = this.ibusTpviService.findOne(map);
    	}
    	return ibusTpvi;
	}
}
