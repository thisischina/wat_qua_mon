package com.hd.ibus.util;

public class ValidateUtils {
	
	
	//判断是否为数字
	public static boolean isInteger(String value) {
		  try {
		   Integer.parseInt(value);
		   return true;
		  } catch (NumberFormatException e) {
		   return false;
		  }
	}

}
