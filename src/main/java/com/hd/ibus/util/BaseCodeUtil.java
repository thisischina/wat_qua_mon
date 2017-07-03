package com.hd.ibus.util;

import org.apache.commons.codec.binary.Base64;

/**
 * base码加解密
 * 
 * @author Administrator
 *
 */
public class BaseCodeUtil {

	public static String encodeStr(byte[] plainText) {

		Base64 base64 = new Base64();
		return new String(base64.encode(plainText));
	}

	public static byte[] decodeStr(String encodeStr) {
		
		Base64 base64 = new Base64();
		return base64.decode(encodeStr.getBytes());

	}
}
