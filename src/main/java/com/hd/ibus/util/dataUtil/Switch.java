package com.hd.ibus.util.dataUtil;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

import org.apache.log4j.Logger;

import com.hd.ibus.util.PrintLog;


public class Switch{

	public static Logger logger = Logger.getLogger(PrintLog.class);
	
	
	public static String  getswitchState(String code){
		
		try {
			
			InetAddress address = InetAddress.getLocalHost();  
            int port = 9001;    
              System.out.println("getLocalHost"+address);
            //����DatagramSocket����  
            DatagramSocket socket = new DatagramSocket(port, address);  
            
            String str3 = code;
            byte[] buf = new byte[1024];  //����byte����  
            DatagramPacket packet = new DatagramPacket(buf, buf.length);  //����DatagramPacket����  
            int count=0;
              while(true){
            	  count++;
            	  if(count==50){
            		  socket.close();
            		  return "outTime";
            	  }
            	  
            	  
            	  socket.receive(packet);  //ͨ���׽��ֽ������  
            	  byte[] byt = packet.getData();
            	  String str2 = Commns.byteArrayToHexString(byt);
            	  System.out.println(str2.substring(0, str2.lastIndexOf("16")+2));
                  
            	  if(str2.indexOf("6801810601")!=-1){
            		  System.out.println("---------------״̬------------��"+str2.substring(0, str2.lastIndexOf("16")+2));
            		  socket.close();
            		  return str2.substring(0, str2.lastIndexOf("16")+2);
            	  }
            	  Thread.sleep(200);
            	  
                  //�ӷ��������ظ�ͻ������  
                  InetAddress clientAddress = packet.getAddress(); //��ÿͻ��˵�IP��ַ  
                  
                  int clientPort = packet.getPort(); //��ÿͻ��˵Ķ˿ں�  
                  System.out.println("clientAddress:"+clientAddress+",clientPort:"+clientPort);
                  
                  SocketAddress sendAddress = packet.getSocketAddress();  
                  
                  byte[] backbuf = Commns.hexStr2Bytes(str3.replaceAll(" ",""));
                  DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length, sendAddress); //��װ���ظ�ͻ��˵����  
                  
                  socket.send(sendPacket);  //ͨ���׽��ַ������������  
                //  socket.close();  //�ر��׽���                    
           }
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	
	//��
	public static String  switchStart(){
		
		try {
			
			InetAddress address = InetAddress.getLocalHost();  
            int port = 9001;    
              System.out.println("getLocalHost"+address);
            //����DatagramSocket����  
            DatagramSocket socket = new DatagramSocket(port, address);  
            
            String str3 = "68 01 01 05 01 01 71 16";
    		
            byte[] buf = new byte[1024];  //����byte����  
            DatagramPacket packet = new DatagramPacket(buf, buf.length);  //����DatagramPacket����  
            int count=0;
              while(true){
            	  count++;
            	  if(count==50){
            		  socket.close();
            		  return "outTime";
            	  }
            	  
            	  
            	  socket.receive(packet);  //ͨ���׽��ֽ������  
            	  byte[] byt = packet.getData();
            	  String str2 = Commns.byteArrayToHexString(byt);
            	  System.out.println(str2);
                  
            	  if(str2.indexOf("6801810500EF16")!=-1){
            		  System.out.println("---------------״̬------------��"+str2.substring(0, str2.lastIndexOf("16")+2));
            		  socket.close();
            		  return str2.substring(0, str2.lastIndexOf("16")+2);
            	  }
            	  Thread.sleep(0);
            	  
                  //�ӷ��������ظ�ͻ������  
                  InetAddress clientAddress = packet.getAddress(); //��ÿͻ��˵�IP��ַ  
                  
                  int clientPort = packet.getPort(); //��ÿͻ��˵Ķ˿ں�  
                  System.out.println("clientAddress:"+clientAddress+",clientPort:"+clientPort);
                  
                  SocketAddress sendAddress = packet.getSocketAddress();  
                  
                  byte[] backbuf = Commns.hexStr2Bytes(str3.replaceAll(" ",""));
                  DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length, sendAddress); //��װ���ظ�ͻ��˵����  
                  
                  socket.send(sendPacket);  //ͨ���׽��ַ������������  
                //  socket.close();  //�ر��׽���                    
           }
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	//��
	public static String  switchStop(){
		
		try {
			
			InetAddress address = InetAddress.getLocalHost();  
	        int port = 9001;    
	          System.out.println("getLocalHost"+address);
	        //����DatagramSocket����  
	        DatagramSocket socket = new DatagramSocket(port, address);  
	        
	        String str3 = "68 01 01 05 01 00 70 16";
			
	        byte[] buf = new byte[1024];  //����byte����  
	        DatagramPacket packet = new DatagramPacket(buf, buf.length);  //����DatagramPacket����  
	        int count=0;
	          while(true){
	        	  count++;
	        	  if(count==50){
	        		  socket.close();
	        		  return "outTime";
	        	  }
	        	  
	        	  
	        	  socket.receive(packet);  //ͨ���׽��ֽ������  
	        	  byte[] byt = packet.getData();
	        	  String str2 = Commns.byteArrayToHexString(byt);
	        	  System.out.println(str2);
	              
	        	  if(str2.indexOf("6801810500EF16")!=-1){
	        		  System.out.println("---------------״̬------------��"+str2.substring(0, str2.lastIndexOf("16")+2));
	        		  socket.close();
	        		  return str2.substring(0, str2.lastIndexOf("16")+2);
	        	  }
	        	  Thread.sleep(0);
	        	  
	              //�ӷ��������ظ�ͻ������  
	              InetAddress clientAddress = packet.getAddress(); //��ÿͻ��˵�IP��ַ  
	              
	              int clientPort = packet.getPort(); //��ÿͻ��˵Ķ˿ں�  
	              System.out.println("clientAddress:"+clientAddress+",clientPort:"+clientPort);
	              
	              SocketAddress sendAddress = packet.getSocketAddress();  
	              
	              byte[] backbuf = Commns.hexStr2Bytes(str3.replaceAll(" ",""));
	              DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length, sendAddress); //��װ���ظ�ͻ��˵����  
	              
	              socket.send(sendPacket);  //ͨ���׽��ַ������������  
	            //  socket.close();  //�ر��׽���                    
	       }
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	
	//�·���ַ
	public static String  setNodeAdress(){
		
		try {
			InetAddress address = InetAddress.getLocalHost();  
	        int port = 9001;    
	          System.out.println("getLocalHost"+address);
	        //����DatagramSocket����  
	        DatagramSocket socket = new DatagramSocket(port, address);  
	        
	        String str3 = "68 01 04 80 60 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 "
		        		+ "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 "
		        		+ "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 "
		        		+ "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 4e 16";
			
	        byte[] buf = new byte[1024];  //����byte����  
	        DatagramPacket packet = new DatagramPacket(buf, buf.length);  //����DatagramPacket����  
	        int count=0;
	          while(true){
	        	  count++;
	        	  if(count==50){
	        		  socket.close();
	        		  return "outTime";
	        	  }
	        	  
	        	  
	        	  socket.receive(packet);  //ͨ���׽��ֽ������  
	        	  byte[] byt = packet.getData();
	        	  String str2 = Commns.byteArrayToHexString(byt);
	        	  System.out.println(str2);
	              
	        	  if(str2.indexOf("680004806001")!=-1){
	        		  System.out.println("---------------״̬------------��"+str2.substring(0, str2.lastIndexOf("16")+2));
	        		  socket.close();
	        		  return str2.substring(0, str2.lastIndexOf("16")+2);
	        	  }
	        	  Thread.sleep(0);
	        	  
	              //�ӷ��������ظ�ͻ������  
	              InetAddress clientAddress = packet.getAddress(); //��ÿͻ��˵�IP��ַ  
	              
	              int clientPort = packet.getPort(); //��ÿͻ��˵Ķ˿ں�  
	              System.out.println("clientAddress:"+clientAddress+",clientPort:"+clientPort);
	              
	              SocketAddress sendAddress = packet.getSocketAddress();  
	              
	              byte[] backbuf = Commns.hexStr2Bytes(str3.replaceAll(" ",""));
	              DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length, sendAddress); //��װ���ظ�ͻ��˵����  
	              
	              socket.send(sendPacket);  //ͨ���׽��ַ������������  
	            //  socket.close();  //�ر��׽���                    
	       }
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public static void main(String[] args){
		Switch.switchStop();
	}
}
