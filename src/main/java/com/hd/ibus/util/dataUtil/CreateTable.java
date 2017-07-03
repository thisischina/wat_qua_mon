package com.hd.ibus.util.dataUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;

import com.hd.ibus.pojo.IbusExist;
import com.hd.ibus.service.impl.IbusExistServiceImpl;
import com.hd.ibus.util.DateUtils;
import com.hd.ibus.util.Helper;

public class CreateTable {

	/**
	 * @param args
	 * @return 
	 */
	
	
	public static String create(String sql,IbusExistServiceImpl ibusExistService,int gatewayId,String tableName){
		
		String JDriver = "com.mysql.jdbc.Driver";


		String conURL = "jdbc:mysql://localhost:3306/ibus?user=root2&password=1234";


		try {
			// 加载JDBC驱动程序
			Class.forName(JDriver);
		} catch (java.lang.ClassNotFoundException e) {
			System.out.println("无法加载JDBC驱动程序" + e.getMessage());
		}
		Connection con = null;
		Statement s = null;
		
		try {
			// 连接数据库URL
			con = DriverManager.getConnection(conURL);
			// 建立Statement类对象
			s = con.createStatement();
			s.executeUpdate(sql); // 执行SQL语句
			System.out.println("创建表成功！");
			
			IbusExist ibusExist = new IbusExist();
			ibusExist.setGatewayId(gatewayId);
			ibusExist.setTableName(tableName);
			String result = ibusExistService.saveExist(ibusExist);
			if(result.equals("true")){
				Map<String,Object> map = Helper.map;
				map.put(tableName, 1);
			}
			
			return result;
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			return "false";
		} finally {
			try {
				if (s != null) {
					s.close();
					s = null;
				}
				if (con != null) {
					con.close(); // 关闭与数据库的连接
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	//分表查询封装
	public static String GetTableName(String dtStart, String dtEnd,String port){
		//ibus_tpvi_9001_201610261051
		String startDate = "2016-10-26 10:51:00";
		
		if(new Date().getTime()<DateUtils.formatStringToDate(dtEnd, "yyyy-MM-dd HH:mm:ss").getTime()){
			dtEnd = DateUtils.formatDateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
			System.out.println(dtEnd+" > "+new Date());
		}
		
		String dtStartStr = dtStart.substring(0, 10).replaceAll("-","").replaceAll(":","").replaceAll(" ","");
		String dtEndStr = dtEnd.substring(0, 10).replaceAll("-","").replaceAll(":","").replaceAll(" ","");
		
		//如果查询时间范围就一个表
        if(dtStart.substring(0, 10).equals(dtEnd.substring(0, 10))){
            return "ibus_tpvi_"+port+"_" + dtStart.substring(0, 10).replaceAll("-","");
            
        //查询时间范围有多个表
        }else{
        	String TableName = "";
        	if(Helper.map.get("ibus_tpvi_"+port+"_" +dtStartStr)!=null){
        		TableName = "(SELECT IFNULL(tp1,0) tp1,IFNULL(tp2,0) tp2,IFNULL(tp3,0) tp3,IFNULL(tp4,0) tp4,IFNULL(tp5,0) tp5,IFNULL(tp6,0) tp6,IFNULL(tp7,0) tp7,IFNULL(tp8,0) tp8,IFNULL(tp9,0) tp9,IFNULL(va,0) va,IFNULL(vb,0) vb,IFNULL(vc,0) vc,"
		+"IFNULL(ia,0) ia,IFNULL(ib,0) ib,IFNULL(ic,0) ic,gateway_id,node_id,ac_time FROM ibus_tpvi_9001_" + dtStartStr;
        	}
            Date dt = DateUtils.addDay(DateUtils.formatStringToDate(dtStart, "yyyy-MM-dd"), 1);
           
            //按分钟分表，每循环一次，开始时间加一分钟
            while(!DateUtils.formatDateToString(dt, "yyyyMMdd").equals(dtEndStr))
            {
            	//System.out.println(DateUtils.formatDateToString(dt, "yyyyMMdd"));
            	//System.out.println(dtEnd.substring(0, 16).replaceAll("-",""));
            	
            	if(Helper.map.get("ibus_tpvi_"+port+"_" +  DateUtils.formatDateToString(dt, "yyyyMMdd"))!=null){
            		if(TableName.length()==0){
            			TableName += " (SELECT IFNULL(tp1,0) tp1,IFNULL(tp2,0) tp2,IFNULL(tp3,0) tp3,IFNULL(tp4,0) tp4,IFNULL(tp5,0) tp5,IFNULL(tp6,0) tp6,IFNULL(tp7,0) tp7,IFNULL(tp8,0) tp8,IFNULL(tp9,0) tp9,IFNULL(va,0) va,IFNULL(vb,0) vb,IFNULL(vc,0) vc,"
		+"IFNULL(ia,0) ia,IFNULL(ib,0) ib,IFNULL(ic,0) ic,gateway_id,node_id,ac_time FROM ibus_tpvi_"+port+"_" +  DateUtils.formatDateToString(dt, "yyyyMMdd");
            		}else{
            			TableName += " UNION ALL SELECT IFNULL(tp1,0) tp1,IFNULL(tp2,0) tp2,IFNULL(tp3,0) tp3,IFNULL(tp4,0) tp4,IFNULL(tp5,0) tp5,IFNULL(tp6,0) tp6,IFNULL(tp7,0) tp7,IFNULL(tp8,0) tp8,IFNULL(tp9,0) tp9,IFNULL(va,0) va,IFNULL(vb,0) vb,IFNULL(vc,0) vc,"
		+"IFNULL(ia,0) ia,IFNULL(ib,0) ib,IFNULL(ic,0) ic,gateway_id,node_id,ac_time FROM ibus_tpvi_"+port+"_" +  DateUtils.formatDateToString(dt, "yyyyMMdd");
            		}
            		
            	}
                
                dt = DateUtils.addDay(dt,1);
                System.out.println(Helper.map.get("ibus_tpvi_"+port+"_" +  DateUtils.formatDateToString(dt, "yyyyMMdd")));
            }
            if(Helper.map.get("ibus_tpvi_"+port+"_" +  DateUtils.formatDateToString(dt, "yyyyMMdd"))!=null){
            	if(TableName.length()!=0){
            		TableName += " UNION ALL SELECT IFNULL(tp1,0) tp1,IFNULL(tp2,0) tp2,IFNULL(tp3,0) tp3,IFNULL(tp4,0) tp4,IFNULL(tp5,0) tp5,IFNULL(tp6,0) tp6,IFNULL(tp7,0) tp7,IFNULL(tp8,0) tp8,IFNULL(tp9,0) tp9,IFNULL(va,0) va,IFNULL(vb,0) vb,IFNULL(vc,0) vc,"
		+"IFNULL(ia,0) ia,IFNULL(ib,0) ib,IFNULL(ic,0) ic,gateway_id,node_id,ac_time FROM ibus_tpvi_"+port+"_" + DateUtils.formatDateToString(dt, "yyyyMMdd") + ") AS T";
            	}else{
            		TableName += "(SELECT IFNULL(tp1,0) tp1,IFNULL(tp2,0) tp2,IFNULL(tp3,0) tp3,IFNULL(tp4,0) tp4,IFNULL(tp5,0) tp5,IFNULL(tp6,0) tp6,IFNULL(tp7,0) tp7,IFNULL(tp8,0) tp8,IFNULL(tp9,0) tp9,IFNULL(va,0) va,IFNULL(vb,0) vb,IFNULL(vc,0) vc,"
		+"IFNULL(ia,0) ia,IFNULL(ib,0) ib,IFNULL(ic,0) ic,gateway_id,node_id,ac_time FROM ibus_tpvi_"+port+"_" + DateUtils.formatDateToString(dt, "yyyyMMdd") + ") AS T";
            	}
            }else if(TableName.length()!=0){
            	TableName += ") AS T";
            }
            System.out.println("TableName"+TableName);
            return TableName;
        }
    }
	
	
	public static void main(String[] args) {
		String sql = "SELECT * FROM " + GetTableName("2016-10-26 10:50:00","2016-11-26 10:53:00","9001");
		System.out.println("sql:"+sql);
	}

}
