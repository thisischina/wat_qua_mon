package com.hd.ibus.util.dataUtil;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;

import com.hd.ibus.service.IbusTpviService;
import com.hd.ibus.service.IbusWarnService;
import com.hd.ibus.util.DateUtils;
import com.hd.ibus.util.Helper;

/**
 * 用来从activemq接收消息的线程。
 * @author lenovo
 *
 */
public class GatewayMQListenerThread implements Runnable {
	public final ExecutorService pushExecutor = Executors.newFixedThreadPool(10);  
	//public final ExecutorService pushExecutor = Executors.newCachedThreadPool();  
	
	
		private IbusTpviService ibusTpviService;
		private IbusWarnService ibusWarnService;
		private int gatewayId;
		private MessageConsumer consumer;
		private int port;
		public GatewayMQListenerThread(MessageConsumer consumer,IbusTpviService ibusTpviService,IbusWarnService ibusWarnService,int gatewayId,int port) {
			this.consumer = consumer;
			this.ibusTpviService = ibusTpviService;
			this.ibusWarnService = ibusWarnService;
			this.gatewayId = gatewayId;
			this.port = port;
		}	

		public void run() {
			System.out.println("################################# GatewayMQListenerThread() start");
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
			try {
	            while (true) {
	                //设置接收者接收消息的时间，为了便于测试，这里谁定为100s
	                TextMessage message;
	                //TimestampM mea;
	                message = (TextMessage) consumer.receive(100000);
	                if (null != message) {
	                	long startAll=System.currentTimeMillis();
	                	final Date date = new Date(message.getJMSTimestamp());
	                	final String temp = f.format(date);
	                	final String str = message.getText(); 
	                	pushExecutor.execute(new Runnable() {  
	                        public void run() {
	                        	long insertStart=0l;
	                        	long insertEnd=0l;
	                        	long insertStart2=0l;
	                        	long insertEnd2=0l;
	                        	long insertStart3=0l;
	                        	long insertEnd3=0l;
	                        	
	                        	long start = System.currentTimeMillis();
	                        	byte[] bt = Commns.hexStr2Bytes(str.split("\"")[1]); 
	                        	
	                        	if(Mosaic.isRight(bt)==true){
	                        		Map map = Judge.receiveWords(bt);
			                    	if(map!=null){
			                    		map.put("acTime2", temp);
				                    	map.put("acTime", "'"+temp+"'");
				                    	map.put("gatewayId", gatewayId);
				                    	map.put("nodeId", ((Float) map.get("id1")).intValue());
				                    	String tableName = "ibus_tpvi_"+port+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
				                    	map.put("tableName", tableName);
				                    	ibusTpviService.saveTpvi22(map);
				                    	//ibusTpviService.saveTpvi(map);
				                    	if((Float)map.get("type")==81.0F){//九路温度
				                    		String warn = "";
				                    		if((Float)map.get("tp1")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp1,"; 
				                    		}
				                    		if((Float)map.get("tp2")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp2,"; 
				                    		}
				                    		if((Float)map.get("tp3")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp3,"; 
				                    		}
				                    		if((Float)map.get("tp4")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp4,"; 
				                    		}
				                    		if((Float)map.get("tp5")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp5,"; 
				                    		}
				                    		if((Float)map.get("tp6")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp6,"; 
				                    		}
				                    		if((Float)map.get("tp7")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp7,"; 
				                    		}
				                    		if((Float)map.get("tp8")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp8,"; 
				                    		}
				                    		if((Float)map.get("tp9")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp9,"; 
				                    		}
				                    		if(warn.length()>0){
				                    			warn.subSequence(0, warn.length()-1);
				                    			map.put("warn", warn);
				                    			map.put("acTime", map.get("acTime2"));
				                    			ibusWarnService.saveWarn2(map);
				                    		}
				                    		
				                    	}else if((Float)map.get("type")==71.0F){//五路温度
				                    		String warn = "";
				                    		//System.out.println("map.get(\"tp1\"):"+map.get("tp1")+","+gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax"+":"+Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax"));
				                    		if((Float)map.get("tp1")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp1,"; 
				                    		}
				                    		if((Float)map.get("tp2")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp2,"; 
				                    		}
				                    		if((Float)map.get("tp3")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp3,"; 
				                    		}
				                    		if((Float)map.get("tp4")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp4,"; 
				                    		}
				                    		if((Float)map.get("tp5")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
				                    			warn += "tp5,"; 
				                    		}
				                    		if((Float)map.get("va")<(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMix")){
				                    			warn += "va,"; 
				                    		}
				                    		if((Float)map.get("vb")<(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMix")){
				                    			warn += "vb,"; 
				                    		}
				                    		if((Float)map.get("vc")<(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMix")){
				                    			warn += "vc,"; 
				                    		}
				                    		if((Float)map.get("va")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMax")){
				                    			warn += "va,"; 
				                    		}
				                    		if((Float)map.get("vb")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMax")){
				                    			warn += "vb,"; 
				                    		}
				                    		if((Float)map.get("vc")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMax")){
				                    			warn += "vc,"; 
				                    		}
				                    		if((Float)map.get("ia")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_iMax")){
				                    			warn += "ia,"; 
				                    		}
				                    		if((Float)map.get("ib")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_iMax")){
				                    			warn += "ib,"; 
				                    		}
				                    		if((Float)map.get("ic")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_iMax")){
				                    			warn += "ic,"; 
				                    		}
				                    		if(warn.length()>0){
				                    			warn.subSequence(0, warn.length()-1);
				                    			map.put("warn", warn);
				                    			map.put("acTime", map.get("acTime2"));
				                    			ibusWarnService.saveWarn2(map);
				                    		}
				                    		
				                    	}
				                    	
				                    	
			                    	}
		                    	}
	                        }             
	                    }); 
	                } else {
	                    break;
	                }	
	            }
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

}
