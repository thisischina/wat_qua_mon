package com.hd.ibus.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hd.ibus.util.activeMQ.AutoUDPPushServiceImpl;
import com.hd.ibus.util.activeMQ.PushService;
import com.hd.ibus.util.dataUtil.Commns;
import com.hd.ibus.util.dataUtil.GatewayUDPListenerThread;
import com.hd.ibus.util.dataUtil.Switch;
import com.hd.ibus.util.dataUtil.UDPInitServlet;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	Logger logger = Logger.getLogger(DemoController.class);
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model){
		return "demo/index";
	}
	@RequestMapping("/index7l")
	public String index7l(HttpServletRequest request,Model model){
		return "demo/index7l";
	}
	
	@RequestMapping("/index2")
	public String index2(HttpServletRequest request,Model model){
		return "demo/index2";
	}
	
	@RequestMapping("/index3")
	public String index3(HttpServletRequest request,Model model){
		
		return "demo/index3";
	}
	
	@RequestMapping("/index4")
	public String index4(HttpServletRequest request,Model model){
		
		return "demo/index4";
	}
	
	//节点控制
	@RequestMapping("/index5")
	public String index5(HttpServletRequest request,Model model){
		
		return "demo/index5";
	}
	
	//群组控制
	@RequestMapping("/index6")
	public String index6(HttpServletRequest request,Model model){
		
		return "demo/index6";
	}
	
	//统计报表
	@RequestMapping("/index7")
	public String index7(HttpServletRequest request,Model model){
		
		return "demo/index7";
	}
	//监测数据
	@RequestMapping("/monitorData")
	public String monitorData(HttpServletRequest request,Model model){
		
		return "statistics/monitorData";
	}
	
	//预警数据
	@RequestMapping("/alarmData")
	public String alarmData(HttpServletRequest request,Model model){
		
		return "statistics/monitorData";
	}
	
	@RequestMapping("/chart")
	public String chart(HttpServletRequest request,Model model){
		
		return "system/chart";
	}
	
	@RequestMapping("/gateway")
	public String gateway(HttpServletRequest request,Model model){
		
		return "system/gateway/gateway";
	}
	
	@RequestMapping("/project")
	public String project(HttpServletRequest request,Model model){
		
		return "system/project/project";
	}
	
	@RequestMapping("/group")
	public String group(HttpServletRequest request,Model model){
		
		return "system/group/group";
	}
	
	@RequestMapping("/operation")
	public String operation(HttpServletRequest request,Model model){
		return "system/operation/operation";
	}
	
	@RequestMapping("/node")
	public String node(HttpServletRequest request,Model model){
		String addChange = request.getParameter("addChange");
		String gatewayId = request.getParameter("gatewayId");
		String nodeAddr = request.getParameter("nodeAddr");
		if(gatewayId!=null){
			model.addAttribute("gatewayId", Integer.parseInt(gatewayId));
		}
		if(nodeAddr!=null){
			model.addAttribute("nodeAddr", Integer.parseInt(nodeAddr));
		}
		model.addAttribute("addChange", addChange);
		return "system/node/node";
	}

	@RequestMapping("top")
	public String top(HttpServletRequest request,Model model){
		
		return "home/top";
	}
	@RequestMapping("left")
	public String left(HttpServletRequest request,Model model){
		
		return "home/left";
	}
	@RequestMapping("main")
	public String main(HttpServletRequest request,Model model){
		
		return "home/main";
	}
	
	
	@RequestMapping("testThread")
	public String testThread(HttpServletRequest request,Model model){
		
		return "home/testThread";
	}
	//打开UDP监听
	@RequestMapping("startGetStill")
	public @ResponseBody String startGetStill(HttpServletRequest request,Model model){
		
		try {
			
			GatewayUDPListenerThread get1 = (GatewayUDPListenerThread) UDPInitServlet.threadMap.get("gateway9001");
			if(get1!=null){
				if(get1.state==true){
					
				}else{
					get1.working=true;
					UDPInitServlet.threadPool.execute(get1);
				}
				
			}else{
				ServletContext sc = request.getServletContext();
				WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
				
				/*PushService pushService = (AutoUDPPushServiceImpl)ac.getBean("pushService");*/
				
				//get1 = new GatewayUDPListenerThread(9001,"",pushService,null,null,null,null);
				//UDPInitServlet.threadMap.put("gateway9001", get1);
				//UDPInitServlet.threadPool.execute(get1);
			}
			
			System.out.println("################################# UDPInitServlet.threadPool.execute(gateway9001);");
			logger.info("UDPInitServlet.threadPool.execute(get1);");
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
	//停止UDP监听
	@RequestMapping("stopGetStill")
	public @ResponseBody String stopGetStill(HttpServletRequest request,Model model){
		System.out.println("stop GetStillThread����"+Commns.still_state);
		try {
			GatewayUDPListenerThread gt = (GatewayUDPListenerThread) UDPInitServlet.threadMap.get("gateway9001");
			System.out.println(gt.toString());
			if(gt!=null){
				gt.working=false;
			}
			int i=0;
			while(gt.state){
				i++;
				Thread.sleep(300);
				System.out.println("gt.state+"+gt.state);
				if(i==20){
					gt.working=true;
					UDPInitServlet.threadPool.execute(gt);
					System.out.println("###################  gt stop------outTime");
					return "outTime";
				}
			}
			System.out.println("###################  gt working ==false;------stop");
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	// 下发节点地址
	@RequestMapping("setNodeAdress")
	public @ResponseBody String setNodeAdress(HttpServletRequest request,
			Model model) {
		try {
			GatewayUDPListenerThread gt = (GatewayUDPListenerThread) UDPInitServlet.threadMap.get("gateway9001");
			
			if(gt!=null){
				//ֹͣ���ո����UDP����߳�
				gt.working=false;
				int i=0;
				while(gt.state){
					i++;
					Thread.sleep(300);
					System.out.println("gt.state+"+gt.state);
					if(i==20){
						gt.working=true;
						UDPInitServlet.threadPool.execute(gt);
						return "outTime";
					}
				}
				System.out.println("#################################### gt.state+"+gt.state);
				
				//ִ����Ҫ�Ĳ�ѯ����
				String message = Switch.setNodeAdress();
				System.out.println("get switchState OK ====" + message);
				
				//���������ո����UDP����߳�
				gt.working=true;
				System.out.println("#################################### gt.working true:" + gt.working);
				Thread.sleep(100);
				UDPInitServlet.threadPool.execute(gt);
				System.out.println("####################################  UDPInitServlet.threadPool.execute(gt);" );
				
				return message;
			}else{
				return "error ";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	
	//查询节点开关的状态
	@RequestMapping("switchStateNew")
	public @ResponseBody String switchStateNew(HttpServletRequest request,
			Model model) {
		System.out.println("get switchState����");
		try {
			GatewayUDPListenerThread gt = (GatewayUDPListenerThread) UDPInitServlet.threadMap.get("gateway9001");
			
			if(gt!=null){
				//设置线程停止标识为false
				gt.working=false;
				int i=0;
				while(gt.state){
					i++;
					Thread.sleep(300);
					System.out.println("gt.state+"+gt.state);
					if(i==20){
						gt.working=true;
						UDPInitServlet.threadPool.execute(gt);
						return "outTime";
					}
				}
				System.out.println("#################################### gt.state+"+gt.state);
				System.out.println("#################################### gt.working false:" + gt.working);
				
				//调用查询节点开关状态的方法
				String message = Switch.getswitchState("68 01 61 06 00 D0 16");
				System.out.println("get switchState OK ====" + message);
				
				//查询完毕，启动线程
				gt.working=true;
				System.out.println("#################################### gt.working true:" + gt.working);
				Thread.sleep(100);
				UDPInitServlet.threadPool.execute(gt);
				System.out.println("####################################  UDPInitServlet.threadPool.execute(gt);" );
				
				return message;
			}else{
				return "error ";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	
	@RequestMapping("switchStart")
	public @ResponseBody String switchStart(HttpServletRequest request,Model model) {
		System.out.println("get switchStart����");
		try {
			GatewayUDPListenerThread gt = (GatewayUDPListenerThread) UDPInitServlet.threadMap.get("gateway9001");
			
			if(gt!=null){
				//ֹͣ���ո����UDP����߳�
				gt.working=false;
				int i=0;
				while(gt.state){
					i++;
					Thread.sleep(300);
					System.out.println("gt.state+"+gt.state);
					if(i==20){
						gt.working=true;
						UDPInitServlet.threadPool.execute(gt);
						return "outTime";
					}
				}
				System.out.println("#################################### gt.state+"+gt.state);
				System.out.println("#################################### gt.working false:" + gt.working);
				
				//ִ����Ҫ�Ĳ�ѯ����
				String message = Switch.switchStart();
				System.out.println("get switchStart OK ====" + message);
				
				//���������ո����UDP����߳�
				gt.working=true;
				System.out.println("#################################### gt.working true:" + gt.working);
				Thread.sleep(100);
				UDPInitServlet.threadPool.execute(gt);
				System.out.println("####################################  UDPInitServlet.threadPool.execute(gt);" );
				
				return message;
			}else{
				return "error ";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	
	// 关闭节点开关
	@RequestMapping("switchStop")
	public @ResponseBody String switchStop(HttpServletRequest request,
			Model model) {
		System.out.println("get switchStop����");
		
		try {
			GatewayUDPListenerThread gt = (GatewayUDPListenerThread) UDPInitServlet.threadMap.get("gateway9001");
			
			if(gt!=null){
				//ֹͣ���ո����UDP����߳�
				gt.working=false;
				int i=0;
				while(gt.state){
					i++;
					Thread.sleep(300);
					System.out.println("gt.state+"+gt.state);
					if(i==20){
						gt.working=true;
						UDPInitServlet.threadPool.execute(gt);
						return "outTime";
					}
				}
				System.out.println("#################################### gt.state+"+gt.state);
				System.out.println("#################################### gt.working false:" + gt.working);
				
				//ִ����Ҫ�Ĳ�ѯ����
				String message = Switch.switchStop();
				System.out.println("get switchStop OK ====" + message);
				
				//���������ո����UDP����߳�
				gt.working=true;
				System.out.println("#################################### gt.working true:" + gt.working);
				Thread.sleep(100);
				UDPInitServlet.threadPool.execute(gt);
				System.out.println("####################################  UDPInitServlet.threadPool.execute(gt);" );
				
				return message;
			}else{
				return "error ";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
