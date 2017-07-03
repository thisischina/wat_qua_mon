package com.hd.ibus.controller;

import java.io.UnsupportedEncodingException;
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
import com.hd.ibus.pojo.IbusOperation;
import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.service.IbusGatewayService;
import com.hd.ibus.service.IbusNodeService;
import com.hd.ibus.service.IbusOperationService;
import com.hd.ibus.util.dataUtil.GatewayUDPListenerThread;
import com.hd.ibus.util.dataUtil.Mosaic;
import com.hd.ibus.util.dataUtil.UDPInitServlet;

/**
 * 控制、设置节点的controller
 * 
 * @author zhangjunxian
 * 
 */
@Controller
@RequestMapping("control")
public class controlController {

	@Resource
	private IbusNodeService ibusNodeService;
	@Resource
	private IbusGatewayService ibusGatewayService;
	@Resource
	private IbusOperationService ibusOperationService;
	
	// 控制台主页
	@RequestMapping("/main")
	public String main(HttpServletRequest request, Model model) {

		return "control/main";
	}

	// 控制台主页
	@RequestMapping("/control")
	public String control(HttpServletRequest request, Model model) {
		List<IbusGateway> gateways = this.ibusGatewayService.findAll();
		model.addAttribute("gateways", gateways);
		return "control/control";
	}
		
	// 网关下的节点内容页
	@RequestMapping("/nodeControlPage")
	public String nodeControlPage(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		//gatewayPort='+gatewayPort+"&id="+id+"&gatewayIp="+gatewayIp+"&nodeCount="+nodeCount)
		model.addAttribute("gatewayPort", request.getParameter("gatewayPort"));
		model.addAttribute("gatewayId", request.getParameter("gatewayId"));
		model.addAttribute("gatewayIp", request.getParameter("gatewayIp"));
		model.addAttribute("nodeCount", request.getParameter("nodeCount"));
		
		
		
		String name = request.getParameter("gatewayName");
		name = java.net.URLDecoder.decode(name,"utf-8");
		model.addAttribute("gatewayName",name );
		System.out.println(name);
		
		
		return "control/nodeControl";
	}
	
	// 网关下的节点内容页
		@RequestMapping("/gatewayControlPage")
		public String gatewayControlPage(HttpServletRequest request, Model model) {
			//gatewayPort='+gatewayPort+"&id="+id+"&gatewayIp="+gatewayIp+"&nodeCount="+nodeCount)
			model.addAttribute("gatewayPort", request.getParameter("gatewayPort"));
			model.addAttribute("gatewayId", request.getParameter("gatewayId"));
			model.addAttribute("gatewayIp", request.getParameter("gatewayIp"));
			model.addAttribute("nodeCount", request.getParameter("nodeCount"));
			model.addAttribute("gatewayName", request.getParameter("gatewayName"));
			
			return "control/gatewayControl";
		}
	
	
	

	// 网关群组控制
	@RequestMapping("/gatewayConfig")
	public String gatewayConfig(HttpServletRequest request,Model model){
		String gatewayId = request.getParameter("gatewayId");
		String port = request.getParameter("port");
		String nodeCount = request.getParameter("nodeCount");
		model.addAttribute("gatewayId", gatewayId);
		model.addAttribute("port", port);
		model.addAttribute("nodeCount",nodeCount);

		return "control/gatewayConfig";
	}

	// 根据网关查询节点信息
	@RequestMapping("/getNodeByGateway")
	public @ResponseBody
	List getNodeByGateway(HttpServletRequest request, Model model, int gatewayId) {

		List<Map> list = ibusNodeService.getNodeByGateway(gatewayId);
		
		
		return list;
	}

	// 单节点控制、设置
	@RequestMapping("/nodeControl")
	public @ResponseBody
	String nodeControl(HttpServletRequest request, Model model)
			throws InterruptedException {

		// 单节点 开：on、关：off、开关状态：state、复位：reset、设置阀值：threshold、读数据：getData
		// 参数：网关端口：port、节点地址：address、操作类型：type、参数；param
		GatewayUDPListenerThread gt = null;

		
		
		
		try {
			int port_state_count = 0;
			while(!Mosaic.port_state){
				port_state_count++;
				Thread.sleep(200);
			
				//线程10秒未停止算超时，重新启动线程，返回outTime
				if (port_state_count == 50) {
					//端口被占用outTime1
					return "outTime1";
				}
			}
			
			Mosaic.port_state=false;
			
			String port = request.getParameter("port");
			String oneMore = request.getParameter("oneMore");
			String param = request.getParameter("param");
			String type = request.getParameter("type");
			String nodeAddr = request.getParameter("nodeAddr");
			String nodeType = request.getParameter("nodeType");
			System.out.println("、、、、、、、、、、、、、、、、、、port:"+port+",param"+param+","+",type:"+type+",nodeAddr:"+nodeAddr+"，nodeType:"+nodeType);
			gt = (GatewayUDPListenerThread) UDPInitServlet.threadMap
					.get("gateway" + port);
			//判断该端口的监听线程是否存在
			if (gt != null) {
				//线程运行状态量设置为false,释放改socket占用的端口
				gt.working = false;
				int i = 0;
				//通过线程状态量判断线程释放停止
				while (gt.state) {
					i++;
					Thread.sleep(200);
					System.out.println("gt.state+" + gt.state);
					//线程10秒未停止算超时，重新启动线程，返回outTime
					if (i == 30) {
						//释放端口
						Mosaic.port_state=true;
						gt.working = true;
						//UDPInitServlet.threadPool.execute(gt);
						
						return "outTime";
					}
				}
				if(nodeAddr.length()==1){
					nodeAddr = "0"+nodeAddr;
				}
				String result = Mosaic.mosaicSendWords(nodeAddr + nodeType, type,
						oneMore, Integer.parseInt(port), param,null);
				if("".equals(result)||result==null||"error".equals(result)){
					Mosaic.port_state=true;
					gt.working = true;
					Thread.sleep(100);
					UDPInitServlet.threadPool.execute(gt);
					return "error1";
				}
				
				Mosaic.port_state=true;
				gt.working = true;
				Thread.sleep(100);
				UDPInitServlet.threadPool.execute(gt);
				
				return result;
			}else{
				Mosaic.port_state=true;
				// 返回操作结果
				return "Thread null";
			}
			
		} catch (Exception e) {
			Mosaic.port_state=true;
			gt.working = true;
			Thread.sleep(100);
			if(gt!=null){
				UDPInitServlet.threadPool.execute(gt);
			}
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * 多节点控制、设置  
	 * 下发地址、复位、开、关
	 * 参数：网关端口 port、节点地址  address、操作类型 type、参数 param
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping("gatewayControl")
	public @ResponseBody String gatewayControl(HttpServletRequest request,
			Model model) throws InterruptedException { 

		GatewayUDPListenerThread gt = null;
		
		
		try {
			int port_state_count = 0;
			while(!Mosaic.port_state){
				port_state_count++;
				Thread.sleep(200);
			
				//线程10秒未停止算超时，重新启动线程，返回outTime
				if (port_state_count == 50) {
					return "outTime3";
				}
			}
			
			Mosaic.port_state=false;
		
			//var data ={port:port,oneMore:oneMore,param:param,type:type,nodeAddr:nodeAddr};
			String nodeCount = request.getParameter("nodeCount");
			String port=request.getParameter("port");
			String oneMore=request.getParameter("oneMore");
			String param=request.getParameter("param");
			String type=request.getParameter("type");
			String nodeAddr=request.getParameter("nodeAddr");
			String str = "port:"+port+",oneMore:"+oneMore+",param:"+param+",type:"+type+",nodeAddr:"+nodeAddr;
			System.out.println(str);
			gt = (GatewayUDPListenerThread) UDPInitServlet.threadMap
					.get("gateway" + port);
			//判断该端口的监听线程是否存在
			if (gt != null) {
				//线程运行状态量设置为false,释放改socket占用的端口
				gt.working = false;
				int i = 0;
				//通过线程状态量判断线程释放停止
				while (gt.state) {
					i++;
					Thread.sleep(200);
					System.out.println("gt.state+" + gt.state);
					//线程10秒未停止算超时，重新启动线程，返回outTime
					if (i == 30) {
						Mosaic.port_state=true;
						gt.working = true;
						//UDPInitServlet.threadPool.execute(gt);
						return "outTime";
					}
				}
				if(nodeCount.length()<2){
					nodeCount = "0"+nodeCount;
				}
				System.out.println(nodeCount);
				System.out.println("****************************type:"+nodeCount+"04"+",oneMore:"+oneMore+",port:"+port+",nodeAddr:"+nodeAddr);
				//String adada2=Mosaic.mosaicSendWords("0004", "80", "more", 9001 ,"03,01");
				String result = Mosaic.mosaicSendWords(nodeCount+"04"  , type,oneMore, Integer.parseInt(port), nodeAddr,param);
				if("".equals(result)||result==null||"error".equals(result)||"error2".equals(result)){
					
					Mosaic.port_state=true;
					gt.working = true;
					UDPInitServlet.threadPool.execute(gt);
					return "error1";
				}
				Mosaic.port_state=true;
				gt.working = true;
				Thread.sleep(100);
				UDPInitServlet.threadPool.execute(gt);
				return result;
			}else{
				Mosaic.port_state=true;
				return "Threadnull";
			}
		} catch (Exception e) {
			Mosaic.port_state=true;
			gt.working = true;
			Thread.sleep(100);
			if(gt!=null){
				UDPInitServlet.threadPool.execute(gt);
			}
			e.printStackTrace();
			return "error";
		}
	}
}
