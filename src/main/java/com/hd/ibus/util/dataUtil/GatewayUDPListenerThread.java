package com.hd.ibus.util.dataUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import com.hd.ibus.service.IbusTpviService;
import com.hd.ibus.service.IbusWarnService;

public class GatewayUDPListenerThread implements Runnable {
	public final ExecutorService insertExecutor = Executors.newFixedThreadPool(10); 
	//public final ExecutorService insertExecutor = Executors.newCachedThreadPool(); 
	
	//线程工作条件
	public volatile boolean working = true;
	//线程工作状态
	public volatile boolean state = true;
	private int port;
	private String gatewayIp;
	private int gatewayId;
	private IbusTpviService ibusTpviService;
	private IbusWarnService ibusWarnService;
	public GatewayUDPListenerThread(int port, String gatewayIp,int gatewayId,IbusTpviService ibusTpviService,IbusWarnService ibusWarnService) {
			
		this.port = port;
		this.gatewayIp = gatewayIp;
		this.gatewayId = gatewayId;
		this.ibusTpviService = ibusTpviService;
		this.ibusWarnService = ibusWarnService;
	}
	
	public void run() {
		System.out.println("#################### GatewayUDPListenerThread start----------port:"+port+",gatewayIp:"+gatewayIp+",gatewayId:"+gatewayId);  
		DatagramSocket socket = null;
		try {
			state = true;//线程启动时设置状态为true
			InetAddress address = InetAddress.getLocalHost();
			// int port = 9001;
			/*if (port == 0) {
				port = 9001;
			}*/
			System.out.println("####################  listener at  "+address+":"+port); 
			//DatagramSocket 获取socket连接
			socket = new DatagramSocket(port, address);
			// 定义字节数组，用于接收数据
			byte[] buf = new byte[120]; 
			// 定义数据包 DatagramPacket
			DatagramPacket packet = new DatagramPacket(buf, buf.length); 
			
			int udpCount = 0;
			while (working) {
				socket.receive(packet); 
				final byte[] byt = packet.getData();
				
				final String str2 = Commns.byteArrayToHexString(byt);
				if(byt[4]==0x12){
					final byte[] bt = Arrays.copyOf(byt, 25);
					insertExecutor.execute(new Runnable() {  
                        public void run() {
                        	//System.out.println("gatewayId:"+gatewayId+",port:"+port);
                        	Mosaic.decodeToDB(bt,gatewayId,port,ibusTpviService,ibusWarnService);
                        }             
                    });
				}
				if(byt[4]==0x24){
					final byte[] bt = Arrays.copyOf(byt, 43);
					insertExecutor.execute(new Runnable() {  
                        public void run() {
                        	Mosaic.decodeToDB(bt,gatewayId,port,ibusTpviService,ibusWarnService);
                        }             
                    });
				}
				
			}
			//线程结束时设置状态为false
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			state = false;
			if(socket!=null){
				socket.close();
			}
			System.out.println("#################### GatewayUDPListenerThread finish----------port:"+port+",gatewayIp:"+gatewayIp);  
			System.out.println("#################### listener finish------port : "+port); 
		}
	}

}
