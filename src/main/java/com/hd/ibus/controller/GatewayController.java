package com.hd.ibus.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hd.ibus.pojo.IbusGateway;
import com.hd.ibus.pojo.IbusOperation;
import com.hd.ibus.pojo.IbusProject;
import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.pojo.vo.IbusGatewayVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusExistService;
import com.hd.ibus.service.IbusGatewayService;
import com.hd.ibus.service.IbusOperationService;
import com.hd.ibus.service.IbusProjectService;
import com.hd.ibus.service.IbusTpviService;
import com.hd.ibus.service.IbusWarnService;

@Controller
@RequestMapping("gateway")
public class GatewayController {

	@Resource
	private IbusProjectService ibusProjectService;
	
	@Resource
	private IbusGatewayService ibusGatewayService;
	
	@Resource
	private IbusTpviService ibusTpviService;
	
	@Resource
	private IbusWarnService ibusWarnService;
	
	@Resource
	private IbusExistService ibusExistService;
	
	@Resource
	private IbusOperationService ibusOperationService;

	@RequestMapping("/directAddGateway")
	public String index2(HttpServletRequest request,Model model){
		DataGridResultInfo dataGridResultInfo=ibusProjectService.findList(null, null, null);
		List<IbusProject> list=dataGridResultInfo.list;
		model.addAttribute("list",list);
		return "system/gateway/addGateway";
	}
	
	@RequestMapping("/directUpdateGateway")
	public String directUpdateGateway(HttpServletRequest request,Model model,Integer id){
		IbusGateway ibusgateway = this.ibusGatewayService.findById(id);
		model.addAttribute(ibusgateway);
		DataGridResultInfo dataGridResultInfo=ibusProjectService.findList(null, null, null);
		List<IbusProject> list=dataGridResultInfo.list;
		model.addAttribute("list",list);
		return "system/gateway/updateGateway";
	}
	
	@RequestMapping("/findList")
	public @ResponseBody DataGridResultInfo findList(HttpServletRequest request,HttpServletResponse response, Integer pageNow,Integer pageSize,IbusGatewayVo ibusGatewayVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		return this.ibusGatewayService.findList(ibusGatewayVo, pageNow, pageSize);
	}
	
	@RequestMapping("/saveIbusGateway")
	public @ResponseBody String saveIbusGateway(HttpServletRequest request,HttpServletResponse response,IbusGatewayVo ibusGatewayVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		String gatewayName = request.getParameter("gatewayName");
		String gatewayIp = request.getParameter("gatewayIp");
		Integer gatewayPort = Integer.parseInt(request.getParameter("gatewayPort"));
		Integer projectId = Integer.parseInt(request.getParameter("projectId"));
		Integer isOnline = Integer.parseInt(request.getParameter("isOnline"));
		Integer nodeCount = Integer.parseInt(request.getParameter("nodeCount"));
		IbusGateway ibusGateway = new IbusGateway();
		ibusGateway.setGatewayName(gatewayName);
		ibusGateway.setGatewayIp(gatewayIp);
		ibusGateway.setGatewayPort(gatewayPort);
		ibusGateway.setIsOnline(isOnline);
		ibusGateway.setProjectId(projectId);
		ibusGateway.setNodeCount(nodeCount);
		ibusGatewayVo.setIbusGateway(ibusGateway);
		ServletContext sc = request.getServletContext();
		WebApplicationContext ac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(sc);
		
		
		return this.ibusGatewayService.saveIbusGateway(ibusGatewayVo,ibusExistService,ibusTpviService,ibusWarnService);
	}
	
	@RequestMapping("/updateIbusGateway")
	public @ResponseBody String updateIbusGateway(HttpServletRequest request,HttpServletResponse response,IbusGatewayVo ibusGatewayVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		String gatewayName = request.getParameter("gatewayName");
		String gatewayIp = request.getParameter("gatewayIp");
		Integer gatewayPort = Integer.parseInt(request.getParameter("gatewayPort"));
		Integer projectId = Integer.parseInt(request.getParameter("projectId"));
		Integer isOnline = Integer.parseInt(request.getParameter("isOnline"));
		Integer nodeCount = Integer.parseInt(request.getParameter("nodeCount"));
		Integer id = Integer.parseInt(request.getParameter("id"));
		IbusGateway ibusGateway = new IbusGateway();
		ibusGateway.setGatewayName(gatewayName);
		ibusGateway.setGatewayIp(gatewayIp);
		ibusGateway.setGatewayPort(gatewayPort);
		ibusGateway.setIsOnline(isOnline);
		ibusGateway.setProjectId(projectId);
		ibusGateway.setNodeCount(nodeCount);
		ibusGateway.setId(id);
		ibusGatewayVo.setIbusGateway(ibusGateway);
		
		
		
		return this.ibusGatewayService.updateIbusGateway(ibusGatewayVo);
	}
	
	@RequestMapping("/deleteIbusGateway")
	public @ResponseBody String deleteIbusGateway(HttpServletRequest request,HttpServletResponse response,Integer id)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		return this.ibusGatewayService.deleteIbusGateway(id,ibusExistService);
	}
	
	@RequestMapping("/findAll")
	public @ResponseBody List<IbusGateway> findAll(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		
		return this.ibusGatewayService.findAll();
	}
	
	@RequestMapping("/testSave")
	public @ResponseBody String TestSave(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		return this.ibusGatewayService.saveIbusGateway2();
	}
	
}
