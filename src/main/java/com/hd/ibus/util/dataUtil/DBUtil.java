package com.hd.ibus.util.dataUtil;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class DBUtil {
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	private static String driver2 = null;
	private static String url2 = null;
	private static String username2 = null;
	private static String password2 = null;
	private static Connection conn = null;
	private static Connection conn2 = null;
	
	static{
		Properties pro = new Properties();
		try{
		InputStream ins =  Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/db.properties");
	     pro.load(ins);
	     driver=pro.getProperty("driverClassName");
	     url=pro.getProperty("url");
	     username=pro.getProperty("username");
	     password=pro.getProperty("password");
	     
	     driver2=pro.getProperty("dataSource.driverClassName");
	     url2=pro.getProperty("dataSource.url");
	     username2=pro.getProperty("dataSource.username");
	     password2=pro.getProperty("dataSource.password");
	     ins.close();
		}catch(Exception e){
			
		}
	}

	public static Connection getConn() {
	    try {
	    	System.out.println(driver);
	    	
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	public static Connection getConn2() {
	    try {
	        Class.forName(driver2); //classLoader,加载对应驱动
	        conn2 = (Connection) DriverManager.getConnection(url2, username2, password2);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn2;
	}
	 
}
