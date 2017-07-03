package ibus;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.hd.ibus.util.dataUtil.Commns;

public class Server {

	  public static void main(String[] args)throws IOException{
	       
		  try {
			  InetAddress address = InetAddress.getLocalHost();
			  DatagramSocket socket = new DatagramSocket(9009, address);
			  while (true) {
					byte[] buf = new byte[1024]; 
					DatagramPacket packet = new DatagramPacket(buf, buf.length); 
					socket.receive(packet); 
					byte[] byt = packet.getData();
					String str2 = Commns.byteArrayToHexString(byt);
					System.out.println("server:"+System.currentTimeMillis()+"--"+str2);
				  
			  }
			  
			 
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			
		}
		
		  
	    }
}
