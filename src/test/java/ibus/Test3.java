package ibus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test3 {
	public static void main(String[] args) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time3 = sdf.format(date);
		String endTime = sdf2.format(sdf.parse(time3));
		System.out.println(endTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); 
        cal.add(Calendar.HOUR, -1);
        date = cal.getTime();
		String startTime = sdf2.format(sdf.parse(sdf.format(date)));
		System.out.println(startTime);
	}
}
