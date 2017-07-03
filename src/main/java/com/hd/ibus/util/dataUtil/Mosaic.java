package com.hd.ibus.util.dataUtil;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.util.SystemOutLogger;

import com.hd.ibus.service.IbusTpviService;
import com.hd.ibus.service.IbusWarnService;
import com.hd.ibus.util.DateUtils;
import com.hd.ibus.util.Helper;

public class Mosaic {
	
	//端口占用标识
	public volatile static boolean port_state = true;
	
	@SuppressWarnings("unchecked")
	public static String mosaicSendWords (String nodeAddress,String control,String onemore,int port,String data,String data2){
		DatagramSocket socket = null;
		try {
			
			InetAddress address = InetAddress.getLocalHost();  
            //System.out.println("getLocalHost"+address);
            socket = new DatagramSocket(port, address);  
            String str3 = nodeAddress;
            byte[] buf = new byte[120];  
            DatagramPacket packet = new DatagramPacket(buf, buf.length); 
            int count=0;
              while(true){
            	  count++;
            	  if(count==50){
            		  socket.close();
            		  return "outTime2";
            	  }
            	  socket.receive(packet);  
            	  byte[] byt = packet.getData();
            	  String str2 = Commns.byteArrayToHexString(byt);
            	  String str7=str2.substring(0, str2.lastIndexOf("16")+2);
            	  byte[] words=Commns.hexStr2Bytes(str7);
            	 // System.out.println("接收：xxxxxxxxxxxxxxx  nodeAddress:"+nodeAddress+",control:"+control+",onemore:"+onemore+",data:"+data+",data2:"+data2);
            	  Map message=mosaicmessage(nodeAddress, control, onemore, data,data2);
            	  //判断返回的报文是不是正确的
            	  //68 01 81 03 00 ed 16
            	  //if(str2.substring(0, str2.lastIndexOf("16")+2).equals(message.get("message"))){
            	  
            	  if(message.get("error")!=null){
            		  return (String) message.get("error");
            	  }
            	  
            	  System.out.println("####################  发送："+message.get("receive"));
            	  System.out.println("####################  接收："+str2);
            	  if(str2.substring(0, str2.lastIndexOf("16")+2).indexOf(message.get("receive").toString().toUpperCase())!=-1){
            		  Map<String, String> p=Judge.ctrlReceiveWords(words);//控制接收解析后返回给控制台
            		  System.out.println("+++++++++++++++++++++ 成功，接收："+str2.substring(0, str2.lastIndexOf("16")+2));
            		  socket.close();
            		  return str2.substring(0, str2.lastIndexOf("16")+2);
            	  }
            	  
            	  //线程开始，发送一次，然后每接收十次发送一次
            	  if(count==1||count%10==0){
            		  
            		  //除第一次发送外，后续发送时暂停三秒再发送
            		  if(count%10==0){
            			  Thread.sleep(4000);
            		  }
            		  
                      InetAddress clientAddress = packet.getAddress(); 
                      int clientPort = packet.getPort(); 
                      //System.out.println("clientAddress:"+clientAddress+",clientPort:"+clientPort);
                      SocketAddress sendAddress = packet.getSocketAddress();  
                      byte[] backbuf = Commns.hexStr2Bytes(message.get("message").toString());

                      System.out.println(count+"--发送---------------------发送："+message.get("message").toString());

                      DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length, sendAddress); 
                      socket.send(sendPacket);
            	  }
            	  
            	    
           }
		} catch (Exception e) {
			e.printStackTrace();
			return "error2";
		}finally{
			if(socket!=null){
				socket.close();
			}
		}
 
	}
	

	public static Map mosaicmessage(String nodeAddress,String control,String onemore,String data,String data2){
		Map<String, String> map=new HashMap<String,String>();
		String start="68";
		String end="16";
		int addr=Integer.parseInt(nodeAddress.substring(0,2));
        String adde=Integer.toHexString(addr);//10进制字符串转16进制
        if(adde.length()<2){
        	adde="0"+adde;
        }
		String ess=nodeAddress.substring(2,4);
		String address=adde+ess;
		String cont=control;
		String length="";
		String datas=data;
		String message=null;
		int CS=0x00;
		String bit="";
		String cs="";
		if(!"".equals(onemore)&&onemore!=null&&"one".equals(onemore)&&"00".equals(data)&&!"03".equals(cont)){//纯控制
			if("00".equals(data)){
				String mess=start+address+data+cont;
				byte[] m=Commns.hexStr2Bytes(mess);//字符串转数组
				for(int i=0;i<m.length;i++){
					CS=CS+m[i];
				}
				byte[] c=new byte[1];
				c[0]=(byte) CS;
				String a=(Commns.byteArrayToHexString(c));//数组转字符串
				int lea=a.length();
				cs=a.substring(lea-2, lea);
				message=start+address+cont+datas+cs+end;
			}
			map.put("message", message);
			String receive=start+address+control;
			map.put("receive",receive);
			return map;
		}else if(!"".equals(onemore)&&onemore!=null&&"more".equals(onemore)&&!"00".equals(data)){//配置查询范围
			String mess2="";
			String bitall="00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00";
			String[] b=bitall.split(",");
			System.out.println(b);
			String[] bat=data.split(",");
			for(int num=0;num<bat.length;num++){
				b[Integer.parseInt(bat[num])-1]="01";
			}
			String bet="";
			
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < b.length; i++){
			 sb. append(b[i]);
			}
			
			String s = sb.toString();
			byte[] da=Commns.hexStr2Bytes(s);
			for(int i=0;i<da.length;i++){
				if(da[i]==0x00){
					bet="0";
				}else{
					bet="1";
				}
				bit=bit+bet;
			}
			int a=bit.length()/8;
			byte[] bt=new byte[a];
			for(int j=0;j<a;j++){
				String p=bit.substring(j*8, j*8+8);
				String P1=new StringBuffer(p).reverse().toString();
				String l=Commns.binaryString2hexString(P1);
				byte[] m=Commns.hexStr2Bytes(l);//字符串转数组
				bt[j]=m[0];
			}
			 datas=(Commns.byteArrayToHexString(bt));//数组转字符串
			 if(data2!=null&&!"".equals(data2)){
				 mess2=start+address+cont+"60"+datas+data2;	
				}
			   mess2=start+address+cont+"60"+datas;
			 byte[] m=Commns.hexStr2Bytes(mess2);//字符串转数组
				for(int i=0;i<m.length;i++){
					CS=CS+m[i];
				}
				byte[] c=new byte[1];
				c[0]=(byte) CS;
				String aq=(Commns.byteArrayToHexString(c));//数组转字符串
				int lea=aq.length();
				cs=aq.substring(lea-2, lea);
				String lenth="0"+a;
				byte[] len=Commns.hexStr2Bytes(lenth);//字符串转数组
				String lenths=(Commns.byteArrayToHexString(len));//数组转字符串
				if(data2!=null&&!"".equals(data2)){
					message=start+address+cont+"60"+datas+data2+cs+end;
					}
			message=start+address+cont+"60"+datas+cs+end;
			String receive=start+address+control;
			System.out.println(" receive=start+address+control:"+receive);
			map.put("message", message);
			System.out.print("发送+："+message);
			System.out.println("长度"+message.length());
			map.put("receive", receive);
			return map;
			
		   }else if(!"".equals(onemore)&&onemore!=null&&"one".equals(onemore)&&"03".equals(cont)){//下发阈值
			String src[] =data.split(",");
			String receive=start+address+control;
			if(src.length<4){
				map.put("error", "阈值必须包含温度，电流，最大最小电压！");
				return map;
			}
			int amax=Integer.parseInt(src[0]);
			if(amax>127){
				map.put("error", "温度最高为70度");
				return map;
			}
			String adata=Integer.toHexString(amax); 
			if("00".equals(adata)){
				map.put("error", "温度最大值不能为0");
				return map;
			}
			int vmix=Integer.parseInt(src[1]);
			String bdata=Integer.toHexString(vmix);
			if("00".equals(bdata)){
				map.put("error", "电流最大值不能为0");
			    return map;	
			}
			if(bdata.length()==3){
				bdata="0"+bdata;
			}
			if(bdata.length()==2){
				bdata="00"+bdata;
			}
			if(bdata.length()==1){
				bdata="000"+bdata;
			}
			int vmax=Integer.parseInt(src[2]);
			if(vmax<140){
				map.put("error", "最小电压值不能低于140");
			    return map;	
			}
			String cdata=Integer.toHexString(vmax);
			int pmax=Integer.parseInt(src[3]);
			if(pmax>240){
				map.put("error", "最大电压值不能大于240");
			    return map;
			}
			String ddata=Integer.toHexString(pmax);
			if(ddata.length()<2){
				ddata="0"+ddata;
			}
			if(adata.length()<2){
				adata="0"+adata;
			}
			if(cdata.length()<2){
				cdata="0"+cdata;
			}
			datas=adata+bdata+cdata+ddata;
			map.put("maxtp", src[0]);
			map.put("maxi", src[1]);
			map.put("mixv", src[2]);
			map.put("maxv", src[3]);
			String mess=start+address+"05"+datas+cont;
			byte[] m=Commns.hexStr2Bytes(mess);//字符串转数组
			for(int i=0;i<m.length;i++){
				CS=CS+m[i];
			}
			byte[] c=new byte[1];
			c[0]=(byte) CS;
			String a=(Commns.byteArrayToHexString(c));//数组转字符串
			int lea=a.length();
			cs=a.substring(lea-2, lea);
			String lenth="05";
			message=start+address+cont+lenth+datas+cs+end;
			receive=start+address+control;
			map.put("message", message);
			map.put("receive",receive);
			System.out.println(message);
			System.out.println(receive);
			return map;
		 }if(!"".equals(onemore)&&onemore!=null&&"one".equals(onemore)&&"05".equals(cont)){
				length="01";
				String mess=start+address+length+data+cont;
				byte[] m=Commns.hexStr2Bytes(mess);//字符串转数组
				for(int i=0;i<m.length;i++){
					CS=CS+m[i];
				}
				byte[] c=new byte[1];
				c[0]=(byte) CS;
				String a=(Commns.byteArrayToHexString(c));//数组转字符串
				int lea=a.length();
				cs=a.substring(lea-2, lea);
				message=start+address+cont+length+datas+cs+end;
				map.put("message", message);
				String receive=start+address+control;
				map.put("receive",receive);
				return map;
			} {
			 return null;
		 }
		}
	
	public static boolean isRight(byte[] word){
		int CS=0x00;
		for(int i=0;i<word.length-2;i++){
			CS=CS+word[i];
		}
		byte[] c=new byte[1];
		c[0]=(byte) CS;
		String a=(Commns.byteArrayToHexString(c));//数组转字符串
		int lea=a.length();
		String cs=a.substring(lea-2, lea);
		int length=word.length;
		byte[] lastcs=new byte[1];
		lastcs[0]=word[length-2];
		String cs1=(Commns.byteArrayToHexString(lastcs));
		if(cs1.equals(cs)){
			return true;
		}
		return false;
	}
	
	public static DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static DateFormat f2 = new SimpleDateFormat("HH:mm:ss");
	
	public static void decodeToDB(final byte[] bt,int gatewayId,int port,IbusTpviService ibusTpviService,IbusWarnService ibusWarnService){
		
		final Date date = new Date();
    	final String temp = f.format(date);
    	
    	if(Mosaic.isRight(bt)==true){
    		Map map = Judge.receiveWords(bt);
        	if(map!=null){
        		map.put("acTime2", temp);
            	map.put("acTime", "'"+temp+"'");
            	map.put("gatewayId", gatewayId);
            	map.put("nodeId", ((Float) map.get("id1")).intValue());
            	String tableName = "ibus_tpvi_"+port+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
            	map.put("tableName", tableName);
            	//insertStart = System.currentTimeMillis();
            	ibusTpviService.saveTpvi22(map);
            	//insertEnd = System.currentTimeMillis();
            	//System.out.println("==ibusTpviService.saveTpvi22(map):"+(insertEnd-insertStart));
            	//ibusTpviService.saveTpvi(map);
            	if((Float)map.get("type")==81.0F&&Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")!=null){//九路温度
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
            			//以下为短信
//            			String content = "{gateway:'"+port+"',node:'"+Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue())+"',reason:'温度过高',time:'"+temp+"'}";
//            			try {
//							MessageUtil.sendMessage(content);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
            		}
            		
            	}else if((Float)map.get("type")==71.0F&&Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")!=null){//五路温度
            		//insertStart2 = System.currentTimeMillis();
            		String warn = "";
            		String typeName2 = "";
            		//System.out.println("map.get(\"tp1\"):"+map.get("tp1")+","+gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax"+":"+Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax"));
            		if((Float)map.get("tp1")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
            			warn += "tp1,"; 
            			typeName2 += "温度越阀值,";
            		}
            		if((Float)map.get("tp2")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
            			warn += "tp2,"; 
            			if(typeName2.length()<1){
            				typeName2 += "温度越阀值,";
            			}
            		}
            		if((Float)map.get("tp3")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
            			warn += "tp3,"; 
            			if(typeName2.length()<1){
            				typeName2 += "温度越阀值,";
            			}
            		}
            		if((Float)map.get("tp4")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
            			warn += "tp4,"; 
            			if(typeName2.length()<1){
            				typeName2 += "温度越阀值,";
            			}
            		}
            		if((Float)map.get("tp5")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_tpMax")){
            			warn += "tp5,"; 
            			if(typeName2.length()<1){
            				typeName2 += "温度越阀值,";
            			}
            		}
            		if((Float)map.get("va")<(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMix")){
            			warn += "va,"; 
            			typeName2 += "电压越阀值,";
            		}
            		if((Float)map.get("vb")<(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMix")){
            			warn += "vb,"; 
            			if(typeName2.length()>0&&!typeName2.contains("电压越阀值")){
            				typeName2 += "电压越阀值,";
            			}
            		}
            		if((Float)map.get("vc")<(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMix")){
            			warn += "vc,"; 
            			if(typeName2.length()>0&&!typeName2.contains("电压越阀值")){
            				typeName2 += "电压越阀值,";
            			}
            		}
            		if((Float)map.get("va")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMax")){
            			warn += "va,"; 
            			if(typeName2.length()>0&&!typeName2.contains("电压越阀值")){
            				typeName2 += "电压越阀值,";
            			}
            		}
            		if((Float)map.get("vb")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMax")){
            			warn += "vb,"; 
            			if(typeName2.length()>0&&!typeName2.contains("电压越阀值")){
            				typeName2 += "电压越阀值,";
            			}
            		}
            		if((Float)map.get("vc")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_vMax")){
            			warn += "vc,"; 
            			if(typeName2.length()>0&&!typeName2.contains("电压越阀值")){
            				typeName2 += "电压越阀值,";
            			}
            		}
            		if((Float)map.get("ia")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_iMax")){
            			warn += "ia,"; 
            			if(typeName2.length()>0&&!typeName2.contains("电流越阀值")){
            				typeName2 += "电流越阀值,";
            			}
            		}
            		if((Float)map.get("ib")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_iMax")){
            			warn += "ib,";
            			if(typeName2.length()>0&&!typeName2.contains("电流越阀值")){
            				typeName2 += "电流越阀值,";
            			}
            		}
            		if((Float)map.get("ic")>(Float)Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue()+"_iMax")){
            			warn += "ic,"; 
            			if(typeName2.length()>0&&!typeName2.contains("电流越阀值")){
            				typeName2 += "电流越阀值,";
            			}
            		}
            		//insertEnd2 = System.currentTimeMillis();
            		//System.out.println("if--worn--time:"+(insertEnd2-insertStart2));
            		if(warn.length()>0){
            			//insertStart3 = System.currentTimeMillis();
            			warn.subSequence(0, warn.length()-1);
            			map.put("warn", warn);
            			map.put("acTime", map.get("acTime2"));
            			//long insertStart3s = System.currentTimeMillis();
            			ibusWarnService.saveWarn2(map);
            			//insertEnd3 = System.currentTimeMillis();
            			//System.out.println("====insert--worn--time  all:"+(insertEnd3-insertStart3)+",step1:"+(insertStart3s-insertStart3)+",step1:"+(insertEnd3-insertStart3s));
            			//以下为短信
//            			typeName2=typeName2.substring(0, typeName2.length()-1);
//            			
//            			String content = "{gateway:'"+port+"',node:'"+Helper.map.get(gatewayId+"_"+((Float) map.get("id1")).intValue())+"',reason:'"+typeName2+"',time:'"+f2.format(date)+"'}"; 
//            			System.out.println("content:"+content);
//            			try {
//							MessageUtil.sendMessage(content);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
            		}
            		
            	}
            	
            	
        	}
    	}
		
		
	}
	
	
	
	public static void main(String[] args) {
		//port:9001,param10,10,142,235,,type:03,nodeAddr:1，nodeType:71

		
		 String a= "68 62 81 01 12 EB 00 EC 00 F4 00 EB 00 E2 00 E9 00 E2 00 E9 00 5C 01 07 16";
		byte[] bt = Commns.hexStr2Bytes(a.replaceAll(" ","")); 
		
		String str ="6862810112EC00ED00F400EB00E200EA00E400EB005E01101600EB005E011016";
		byte[] bt2 = Commns.hexStr2Bytes(str); 
		
		boolean result = Mosaic.isRight(bt2);
		System.out.println(bt2);
		
	}
}
