package com.hd.ibus.util;

import org.junit.Test;

public class StringUtils {
	
	private StringBuilder sb;
	
	public StringUtils() {
		super();
	}
	
	public StringUtils(String str) {
		super();
		this.sb = new StringBuilder(str);
	}

	public StringUtils replace(String source, String target) {
	
		sb.replace(sb.indexOf(source), sb.indexOf(source)+source.length(), target);
		return this;
		
	}
	
	public String getSb() {
		return sb.toString();
	}

	/**
	 * 如果参数str是空的 返回true
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		
		if(str == null || "".equals(str))
			return true;
		return false;
	}
	
	

	public static String substr(String str, String con) {
		
		if(isEmpty(str))
			return "";
		int i = str.indexOf(con);
		if(i == -1)
			return str;
		return str.substring(i+con.length());
	}
	
	
	@Test
	public void test() {
		String str = StringUtils.substr("aabase64_sadasd", "base64");
		System.out.println(str);
	}
}
