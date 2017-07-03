package ibus;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.hd.ibus.util.dataUtil.Commns;

public class GatewayUDPclientDemo implements Runnable {

	private int flag;
	
public GatewayUDPclientDemo(int flag){
	this.flag = flag;
}
	
	public void run() {
		try {
			DatagramSocket client = new DatagramSocket();
	        
			String str3="6801710124C800B600C800C8000000000000000000D500000000000000000000000000000000000000E216";
	        //InetAddress addr = InetAddress.getLocalHost();
			InetAddress addr = InetAddress.getByName("192.168.0.198");
	        int port = flag;
	        byte[] backbuf = Commns.hexStr2Bytes(str3);
	        
	        DatagramPacket sendPacket = new DatagramPacket(backbuf ,backbuf.length , addr , port);
	        int i=0;
	        while(true){
	        	Thread.sleep(100);
	        	//System.out.println(flag+":"+i+"--"+System.currentTimeMillis()+":"+str3);
	        	 client.send(sendPacket);
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		 
		
	}
	
	public static void main(String[] args) throws InterruptedException {


		Thread th1 = new Thread(new GatewayUDPclientDemo(9001));

		Thread th2 = new Thread(new GatewayUDPclientDemo(9002));
		Thread th3 = new Thread(new GatewayUDPclientDemo(9003));


//		Thread th3 = new Thread(new GatewayUDPclientDemo(9007));
//		Thread th4 = new Thread(new GatewayUDPclientDemo(9006));
//		Thread th5 = new Thread(new GatewayUDPclientDemo(9005));
//		Thread th6 = new Thread(new GatewayUDPclientDemo(9010));
//		Thread th7 = new Thread(new GatewayUDPclientDemo(9011));
//		Thread th8 = new Thread(new GatewayUDPclientDemo(9012));
//		Thread th9 = new Thread(new GatewayUDPclientDemo(9013));
//		Thread th10 = new Thread(new GatewayUDPclientDemo(9014));
		th1.start();
		th2.start();
		th3.start();
//		th4.start();
//		th5.start();
//		th6.start();
		//th7.start();
		//th8.start();
		//th9.start();
		//th10.start();
	}
	

}
