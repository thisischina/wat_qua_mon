package com.hd.ibus.util.dataUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hd.ibus.pojo.IbusExist;
import com.hd.ibus.pojo.IbusGateway;
import com.hd.ibus.pojo.IbusNode;
import com.hd.ibus.service.IbusTpviService;
import com.hd.ibus.service.impl.IbusExistServiceImpl;
import com.hd.ibus.service.impl.IbusGatewayServiceImpl;
import com.hd.ibus.service.impl.IbusNodeServiceImpl;
import com.hd.ibus.service.impl.IbusTpviServiceImpl;
import com.hd.ibus.service.impl.IbusWarnServiceImpl;
import com.hd.ibus.util.DateUtils;
import com.hd.ibus.util.Helper;

/**
 * 
 * @author zhangjunxian
 * 
 */
public class UDPInitServlet extends HttpServlet {

	public static ExecutorService threadPool = Executors.newCachedThreadPool();
	
	public static ExecutorService receiverThreadPool = Executors.newCachedThreadPool();

	public static Map<String, Object> threadMap = new HashMap<String, Object>();

	public UDPInitServlet() {
		super();
	}

	public void init() throws ServletException {
		System.out.println("####################  UDPInitServlet  init");

		/*ServletContext sc = this.getServletContext();
		WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);

		IbusTpviService ibusTpviService = (IbusTpviServiceImpl) ac.getBean("ibusTpviService");

		IbusGatewayServiceImpl ibusGatewayService = (IbusGatewayServiceImpl) ac.getBean("ibusGatewayService");
		IbusNodeServiceImpl ibusNodeService = (IbusNodeServiceImpl) ac.getBean("ibusNodeService");
		IbusWarnServiceImpl ibusWarnService = (IbusWarnServiceImpl) ac.getBean("ibusWarnService");
		IbusExistServiceImpl ibusExistService = (IbusExistServiceImpl) ac.getBean("ibusExistService");
		
		List<IbusGateway> list = ibusGatewayService.findAll();
		List<IbusNode> nodeList = ibusNodeService.findAll();
		
		Map<String,Object> map = Helper.map;
		if(nodeList!=null){
			for(int i=0;i<nodeList.size();i++){
				IbusNode ibusNode = nodeList.get(i);
				map.put(ibusNode.getGatewayId()+"_"+ibusNode.getNodeAddress()+"_tpMax",ibusNode.getTpMax());
				map.put(ibusNode.getGatewayId()+"_"+ibusNode.getNodeAddress()+"_vMix",ibusNode.getvMix());
				map.put(ibusNode.getGatewayId()+"_"+ibusNode.getNodeAddress()+"_vMax",ibusNode.getvMax());
				map.put(ibusNode.getGatewayId()+"_"+ibusNode.getNodeAddress()+"_iMax",ibusNode.getiMax());
				map.put(ibusNode.getGatewayId()+"_"+ibusNode.getNodeAddress(), ibusNode.getNodeName());
			}
		}
		
		List<IbusExist> existList = ibusExistService.findAll();
		if(existList!=null){
			for(int i=0;i<existList.size();i++){
				map.put(existList.get(i).getTableName(), 1);
			}
		}
		//调用定时器
		if(list!=null){
				try {
					timer4(list,ibusExistService);
					System.out.println("初始化 调用 timer4(list);");
				} catch (Exception e) {
					System.out.println("timer4(list) error!");
					e.printStackTrace();
				}
		}
		
		//数据库同步
		try {
			DataSynchronizationTimer3.timer4(null, null); 
			System.out.println("初始化 调用 timer4(list);");
		} catch (Exception e) {
			System.out.println("timer4(list) error!");
			e.printStackTrace();
		}
		
		try {
			// 根据网关发送的UDP端口起监听线程
			for (IbusGateway obj : list) {

				int port = obj.getGatewayPort();
				System.out.println(obj.getGatewayPort());
				//消息接收和处理
				GatewayUDPListenerThread thread = new GatewayUDPListenerThread(obj.getGatewayPort(), obj.getGatewayIp(),obj.getId(),ibusTpviService,ibusWarnService);
				threadMap.put("gateway" + port, thread);
				threadPool.execute(thread);
				
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}*/
	}
	
	 // 定时器   用于生成监测数据表，监测数据按网关和日分表，每个网关每天一张数据表
    public static void timer4(final List<IbusGateway> list,final IbusExistServiceImpl ibusExistService) {   
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, 0); // 控制时  
        calendar.set(Calendar.MINUTE, 0);       // 控制分  
        calendar.set(Calendar.SECOND, 0);       // 控制秒  
  
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
        
        Timer timer = new Timer();  
        timer.scheduleAtFixedRate(new TimerTask() {  
            public void run() {  
                System.out.println("-------timer4 定时器任务，创建监测数据表--------"); 
                
                for (IbusGateway obj : list) {
    				int port = obj.getGatewayPort();
                	//ibus_tpvi_9001_20161026
    				Date date = new Date();
    				
                	String tableName = "ibus_tpvi_"+port+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
                	String auto_increment = DateUtils.formatDateToString(date, "yyyyMMdd")+"0000000";
                	
                	System.out.println("create table:"+tableName);
                	String sql ="CREATE TABLE "+tableName+" ("
								  +"`id` bigint(15) NOT NULL AUTO_INCREMENT,"
								  +"`tp1` float(11,1) DEFAULT NULL,"
								  +"`tp2` float(11,1) DEFAULT NULL,"
								  +"`tp3` float(11,1) DEFAULT NULL,"
								  +"`tp4` float(11,1) DEFAULT NULL,"
								  +"`tp5` float(11,1) DEFAULT NULL,"
								  +"`tp6` float(11,1) DEFAULT NULL,"
								  +"`tp7` float(11,1) DEFAULT NULL,"
								  +"`tp8` float(11,1) DEFAULT NULL,"
								  +"`tp9` float(11,1) DEFAULT NULL,"
								  +"`va` float(11,2) DEFAULT NULL,"
								  +"`vb` float(11,2) DEFAULT NULL,"
								  +"`vc` float(11,2) DEFAULT NULL,"
								  +"`ia` float(11,3) DEFAULT NULL,"
								  +"`ib` float(11,3) DEFAULT NULL,"
								  +"`ic` float(11,3) DEFAULT NULL,"
								  +"`gateway_id` int(11) NOT NULL,"
								  +"`node_id` int(11) NOT NULL,"
								  +"`ac_time` datetime(3) NOT NULL,"
								  +"PRIMARY KEY (`id`)"
								  +") ENGINE=InnoDB AUTO_INCREMENT="+auto_increment+" DEFAULT CHARSET=utf8;"; 
                	System.out.println(tableName);
                	  CreateTable.create(sql,ibusExistService,obj.getId(),tableName);
                }
            }  
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行  
    	//}, time, 1000*60);// 这里设定将延时每天固定执行  
    } 

    
    public static String createTable(IbusGateway obj,IbusExistServiceImpl ibusExistService){
    	int port = obj.getGatewayPort();
    	//ibus_tpvi_9001_20161026
		Date date = new Date();
		
    	String tableName = "ibus_tpvi_"+port+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
    	String auto_increment = DateUtils.formatDateToString(date, "yyyyMMdd")+"0000000";
    	
    	System.out.println("create table:"+tableName);
    	String sql ="CREATE TABLE "+tableName+" ("
					  +"`id` bigint(15) NOT NULL AUTO_INCREMENT,"
					  +"`tp1` float(11,1) DEFAULT NULL,"
					  +"`tp2` float(11,1) DEFAULT NULL,"
					  +"`tp3` float(11,1) DEFAULT NULL,"
					  +"`tp4` float(11,1) DEFAULT NULL,"
					  +"`tp5` float(11,1) DEFAULT NULL,"
					  +"`tp6` float(11,1) DEFAULT NULL,"
					  +"`tp7` float(11,1) DEFAULT NULL,"
					  +"`tp8` float(11,1) DEFAULT NULL,"
					  +"`tp9` float(11,1) DEFAULT NULL,"
					  +"`va` float(11,2) DEFAULT NULL,"
					  +"`vb` float(11,2) DEFAULT NULL,"
					  +"`vc` float(11,2) DEFAULT NULL,"
					  +"`ia` float(11,3) DEFAULT NULL,"
					  +"`ib` float(11,3) DEFAULT NULL,"
					  +"`ic` float(11,3) DEFAULT NULL,"
					  +"`gateway_id` int(11) NOT NULL,"
					  +"`node_id` int(11) NOT NULL,"
					  +"`ac_time` datetime(3) NOT NULL,"
					  +"PRIMARY KEY (`id`)"
					  +") ENGINE=InnoDB AUTO_INCREMENT="+auto_increment+" DEFAULT CHARSET=utf8;"; 
    	System.out.println(tableName);
    	return  CreateTable.create(sql,ibusExistService,obj.getId(),tableName);
    }
}
