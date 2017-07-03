package ibus;

import com.hd.ibus.util.dataUtil.Commns;

public class Test1 {

	public static void main(String[] args) {
		
		String strAddr[] ={"00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000",
				"00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000",
				"00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000",
				"00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000",
				"00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000",
				"00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000",
				"00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000",
				"00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000",
				"00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000",
				"00000000","00000000","00000000","00000000","00000000","00000000"};
		byte[]  byteArr=new byte[96];
		
		String addrs = "1,5,10,15";
		
	   String[] addrArr = addrs.split(",");
	   
		
	   
	   for(String addr :addrArr){
		   Integer addrInt = Integer.parseInt(addr);
		   
		   int i = addrInt/8;
		   int j =addrInt%8;
		  // System.out.println("i:"+i+",j:"+j);
		   
		   String str1 = strAddr[i].substring(0, 8-j);
		   String str2 = strAddr[i].substring(8-j+1,8);
		   strAddr[i]=str1+"1"+str2;
		   System.out.println( strAddr[i]);
		   
		   
		   
	   }
		String strsss = String.valueOf(strAddr);
		
		for(int i=0;i<96;i++){
			String x16 = Integer.toHexString(Integer.parseInt(strAddr[i], 2));//转十六进制
			
			
			System.out.println(x16);
			//Commns.hexStr2Bytes(Integer.toHexString(Integer.parseInt(strAddr[i], 2)));
		}
		
		
		
		
	}
	
	
	public static byte bit2byte(String bString){
		byte result=0;
		for(int i=bString.length()-1,j=0;i>=0;i--,j++){
		result+=(Byte.parseByte(bString.charAt(i)+"")*Math.pow(2, j));
		}
		return result;
		}

}
