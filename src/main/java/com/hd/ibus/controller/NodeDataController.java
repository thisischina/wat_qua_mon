package com.hd.ibus.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hd.ibus.pojo.GatewayData;
import com.hd.ibus.pojo.GroupData;
import com.hd.ibus.pojo.IbusGateway;
import com.hd.ibus.pojo.IbusGroup;
import com.hd.ibus.pojo.IbusNode;
import com.hd.ibus.pojo.IbusTpvi;
import com.hd.ibus.pojo.NodeData;
import com.hd.ibus.service.IbusGatewayService;
import com.hd.ibus.service.IbusGroupService;
import com.hd.ibus.service.IbusNodeService;
import com.hd.ibus.service.IbusTpviService;
import com.hd.ibus.service.IbusWarnService;
import com.hd.ibus.util.Icon;
import com.hd.ibus.util.activeMQ.AutoUDPPushServiceImpl;
import com.hd.ibus.util.activeMQ.PushService;
import com.hd.ibus.util.dataUtil.GatewayMQListenerThread;
import com.hd.ibus.util.dataUtil.GatewayUDPListenerThread;
import com.hd.ibus.util.dataUtil.UDPInitServlet;

@Controller
@RequestMapping("/main")
public class NodeDataController {
	
	@Resource
	private IbusGatewayService ibusGatewayService;
	
	@Resource
	private IbusGroupService ibusGroupService;
	
	@Resource
	private IbusNodeService ibusNodeService;
	
	@Resource
	private IbusTpviService ibusTpviService;
	
	@Resource
	private IbusWarnService ibusWarnService;

	@RequestMapping("/findList")
	public @ResponseBody Map<String,Object> findList(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		int count = 0;
		int port = 0;
		int node_id = 1;
		int node_addr = 1;
		int gateway_id = 1;
		String name = "";
		//取出所有网关
		List<IbusGateway> gatewayList = this.ibusGatewayService.findAll();
		//用来存储所有网关信息
		List<GatewayData> gateList = new ArrayList<GatewayData>();
		for(int i=0;i<gatewayList.size();i++){
			IbusGateway ibusGateway = gatewayList.get(i);
			//给网关赋值
			GatewayData gatewayData = new GatewayData();
			gatewayData.setGatewayId(""+ibusGateway.getId());
			gatewayData.setName(ibusGateway.getGatewayName());
			gatewayData.setOpen(true);
			gatewayData.setIcon(Icon.ICON6);
			//将所属与该网关的群组搜索出来
			List<IbusGroup> groupList = this.ibusGroupService.findListByGatewayId(ibusGateway.getId());
			//搜索出来的群组将塞入gpList，作为网关的children
			List<GroupData> gpList = new ArrayList<GroupData>();
			for(int j=0;j<groupList.size();j++){
				IbusGroup ibusGroup = groupList.get(j);
				GroupData groupData = new GroupData();
				groupData.setType("group");
				//groupData.setFileUrl(ibusGroup.getFileUrl());
				groupData.setGroupId(""+ibusGroup.getId());
				groupData.setName(ibusGroup.getGroupName());
				groupData.setOpen(true);
				groupData.setIcon(Icon.ICON7);
				groupData.setPort(ibusGateway.getGatewayPort()+"");
				//将所属与该群组的节点搜索出来
				List<IbusNode> nodeList = this.ibusNodeService.findListByGroupId(ibusGroup.getId());
				List<NodeData> nodeDatas = new ArrayList<NodeData>();
				if(nodeList.size()>0&&count==0){
					node_id = nodeList.get(0).getId();
					node_addr = nodeList.get(0).getNodeAddress();
					port = ibusGateway.getGatewayPort();
					gateway_id = ibusGateway.getId();
					name = nodeList.get(0).getNodeName();
					count++;
				}
				for(int k=0;k<nodeList.size();k++){
					IbusNode ibusNode = nodeList.get(k);
					NodeData nodeData = new NodeData();
					nodeData.setType("node");
					nodeData.setGatewayId(ibusGateway.getId()+"");
					nodeData.setNodeId(ibusNode.getId()+"");
					nodeData.setName(ibusNode.getNodeName());
					nodeData.setNodeAddress(ibusNode.getNodeAddress()+"");
					nodeData.setPort(ibusGateway.getGatewayPort()+"");
					nodeDatas.add(nodeData);
				}
				groupData.setChildren(nodeDatas);
				gpList.add(groupData);
			}
			gatewayData.setChildren(gpList);
			gateList.add(gatewayData);
		}
		System.out.println("node_id:"+node_id+"node_addr:"+node_addr+"port:"+port);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("node_id", node_id);
		map.put("node_addr", node_addr);
		map.put("gateway_id", gateway_id);
		map.put("port", port);
		map.put("list", gateList);
		map.put("name", name);
		return map;
	}
	
	/**
	 * 根据nodeId查询温度电流电压表（ibus_tpvi）
	 * 返回前台构成曲线图
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findChartData")
	public @ResponseBody Map findChartData(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String nodeId = request.getParameter("nodeId");
		String gatewayId = request.getParameter("gatewayId");
		Map<String,Object> map = new HashMap<String,Object>();
		if(startTime!=null&&startTime!=""&&endTime!=null&&endTime!=""){
			map.put("startTime", startTime);
			map.put("endTime", endTime);
		}
		map.put("nodeId", nodeId);
		map.put("gatewayId", gatewayId);
		return this.ibusTpviService.findAllById(map); 
	}
	
	@RequestMapping("/findOneById")
	public @ResponseBody IbusTpvi findOneById(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		int nodeId = Integer.parseInt(request.getParameter("nodeId"));
		int gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nodeId", nodeId);
		map.put("gatewayId", gatewayId);
		return this.ibusTpviService.findOneById(map); 
	}
	
}
