package com.hd.ibus.util.dataUtil;


public class Commns {

	public static boolean still= false;
	
	public static void main(String[] args){
		System.out.println("6800048400F016BE00BC00BC00FA00FA00FA00FA00F900D21600FA00F900D216".length());
		String s = "6801810112C000BF00BC00BD00FA00FA00FA00FA00FA00D716";
		byte[] bt = hexStr2Bytes(s);
		System.out.println(bt); 
	}
	
	//��ݽ����̵߳�״̬��0�����У�1��ֹͣ
	public static int still_state= 0;
	//字符串转数组
	public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        //System.out.println(src.length());
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
        }
        return ret;
    }


private static byte uniteBytes(String src0, String src1) {
    byte b0 = Byte.decode("0x" + src0).byteValue();
    b0 = (byte) (b0 << 4);
    byte b1 = Byte.decode("0x" + src1).byteValue();
    byte ret = (byte) (b0 | b1);
    return ret;
}


private final static String HEX_CODE = "0123456789ABCDEF";

//数组转字符串
public final static  String byteArrayToHexString(byte[] bs) {
    int _byteLen = bs.length;
    StringBuilder _result = new StringBuilder(_byteLen * 2);
    for (int i = 0; i < _byteLen; i++) {
        int n = bs[i] & 0xFF;
        _result.append(HEX_CODE.charAt(n >> 4));
        _result.append(HEX_CODE.charAt(n & 0x0F));
    }
    return String.valueOf(_result);
}
//16进制转二进制
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
	//截取byte数组调换位置
	public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[2];
        bs[0]=src[begin+1];
        bs[1]=src[begin];
		return bs;
    }
	//2进制转16进制
	 public static String binaryString2hexString(String bString)  
	    {  
	        if (bString == null || bString.equals("") || bString.length() % 8 != 0)  
	            return null;  
	        StringBuffer tmp = new StringBuffer();  
	        int iTmp = 0;  
	        for (int i = 0; i < bString.length(); i += 4)  
	        {  
	            iTmp = 0;  
	            for (int j = 0; j < 4; j++)  
	            {  
	                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);  
	            }  
	            tmp.append(Integer.toHexString(iTmp));  
	        }  
	        return tmp.toString();  
	    }  
	 
	 public  static String byteToHexString(byte arr) {
			StringBuilder _result = new StringBuilder(2);
				int n = arr & 0xFF;
				_result.append(HEX_CODE.charAt(n >> 4));
				_result.append(HEX_CODE.charAt(n & 0x0F));
			return String.valueOf(_result);
		}

}
