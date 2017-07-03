package com.hd.ibus.util.dataUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.hd.ibus.pojo.IbusGateway;
import com.hd.ibus.service.impl.IbusExistServiceImpl;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.DateUtils;
import com.hd.ibus.util.PropertiesUtils;
import com.hd.ibus.util.dataUtil.DBUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DataSynchronizationTimer2 {
	private static String sttd = PropertiesUtils.getValue(Config.CONFIG, "sttd");
	
	 // 定时器   用于生成监测数据表，监测数据按网关和日分表，每个网关每天一张数据表
    public static void timer4(final List<IbusGateway> list,final IbusExistServiceImpl ibusExistService) throws ParseException {   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR, 1);
		Date date = cal.getTime();
		String time3 = sdf.format(date);
		String time2 = sdf2.format(sdf.parse(time3));
        Date time = sdf2.parse(time2);
        
//        Calendar calendar = Calendar.getInstance();  
//        calendar.setTime(new Date());
//        calendar.add(Calendar.MINUTE, 1);
//        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
       
        Timer timer = new Timer();  
        timer.schedule(new TimerTask() {  
            public void run() {  
            	 Connection conn = null;
                 Connection conn2 = null;
                 try {
                 conn = DBUtil.getConn2();
                 conn2 = DBUtil.getConn();
                 
                
					
	                 try{
	                 	insertOperation(conn,conn2);
	                  }catch(Exception e){
	                 	 System.out.println("日志表异常");
	                  }
	                 try{
	                	insertProject(conn,conn2);
	                 }catch(Exception e){
	                	 System.out.println("项目表异常");
	                 }
	                 try{
	                	 insertGateway(conn,conn2);
	                 }catch(Exception e){
	                	 System.out.println("网关表异常");
	                 }
	                 try{
	                	 insertGroup(conn,conn2);
	                 }catch(Exception e){
	                	 System.out.println("群组表异常");
	                 }
	                 try{
	                	 insertNode(conn,conn2);
	                 }catch(Exception e){
	                	 System.out.println("节点表异常");
	                 }
	                 try{
	                	 insertUser(conn,conn2);
	                 }catch(Exception e){
	                	 System.out.println("用户表异常");
	                 }
	                 try{
	                	 insertExcel(conn,conn2);
	                 }catch(Exception e){
	                	 System.out.println("excel表异常");
	                 }
	                 try{
	                	 insertExist(conn,conn2);
	                 }catch(Exception e){
	                	 System.out.println("exist表异常");
	                 }
	                 try{
	                	 insertWarn(conn,conn2);
	                 }catch(Exception e){
	                	 System.out.println("告警表异常");
	                 }
	                 try{
	                	 checkTable(conn,conn2);
	                 }catch(Exception e){
	                	 System.out.println("检查表是否存在异常");
	                 }
	                 conn2.setAutoCommit(false);
	                 try{
	                	 insertTpvi(conn,conn2);
	                 }catch(Exception e){
	                	 System.out.println("插入数据异常");
	                 }
	                 conn2.setAutoCommit(true);
                 } catch (Exception e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
            	System.out.println(new Date()+"我在执行");
            }  
        }, time, 1000 * 60 * 60 );// 这里设定将延时每天固定执行  
    	//}, time, 1000*60);// 这里设定将延时每天固定执行  
    } 
    
    //创表
    public static void checkTable(Connection conn,Connection conn2){
    	Statement stat = null;
        PreparedStatement stat2 = null;
        ResultSet rst = null;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date); 
        cal.add(Calendar.HOUR, -1);
        date = cal.getTime();
        String sql = "select * from ibus_gateway";
        try {
			stat = (Statement) conn.createStatement();
			rst = stat.executeQuery(sql);
			while(rst.next()){
				String tableName = "ibus_tpvi_"+sttd+"_"+rst.getInt("gateway_port")+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
				String sql2 = "select * from "+tableName+" limit 0,1";
				stat2 = (PreparedStatement) conn2.prepareStatement(sql2);
				try{
					stat2.execute();
				}catch(Exception e){
					System.out.println(tableName+"表不存在!开始创建!");
					String auto_increment = DateUtils.formatDateToString(date, "yyyyMMdd")+"0000000";
					String sql3 = createTable(tableName,auto_increment);
					stat2 = (PreparedStatement) conn2.prepareStatement(sql3);
					try{
						stat2.execute();
					}catch(Exception e1){
						System.out.println("创建表"+tableName+"失败!");
					}
				}
				
			}
			
		} catch (SQLException e1) {
			System.out.println("请检查本地网关表数据是否异常!");
		}
        if(stat!=null){
         	 try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
   	
   	if(stat2!=null){
        	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      }
   	if(rst!=null){
        	 try {
        		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      }
    }
    
    private static String createTable(String tableName,String auto_increment){
    	String sql ="CREATE TABLE "+tableName+" ("
				  +"`id` bigint(15) NOT NULL AUTO_INCREMENT,"
				  +"`tp1` float(11,1) DEFAULT NULL,"
				  +"`tp2` float(11,1) DEFAULT NULL,"
				  +"`tp3` float(11,1) DEFAULT NULL,"
				  +"`tp4` float(11,1) DEFAULT NULL,"
				  +"`tp5` float(11,1) DEFAULT NULL,"
				  +"`tp6` float(11,1) DEFAULT NULL,"
				  +"`tp7` float(11,1) DEFAULT NULL,"
				  +"`tp8` float(11,1) DEFAULT NULL,"
				  +"`tp9` float(11,1) DEFAULT NULL,"
				  +"`va` float(11,2) DEFAULT NULL,"
				  +"`vb` float(11,2) DEFAULT NULL,"
				  +"`vc` float(11,2) DEFAULT NULL,"
				  +"`ia` float(11,3) DEFAULT NULL,"
				  +"`ib` float(11,3) DEFAULT NULL,"
				  +"`ic` float(11,3) DEFAULT NULL,"
				  +"`gateway_id` int(11) NOT NULL,"
				  +"`node_id` int(11) NOT NULL,"
				  +"`ac_time` datetime(3) NOT NULL,"
				  +"PRIMARY KEY (`id`)"
				  +") ENGINE=InnoDB AUTO_INCREMENT="+auto_increment+" DEFAULT CHARSET=utf8;"; 
    	return sql;
    }
    
    //数据表
    public static void insertTpvi(Connection conn,Connection conn2) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH");
    	
    	
    	Statement stat = null;
        PreparedStatement stat2 = null;
        Statement stat3 = null;
        ResultSet rst = null;
        ResultSet rst2 = null;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        String time3 = sdf.format(date);
        String endTime = sdf2.format(sdf.parse(time3));
        cal.setTime(date); 
        cal.add(Calendar.HOUR, -1);
        date = cal.getTime();
        String startTime = sdf2.format(sdf.parse(sdf.format(date)));
        String sql = "select * from ibus_gateway";
        try {
			stat = (Statement) conn.createStatement();
			stat3 = (Statement) conn.createStatement();
			rst = stat.executeQuery(sql);
			while(rst.next()){
				String tableName = "ibus_tpvi_"+rst.getInt("gateway_port")+"_"+DateUtils.formatDateToString(date, "yyyyMMdd");
				String groupSql = "select * from "+tableName+" where ac_time>'"+startTime+"' and ac_time<'"+endTime+"'";
		        String groupSql2 = "insert into "+tableName+"(id,tp1,tp2,tp3,tp4,tp5,tp6,tp7,tp8,tp9,va,vb,vc,ia,ib,ic,"
		        		+ "gateway_id,node_id,ac_time) values(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?)";
		        System.out.println(groupSql);
		        try{
		        	rst2 = stat3.executeQuery(groupSql);
		        }catch(Exception e3){
		        	System.out.println("为空跳过");
		        	continue;
		        }
				stat2 = (PreparedStatement) conn2.prepareStatement(groupSql2);
				int batchCounter=0;
		        while(rst2.next()){
		        	//System.out.println("1");
		        	stat2.setLong(1, rst2.getLong("id"));
		        	//System.out.println("4");
	            	stat2.setFloat(2, rst2.getFloat("tp1")); 
	            	//System.out.println("5");
	            	stat2.setFloat(3, rst2.getFloat("tp2")); 
	            	
	            	stat2.setFloat(4, rst2.getFloat("tp3")); 
	            	stat2.setFloat(5, rst2.getFloat("tp4")); 
	            	stat2.setFloat(6, rst2.getFloat("tp5")); 
	            	stat2.setFloat(7, rst2.getFloat("tp6")); 
	            	stat2.setFloat(8, rst2.getFloat("tp7")); 
	            	stat2.setFloat(9, rst2.getFloat("tp8")); 
	            	stat2.setFloat(10, rst2.getFloat("tp9")); 
	            	stat2.setFloat(11, rst2.getFloat("va")); 
	            	stat2.setFloat(12, rst2.getFloat("vb")); 
	            	stat2.setFloat(13, rst2.getFloat("vc")); 
	            	stat2.setFloat(14, rst2.getFloat("ia")); 
	            	stat2.setFloat(15, rst2.getFloat("ib")); 
	            	stat2.setFloat(16, rst2.getFloat("ic")); 
	            	stat2.setInt(17, rst2.getInt("gateway_id"));
	            	stat2.setInt(18, rst2.getInt("node_id"));
	            	//System.out.println("2");;
	            	Date st = rst2.getTimestamp("ac_time");
	            	System.out.println("----------------------"+st);
	            	stat2.setTimestamp(19, rst2.getTimestamp("ac_time"));
	            	//System.out.println("3");
	            	
	            	stat2.addBatch(); 
	                 batchCounter++; 
	                 if (batchCounter % 1000 == 0) { // 1万条数据一提交
	                	 stat2.executeBatch(); 
	                	 stat2.clearBatch(); 
	                	 conn2.commit(); 
	                 }  
//	            	try{
//	            		stat2.executeUpdate();
//	            		System.out.println("插入成功!"+rst.getString("id"));  
//	            	}catch(Exception e){
//	            		System.out.println("插入失败");
//	            		//e.printStackTrace();
//	            	}
		        }
		        stat2.executeBatch(); 
           	 	stat2.clearBatch(); 
           	 	conn2.commit(); 
		        
			}
			
		} catch (SQLException e1) {
			System.out.println("请检查本地tpvi数据是否异常!");
			e1.printStackTrace();
		}
    	if(stat!=null){
          	 try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	if(stat2!=null){
         	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    	if(rst!=null){
         	 try {
         		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }

    	if(stat3!=null){
         	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    	if(rst2!=null){
         	 try {
         		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
        
    }
    
    //告警表
    public static void insertWarn(Connection conn,Connection conn2){
    	Statement stat = null;
        PreparedStatement stat2 = null;
        ResultSet rst = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        String endTime = sdf.format(date);
        cal.setTime(date); 
        cal.add(Calendar.HOUR, -1);
        date = cal.getTime();
        String startTime = sdf.format(date);
        String groupSql = "select * from ibus_warn where ac_time>'"+startTime+"' and ac_time<'"+endTime+"'";
        String groupSql2 = "insert into ibus_warn_"+sttd+"(id,tp1,tp2,tp3,tp4,tp5,tp6,tp7,tp8,tp9,va,vb,vc,ia,ib,ic,"
        		+ "gateway_id,node_id,ac_time,warn,state) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            stat=(Statement) conn.createStatement();
            stat2 = (PreparedStatement) conn2.prepareStatement(groupSql2);
            rst = stat.executeQuery(groupSql);
            while(rst.next()){
            	stat2.setInt(1, rst.getInt("id"));
            	stat2.setFloat(2, rst.getFloat("tp1")); 
            	stat2.setFloat(3, rst.getFloat("tp2")); 
            	stat2.setFloat(4, rst.getFloat("tp3")); 
            	stat2.setFloat(5, rst.getFloat("tp4")); 
            	stat2.setFloat(6, rst.getFloat("tp5")); 
            	stat2.setFloat(7, rst.getFloat("tp6")); 
            	stat2.setFloat(8, rst.getFloat("tp7")); 
            	stat2.setFloat(9, rst.getFloat("tp8")); 
            	stat2.setFloat(10, rst.getFloat("tp9")); 
            	stat2.setFloat(11, rst.getFloat("va")); 
            	stat2.setFloat(12, rst.getFloat("vb")); 
            	stat2.setFloat(13, rst.getFloat("vc")); 
            	stat2.setFloat(14, rst.getFloat("ia")); 
            	stat2.setFloat(15, rst.getFloat("ib")); 
            	stat2.setFloat(16, rst.getFloat("ic")); 
            	stat2.setInt(17, rst.getInt("gateway_id"));
            	stat2.setInt(18, rst.getInt("node_id"));
            	stat2.setTimestamp(19, rst.getTimestamp("ac_time"));
            	stat2.setString(20, rst.getString("warn"));
            	stat2.setInt(21, rst.getInt("state"));
            	
            	try{
            		stat2.executeUpdate();
            		System.out.println("插入成功!"+rst.getString("warn"));  
            	}catch(Exception e){
            		System.out.println("告警插入失败");
            	}
            }
           
            System.out.println("-------------------------");	
            
            
        }catch(SQLException e){
        	System.out.println("告警表错误");
        }
    	
    	if(stat!=null){
          	 try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	if(stat2!=null){
         	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    	if(rst!=null){
         	 try {
         		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
        
    }
    
    //存在表
    public static void insertExist(Connection conn,Connection conn2){
    	Statement stat = null;
        PreparedStatement stat2 = null;
        ResultSet rst = null;
        String groupSql = "select * from ibus_exist";
        String groupSql2 = "insert into ibus_exist_"+sttd+"(id,gateway_id,table_name) values(?,?,?)";
        String groupSql3 = "update ibus_exist_"+sttd+" set gateway_id=?,table_name=? where id=?";
        try{
            stat=(Statement) conn.createStatement();
            rst = stat.executeQuery(groupSql);
            while(rst.next()){
            	 stat2 = (PreparedStatement) conn2.prepareStatement(groupSql2);
            	stat2.setInt(1, rst.getInt("id"));
            	stat2.setInt(2, rst.getInt("gateway_id"));
            	stat2.setString(3, rst.getString("table_name"));
            	try{
            		stat2.executeUpdate();
            		System.out.println("插入成功!"+rst.getString("table_name"));  
            	}catch(Exception e){
            		System.out.println("exist插入失败");
            		stat2 = (PreparedStatement) conn2.prepareStatement(groupSql3);
            		stat2.setInt(3, rst.getInt("id"));
                	stat2.setInt(1, rst.getInt("gateway_id"));
                	stat2.setString(2, rst.getString("table_name"));
                	try{
                		stat2.executeUpdate();
                		System.out.println("exist更新成功!"+rst.getString("table_name"));  
                	}catch(Exception e1){
                		System.out.println("exist更新失败");
                	}
            	}
            }
           
            System.out.println("-------------------------");	
            
            
        }catch(SQLException e){
        	System.out.println("exsit表错误");
        }
    	
    	if(stat!=null){
          	 try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	if(stat2!=null){
         	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    	if(rst!=null){
         	 try {
         		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
        
    }
    
    //批量导入导出
    public static void insertExcel(Connection conn,Connection conn2){
    	Statement stat = null;
        PreparedStatement stat2 = null;
        ResultSet rst = null;
        String groupSql = "select * from excel_url";
        String groupSql2 = "insert into excel_url_"+sttd+"(id,fileurl,insertdate,filename) values(?,?,?,?)";
        String groupSql3 = "update excel_url_"+sttd+" set fileurl=?,insertdate=?,filename=? where id=? ";
        try{
            stat=(Statement) conn.createStatement();
            rst = stat.executeQuery(groupSql);
            while(rst.next()){
            	 stat2 = (PreparedStatement) conn2.prepareStatement(groupSql2);
            	stat2.setInt(1, rst.getInt("id"));
            	stat2.setString(2, rst.getString("fileurl"));
            	stat2.setDate(3, rst.getDate("insertdate"));
            	stat2.setString(4, rst.getString("filename"));
            	
            	try{
            		stat2.executeUpdate();
            		System.out.println("插入成功!"+rst.getString("id"));  
            	}catch(Exception e){
            		System.out.println("excel插入失败");
            		stat2 = (PreparedStatement) conn2.prepareStatement(groupSql3);
            		stat2.setInt(4, rst.getInt("id"));
                	stat2.setString(1, rst.getString("fileurl"));
                	stat2.setDate(2, rst.getDate("insertdate"));
                	stat2.setString(3, rst.getString("filename"));
                	try{
                		stat2.executeUpdate();
                		System.out.println("插入成功!"+rst.getString("id"));  
                	}catch(Exception e1){
                		System.out.println("更新失败");
                	}
            	}
            }
           
            System.out.println("-------------------------");	
            
            
        }catch(SQLException e){
        	System.out.println("excel错误");
        }
    	
    	if(stat!=null){
          	 try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	if(stat2!=null){
         	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    	if(rst!=null){
         	 try {
         		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
        
    }
    
    //用户
    public static void insertUser(Connection conn,Connection conn2){
    	Statement stat = null;
        PreparedStatement stat2 = null;
        ResultSet rst = null;
        String groupSql = "select * from ibus_user";
        String groupSql2 = "insert into ibus_user_"+sttd+"(id,user_name,password,tel,"
        		+ "email,role) values(?,?,?,?,?,?)";
        String groupSql3 = "update ibus_user_"+sttd+" set user_name=?,password=?,tel=?,email=?,role=? where id=?";
        try{
            stat=(Statement) conn.createStatement();
            rst = stat.executeQuery(groupSql);
            while(rst.next()){
            	stat2 = (PreparedStatement) conn2.prepareStatement(groupSql2);
            	stat2.setInt(1, rst.getInt("id"));
            	stat2.setString(2, rst.getString("user_name"));
            	stat2.setString(3, rst.getString("password"));
            	stat2.setString(4, rst.getString("tel"));
            	stat2.setString(5, rst.getString("email"));
            	stat2.setInt(6, rst.getInt("role"));
            	try{
            		stat2.executeUpdate();
            		System.out.println("插入成功!"+rst.getString("user_name"));  
            	}catch(Exception e){
            		System.out.println("用户插入失败");
            		stat2 = (PreparedStatement) conn2.prepareStatement(groupSql3);
            		stat2.setInt(6, rst.getInt("id"));
                	stat2.setString(1, rst.getString("user_name"));
                	stat2.setString(2, rst.getString("password"));
                	stat2.setString(3, rst.getString("tel"));
                	stat2.setString(4, rst.getString("email"));
                	stat2.setInt(5, rst.getInt("role"));
                	try{
                		stat2.executeUpdate();
                		System.out.println("用户更新成功!"+rst.getString("user_name"));  
                	}catch(Exception e1){
                		System.out.println("用户更新失败");
                	}
            	}
            }
           
            System.out.println("-------------------------");	
            
            
        }catch(SQLException e){
        	System.out.println("用户错误");
        }
    	
    	if(stat!=null){
          	 try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	if(stat2!=null){
         	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    	if(rst!=null){
         	 try {
         		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
        
    }
    
    //节点
    public static void insertNode(Connection conn,Connection conn2){
    	Statement stat = null;
        PreparedStatement stat2 = null;
        ResultSet rst = null;
        String groupSql = "select * from ibus_node";
        String groupSql2 = "insert into ibus_node_"+sttd+"(id,node_name,node_address,node_type,"
        		+ "node_type2,node_state,y,x,v_mix,v_max,i_max,tp_max,group_id,gateway_id,"
        		+ "node_isonline) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String groupSql3 = "update ibus_node_"+sttd+" set node_name=?,node_address=?,node_type=?,node_type2=?,"
        		+ "node_state=?,y=?,x=?,v_mix=?,v_max=?,i_max=?,tp_max=?,group_id=?,gateway_id=?,node_isonline=?"
        		+ " where id=?";
        try{
            stat=(Statement) conn.createStatement();
            rst = stat.executeQuery(groupSql);
            while(rst.next()){
            	stat2 = (PreparedStatement) conn2.prepareStatement(groupSql2);
            	stat2.setInt(1, rst.getInt("id"));
            	stat2.setString(2, rst.getString("node_name"));
            	stat2.setInt(3, rst.getInt("node_address"));
            	stat2.setInt(4, rst.getInt("node_type"));
            	stat2.setInt(5, rst.getInt("node_type2"));
            	stat2.setInt(6, rst.getInt("node_state"));
            	stat2.setInt(7, rst.getInt("y"));
            	stat2.setInt(8, rst.getInt("x"));
            	stat2.setFloat(9, rst.getFloat("v_mix"));
            	stat2.setFloat(10, rst.getFloat("v_max"));
            	stat2.setFloat(11, rst.getFloat("i_max"));
            	stat2.setFloat(12, rst.getFloat("tp_max"));
            	stat2.setInt(13, rst.getInt("group_id"));
            	stat2.setInt(14, rst.getInt("gateway_id"));
            	stat2.setInt(15, rst.getInt("node_isonline"));
            	try{
            		stat2.executeUpdate();
            		System.out.println("插入成功!"+rst.getString("node_name"));  
            	}catch(Exception e){
            		System.out.println("节点插入失败");
            		stat2 = (PreparedStatement) conn2.prepareStatement(groupSql3);
            		stat2.setInt(15, rst.getInt("id"));
                	stat2.setString(1, rst.getString("node_name"));
                	stat2.setInt(2, rst.getInt("node_address"));
                	stat2.setInt(3, rst.getInt("node_type"));
                	stat2.setInt(4, rst.getInt("node_type2"));
                	stat2.setInt(5, rst.getInt("node_state"));
                	stat2.setInt(6, rst.getInt("y"));
                	stat2.setInt(7, rst.getInt("x"));
                	stat2.setFloat(8, rst.getFloat("v_mix"));
                	stat2.setFloat(9, rst.getFloat("v_max"));
                	stat2.setFloat(10, rst.getFloat("i_max"));
                	stat2.setFloat(11, rst.getFloat("tp_max"));
                	stat2.setInt(12, rst.getInt("group_id"));
                	stat2.setInt(13, rst.getInt("gateway_id"));
                	stat2.setInt(14, rst.getInt("node_isonline"));
                	try{
                		stat2.executeUpdate();
                		System.out.println("节点更新成功!"+rst.getString("node_name"));  
                	}catch(Exception e1){
                		System.out.println("节点更新失败");
                	}
            		
            	}
            }
           
            System.out.println("-------------------------");	
            
            
        }catch(SQLException e){
        	System.out.println("节点错误");
        }
    	
    	if(stat!=null){
          	 try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	if(stat2!=null){
         	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    	if(rst!=null){
         	 try {
         		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
        
    }
    
    //群组
    public static void insertGroup(Connection conn,Connection conn2){
    	Statement stat = null;
        PreparedStatement stat2 = null;
        ResultSet rst = null;
        String groupSql = "select * from ibus_group";
        String groupSql2 = "insert into ibus_group_"+sttd+"(id,group_name,gateway_id,group_state,file_url,"
        		+ "file_name) values(?,?,?,?,?,?)";
        String groupSql3 = "update ibus_group_"+sttd+" set group_name=?,gateway_id=?,group_state=?,file_url=?,"
        		+ "file_name=? where id=?";
        try{
            stat=(Statement) conn.createStatement();
            rst = stat.executeQuery(groupSql);
            while(rst.next()){
            	stat2 = (PreparedStatement) conn2.prepareStatement(groupSql2);
            	stat2.setInt(1, rst.getInt("id"));
            	stat2.setString(2, rst.getString("group_name"));
            	stat2.setInt(3, rst.getInt("gateway_id"));
            	stat2.setInt(4, rst.getInt("group_state"));
            	stat2.setString(5, rst.getString("file_url"));
            	stat2.setString(6, rst.getString("file_name"));
            	//System.out.println("==================="+rst.getInt("node_count"));
            	try{
            		stat2.executeUpdate();
            		System.out.println("插入成功!"+rst.getString("group_name"));  
            	}catch(Exception e){
            		System.out.println("群组插入失败");
            		stat2 = (PreparedStatement) conn2.prepareStatement(groupSql3);
            		stat2.setInt(6, rst.getInt("id"));
                	stat2.setString(1, rst.getString("group_name"));
                	stat2.setInt(2, rst.getInt("gateway_id"));
                	stat2.setInt(3, rst.getInt("group_state"));
                	stat2.setString(4, rst.getString("file_url"));
                	stat2.setString(5, rst.getString("file_name"));
                	try{
                		stat2.executeUpdate();
                		System.out.println("群组更新成功!"+rst.getString("group_name"));  
                	}catch(Exception e1){
                		System.out.println("群组更新失败");
                	}
            	}
            }
           
            System.out.println("-------------------------");	
            
            
        }catch(SQLException e){
        	System.out.println("群组错误");
        }
    	
    	if(stat!=null){
          	 try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	if(stat2!=null){
         	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    	if(rst!=null){
         	 try {
         		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
        
    }
    
    //网关
    public static void insertGateway(Connection conn,Connection conn2){
    	Statement stat = null;
        PreparedStatement stat2 = null;
        ResultSet rst = null;
        String sql = "select * from ibus_gateway";
        String sql2 = "insert into ibus_gateway_"+sttd+"(id,gateway_name,gateway_ip,gateway_port,project_id," +
        		"is_online,node_count) values(?,?,?,?,?,?,?)";
        String sql3 = "update ibus_gateway_"+sttd+" set gateway_name=?,gateway_ip=?,gateway_port=?,project_id=?,"
        		+ "is_online=?,node_count=? where id=?";
    	try{
            stat=(Statement) conn.createStatement();
            rst = stat.executeQuery(sql);
            while(rst.next()){
            	stat2 = (PreparedStatement) conn2.prepareStatement(sql2);
            	stat2.setInt(1, rst.getInt("id"));
            	stat2.setString(2, rst.getString("gateway_name"));
            	stat2.setString(3, rst.getString("gateway_ip"));
            	stat2.setInt(4, rst.getInt("gateway_port"));
            	stat2.setInt(5, rst.getInt("project_id"));
            	stat2.setInt(6, rst.getInt("is_online"));
            	stat2.setInt(7, rst.getInt("node_count"));
            	System.out.println("==================="+rst.getInt("node_count"));
            	try{
            		stat2.executeUpdate();
            		System.out.println("插入成功!"+rst.getString("gateway_name"));  
            	}catch(Exception e){
            		System.out.println("网关插入失败");
            		stat2 = (PreparedStatement) conn2.prepareStatement(sql3);
            		stat2.setInt(7, rst.getInt("id"));
                	stat2.setString(1, rst.getString("gateway_name"));
                	stat2.setString(2, rst.getString("gateway_ip"));
                	stat2.setInt(3, rst.getInt("gateway_port"));
                	stat2.setInt(4, rst.getInt("project_id"));
                	stat2.setInt(5, rst.getInt("is_online"));
                	stat2.setInt(6, rst.getInt("node_count"));
                	try{
                		stat2.executeUpdate();
                		System.out.println("插入网关!"+rst.getString("gateway_name"));  
                	}catch(Exception e1){
                		System.out.println("更新网关失败");
                	}
            	}
            }
           
            System.out.println("-------------------------");	
            
            
        }catch(SQLException e){
        	System.out.println("网关错误");
        }
    	
    	if(stat!=null){
          	 try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	if(stat2!=null){
         	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    	if(rst!=null){
         	 try {
         		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    }
    
  //项目
    public static void insertOperation(Connection conn,Connection conn2){
    	Statement stat = null;
        PreparedStatement stat2 = null;
        ResultSet rst = null;
        String sql = "select * from ibus_operation";
        String sql2 = "insert into ibus_operation_"+sttd+"(id,user_id,operation_type,operation_name,ac_time) values(?,?,?,?,?)";
        String sql3 = "update ibus_operation_"+sttd+" set user_id=?,operation_type=?,operation_name=?,ac_time=? where id=?";
    	try{
            stat=(Statement) conn.createStatement();
            rst = stat.executeQuery(sql);
            while(rst.next()){
            	stat2 = (PreparedStatement) conn2.prepareStatement(sql2);
            	stat2.setInt(1, rst.getInt("id"));
            	stat2.setInt(2, rst.getInt("user_id"));
            	stat2.setString(3, rst.getString("operation_type"));
            	stat2.setString(4, rst.getString("operation_name"));
            	stat2.setDate(5, rst.getDate("ac_time"));
            	try{
            		stat2.executeUpdate();
            		System.out.println("插入成功!"+rst.getString("operation_name"));  
            	}catch(Exception e){
            		System.out.println("日志插入失败");
            		stat2 = (PreparedStatement) conn2.prepareStatement(sql3);
            		stat2.setInt(5, rst.getInt("id"));
                	stat2.setInt(1, rst.getInt("user_id"));
                	stat2.setString(2, rst.getString("operation_type"));
                	stat2.setString(3, rst.getString("operation_name"));
                	stat2.setDate(4, rst.getDate("ac_time"));
                	try{
                		stat2.executeUpdate();
                		System.out.println("更新日志成功!"+rst.getString("operation_name"));  
                	}catch(Exception e1){
                		System.out.println("同步更新日志失败");
                	}
            	}
            }
           
            System.out.println("-------------------------");	
            
            
        }catch(SQLException e){
        	System.out.println("日志插入错误");
        }
    	
    	if(stat!=null){
          	 try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	if(stat2!=null){
         	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    	if(rst!=null){
         	 try {
         		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    }
    
    //项目
    public static void insertProject(Connection conn,Connection conn2){
    	Statement stat = null;
        PreparedStatement stat2 = null;
        ResultSet rst = null;
        String sql = "select * from ibus_project";
        String sql2 = "insert into ibus_project_"+sttd+"(id,project_user,project_address,project_name,project_maker," +
        		"project_name2) values(?,?,?,?,?,?)";
        String sql3 = "update ibus_project_"+sttd+" set project_user=?,project_address=?,project_name=?,project_maker=?"
        		+ ",project_name2=? where id=?";
    	try{
            stat=(Statement) conn.createStatement();
            rst = stat.executeQuery(sql);
            while(rst.next()){
            	stat2 = (PreparedStatement) conn2.prepareStatement(sql2);
            	stat2.setInt(1, rst.getInt("id"));
            	stat2.setString(2, rst.getString("project_user"));
            	stat2.setString(3, rst.getString("project_address"));
            	stat2.setString(4, rst.getString("project_name"));
            	stat2.setString(5, rst.getString("project_maker"));
            	stat2.setString(6, rst.getString("project_name2"));
            	try{
            		stat2.executeUpdate();
            		System.out.println("插入成功!"+rst.getString("project_name"));  
            	}catch(Exception e){
            		System.out.println("项目插入失败");
            		stat2 = (PreparedStatement) conn2.prepareStatement(sql3);
            		stat2.setInt(6, rst.getInt("id"));
                	stat2.setString(1, rst.getString("project_user"));
                	stat2.setString(2, rst.getString("project_address"));
                	stat2.setString(3, rst.getString("project_name"));
                	stat2.setString(4, rst.getString("project_maker"));
                	stat2.setString(5, rst.getString("project_name2"));
                	try{
	                	stat2.executeUpdate();
	            		System.out.println("更新成功!"+rst.getString("project_name")); 
                	}catch(Exception e1){
                		System.out.println("更新失败");
                	}
            	}
            }
           
            System.out.println("-------------------------");	
            
            
        }catch(SQLException e){
        	System.out.println("项目插入错误");
        }
    	
    	if(stat!=null){
          	 try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    	
    	if(stat2!=null){
         	 try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    	if(rst!=null){
         	 try {
         		rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
    }
}
