package com.hd.ibus.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

public class PrintLog {

	private static Logger logger = Logger.getLogger(PrintLog.class);

	public static void printInfo(Exception e) {
		logger.info(e);

	}

	public static void printDebug(Exception e) {
		//logger.debug("debug日志");
		logger.debug(e);
	}

	public static void printError(Exception e) {
		logger.error(e);
	}

	public static void main(String[] args) {
		Connection conn = getCon(); 
		System.out.println("获取连接");
	}

	public static Connection getCon() {
		try {
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "roo";
		try {
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
		} catch (SQLException se) {
			printInfo(se);
			printDebug(se);
			printError(se);
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		return conn;
	}
}
