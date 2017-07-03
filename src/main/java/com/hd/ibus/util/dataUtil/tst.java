package com.hd.ibus.util.dataUtil;

import java.util.Map;

public class tst {
	public 
	   static int bytesToInt(byte[] ary, int offset) {  
	    int value;    
	    byte[] ary1={0X68,0X03,0X71,0X01};
	    return 0;
}
	

	 public static String hexString2binaryString(String hexString)
	    {
	        if (hexString == null || hexString.length() % 2 != 0)
	            return null;
	        String bString = "", tmp;
	        for (int i = 0; i < hexString.length(); i++)
	        {
	            tmp = "0000"
	                    + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
	            bString += tmp.substring(tmp.length() - 4);
	        }
	        return bString;
	    }
	public static void main(String[] args) {



	    //
//		String adada=Mosaic.mosaicSendWords("0181", "02", "one", 9001 ,"00");
    String a= "68 18 81 01 12 FC 00 FD 00 FE 00 F9 00 FC 00 04 01 01 01 05 01 4D 01 5B 16";
    byte[] bt = Commns.hexStr2Bytes(a.replaceAll(" ","")); 
	Map<String, Float> map = Judge.receiveWords(bt);
	for (Map.Entry<String,Float> entry : map.entrySet()) {  
		  
	    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
	  
	}  

	//String adada2=Mosaic.mosaicSendWords("0004", "80", "more", 9001 ,"05,06",null);

//		String adada3=Mosaic.mosaicSendWords("0181", "04", "one", 9001 ,"00");
//		String adada4=Mosaic.mosaicSendWords("0181", "05", "one", 9001 ,"01");
	//	String adada5=Mosaic.mosaicSendWords("0181", "05", "one", 9001 ,"00");
//		String adada6=Mosaic.mosaicSendWords("0181", "06", "one", 9001 ,"01,00,01");
		//System.out.println(adada);
		//System.out.println(binaryString2hexString("10010110"));

	    //
		//String adada=Mosaic.mosaicSendWords("0181", "03", "one", "50,80,145,236");
		//System.out.println(adada);
		//System.out.println(binaryString2hexString("10010110"));

		

	}
	
	    
}
