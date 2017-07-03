package com.hd.ibus.util;

import java.text.ParseException;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


/**
 * key参数类型, value需要转换成的类型
 * @author Administrator
 *
 */
public class CustomDateConverter implements Converter<String,Date>{

	public Date convert(String source) {
		
		if(StringUtils.isEmpty(source))
			return null;
		
		try {
			return DateUtils.getDate(source, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//转换失败返回 null
		return null;
	}

}
