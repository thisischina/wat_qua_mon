package com.hd.ibus.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hd.ibus.pojo.IbusNode;
import com.hd.ibus.pojo.IbusOperation;
import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.pojo.vo.IbusNodeVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusGatewayService;
import com.hd.ibus.service.IbusNodeService;
import com.hd.ibus.service.IbusOperationService;
import com.hd.ibus.util.DateUtils;
import com.hd.ibus.util.ValidateUtils;

@Controller
@RequestMapping("node")
public class NodeController {

	@Resource
	private IbusNodeService ibusNodeService;
	
	@Resource
	private IbusGatewayService ibusGatewayService;
	
	@Resource
	private IbusOperationService ibusOperationService;

	@RequestMapping("/directAddNode")
	public String index2(HttpServletRequest request,Model model){
		
		return "system/node/addNode";
	}
	
	@RequestMapping("/directAddNodeTest")
	public String directAddNodeTest(HttpServletRequest request,Model model){
		
		return "system/node/addNodeTest";
	}
	
	@RequestMapping("/position")
	public String position(HttpServletRequest request,Model model){
		
		return "system/node/position";
	}
	
	@RequestMapping("/directUpdateNode")
	public String directUpdateNode(HttpServletRequest request,Model model,Integer id){
		IbusNode ibusNode = this.ibusNodeService.findById(id);
		model.addAttribute(ibusNode);
		return "system/node/updateNode";
	}
	
	@RequestMapping("/findList")
	public @ResponseBody DataGridResultInfo findList(HttpServletRequest request,HttpServletResponse response, Integer pageNow,Integer pageSize,IbusNodeVo ibusNodeVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		return this.ibusNodeService.findList(ibusNodeVo, pageNow, pageSize);
	}
	
	@RequestMapping("/saveIbusNode")
	public @ResponseBody String saveIbusNode(HttpServletRequest request,HttpServletResponse response,IbusNodeVo ibusNodeVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		return this.ibusNodeService.saveIbusNode(ibusNodeVo,ibusGatewayService);
	}
	
	@RequestMapping("/nodalPoint")
	public @ResponseBody String nodalPoint(HttpServletRequest request,HttpServletResponse response,Integer  gatewayId)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		
		return this.ibusNodeService.nodalPoint(gatewayId, ibusGatewayService); 
	}
	
	@RequestMapping("/updateIbusNode")
	public @ResponseBody String updateIbusNode(HttpServletRequest request,HttpServletResponse response,IbusNodeVo ibusNodeVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		return this.ibusNodeService.updateIbusNode(ibusNodeVo);
	}
	
	@RequestMapping("/deleteIbusNode")
	public @ResponseBody String deleteIbusNode(HttpServletRequest request,HttpServletResponse response,Integer id)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");		
		return this.ibusNodeService.deleteIbusNode(id);
	}
	
	@RequestMapping("/findById")
	public @ResponseBody IbusNode findById(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("nodeId"));
		
		return this.ibusNodeService.findById(id);
	}
	
	@RequestMapping("/updateByAddress")
	public @ResponseBody String updateByAddress(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		try {
			int nodeState = Integer.parseInt(request.getParameter("nodeState"));
			String nodes = request.getParameter("nodes");
			int gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
			
			
			
			return this.ibusNodeService.updateByAddress(nodeState, nodes,gatewayId);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
	@RequestMapping("/updateIsOnlineByAddress")
	public @ResponseBody String updateIsOnlineByAddress(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		try {
			int nodeIsonline = Integer.parseInt(request.getParameter("nodeIsonline"));
			String nodes = request.getParameter("nodes");
			int gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
			String result = this.ibusNodeService.updateIsOnlineByAddress(nodeIsonline, nodes,gatewayId);
			System.out.println("result:"+result);
			System.out.println("nodeIsonline:"+nodeIsonline);
			System.out.println("gatewayId:"+gatewayId);
			System.out.println("nodes:"+nodes);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		
	}
	
	//根据群组ID获取节点地址
		@RequestMapping("/findNodeByGroupId")
		public @ResponseBody List<IbusNode> findNodeByGroupId(HttpServletRequest request,HttpServletResponse response)
				throws IOException {
			try {
				
				Integer groupId = Integer.parseInt(request.getParameter("groupId"));
				
				return this.ibusNodeService.findListByGroupId(groupId);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			
		}
	
	//根据网关ID获取节点地址
	@RequestMapping("/findNodeByGatewayId")
	public @ResponseBody List<Map> getNodeAddrBygatewayId(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		try {
			
			Integer gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
			
			return this.ibusNodeService.findNodeByGatewayId(gatewayId); 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	//导入解析excel文件
	@RequestMapping("/insertFromExcle")
	public String insertFromExcle(HttpServletRequest request,Model model){
		
		return "system/node/insertFromExcle";
	}
	//导入解析excel文件
	@RequestMapping("uploadTest")
	public @ResponseBody   Map uploadTest(IbusNodeVo ibusNodeVo,MultipartHttpServletRequest  request,HttpServletResponse response) throws Exception {
		
		  try{
		  String updateP = request.getParameter("updateP");
		  System.out.println("======================="+updateP);
		  String fileNameSuffix=null;
	      String fileName= null;
		  MultipartFile mf = request.getFile(updateP);
          fileName=mf.getOriginalFilename();
          String filename2="";
          String disName2="";
          fileNameSuffix=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
          System.out.println("======================="+mf);
          //FileInputStream fis = new FileInputStream(new File("e:/write.xls"));
          InputStream fis = mf.getInputStream();
          Date d11=new Date();
          String date11=DateUtils.getDate(d11, "yyyy-MM-dd HH:mm:ss");
          String disName =Calendar.getInstance().getTimeInMillis()+fileName;
          FileOutputStream fos = new FileOutputStream(new File("e:/"+disName));
          byte[] buff = new byte[1024];
          int len = 0;
          try {
              len = fis.read(buff);
              while (len > 0) {
                  fos.write(buff, 0, len);
                  len = fis.read(buff);
              }
          } catch(Exception e){
        	  e.printStackTrace();
        	  Map result = new HashMap();
        	  result.put("flag", "-1");
        	  result.put('e', "导入异常，请检查系统配置或联系软件开发团队");
        	  return result;
          }
          
          finally {
        	  fos.flush();
              fis.close();
              fos.close();
              System.out.println("===================================文件上传完成");
          }
          List list=ibusNodeService.findAll();
          Workbook rwb=Workbook.getWorkbook(mf.getInputStream());
          Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
          int clos=rs.getColumns();//得到所有的列
          int rows=rs.getRows();//得到所有的行
          Map<String, String> map=new HashMap<String, String>();
          String ids="";
          System.out.println(clos+" rows:"+rows);
           //判断导入的信息，格式、长度、必填项
          List list1 =new ArrayList();
          
        	  
        	   boolean flag=false;
               for (int i = 3; i < rows; i++) {
             	  Map<String,String> map1=new HashMap<String,String>();
             	  
                   for (int j = 0; j < 7; j++) {
                 	  int re=0;
                 	  String question="";
                       //第一个是列数，第二个是行数
                       String node_name=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                       //判断电表编号meter_id格式是否正确，12个字符串
                       if("".equals(node_name)||node_name==null){
                     	
                     	  question="节点名称为空、";
                       }
                       String node_address=rs.getCell(j++, i).getContents();
                       //判断是否为数字
                       if("".equals(node_address)||node_address==null){
                     	  re=1;
                     	  question=question+"节点地址为空、";
                       }
                       
                       String node_type=rs.getCell(j++, i).getContents();
                       System.out.println(node_type);
                       //判断是否为数字
                       if("".equals(node_type)||node_type==null){
                     	  re=1;
                     	  question=question+"节点类型为空、";
                       }
                       String v_mix=rs.getCell(j++, i).getContents();
                       //判断长度是否大于50
                       if("".equals(v_mix)||v_mix==null||!ValidateUtils.isInteger(v_mix)||Integer.parseInt(v_mix)<176){
                     	  re=1;
                     	  question=question+"最小电压不正确、";
                       }
                       String v_max= rs.getCell(j++, i).getContents();
                       //判断类型是否为1或者0
                       if("".equals(v_max)||v_max==null||!ValidateUtils.isInteger(v_max)||Integer.parseInt(v_max)>264){
                           re=1;
                     	  question=question+"最大电压不正确、";
                       }
                       String i_max=rs.getCell(j++, i).getContents();
                       if("".equals(i_max)||i_max==null||!ValidateUtils.isInteger(i_max)||Integer.parseInt(i_max)==0){
                     	  re=1;
                     	  question=question+"最大电流不能为0、";
                       }
                       String tp_max=rs.getCell(j++, i).getContents();
                       if("".equals(tp_max)||tp_max==null||!ValidateUtils.isInteger(tp_max)||Integer.parseInt(tp_max)>70){
                     	  re=1;
                     	  question=question+"温度最高不超过70、";
                       }
                       String group_id=rs.getCell(j++, i).getContents();
                       if("".equals(group_id)||!ValidateUtils.isInteger(group_id)){
                     	  re=1;
                     	  question=question+"所属群组错误、";
                       }
                       String gateway_id=rs.getCell(j++, i).getContents();
                       if("".equals(gateway_id)||!ValidateUtils.isInteger(gateway_id)){
                     	  re=1;
                     	  question=question+"所属网关错误、";
                       }
                       
                       map1.put("node_name", node_name);
                 	  map1.put("node_address", node_address);
                 	  map1.put("node_type", node_type);
                 	  map1.put("v_mix", v_mix);
                 	  map1.put("v_max", v_max);
                 	  map1.put("i_max", i_max);
                 	  map1.put("tp_max", tp_max);
                 	  
                       if(re==1){
                     	  map1.put("question", question);
                     	  list1.add(map1);
                       }else{
                    	   ibusNodeService.insertFromExcel(map1);
                     	  //每循环一次判断当前的meter_id 在不在list中         插入成功list.add(meter_id)
                       }
                       
                }
                   
               }
               
               if(list1.size()==0){
             	  Date d=new Date();
                   String date=DateUtils.getDate(d, "yyyy-MM-dd HH:mm:ss");
             	  String filename=date+"ems";
             	  String fileurl="D:/"+filename+".xls";
             	  //上传人编号ID
                   Map m=new HashMap();
                   m.put("fileurl", fileurl);
                   m.put("date", date);
                   m.put("filename", filename);
                  // ibusNodeService.insertFromUrl(m);
                   System.out.println("====list1.size()==");
                   Map result = new HashMap();
                   result.put("flag", 0);
                   result.put("fileName", fileName);
                   result.put("disName", disName);
                   
                   
                  // return  "{‘文件["+fileName+"]插入成功,点此链接:"+disName+"查看’}";
             	  return result;
               }else{
              //新建excel表，OutPut文件，插入数据库
             	    String contentPath  = request.getRealPath("/");
             	    InputStream instream = new FileInputStream(contentPath+"/target/test2003.xls"); //定义EXCEL模板的路径
             	    //OutputStream outstream=new FileOutputStream("F/report1.xls"); //定义根据模板生成的目标文件的路径
             		Workbook wb = Workbook.getWorkbook(instream); //在相应路径中生成一个EXCEL文件
             		 filename2=fileName+"错误列表";
             		WritableWorkbook wwb = Workbook.createWorkbook(new File("D:/"+filename2),wb); //创建一个可读写的目标EXCEL文件
             		 disName2="D:/"+filename2;
             		//WritableWorkbook wwb = Workbook.createWorkbook(outstream, wb);
             		WritableSheet ws = wwb.getSheet(0);   //创建EXCEL页“Sheet1”
             		
             		/*Label label1 = new Label(0,0, "编号（meter_id）");
             		Label label2 = new Label(1,0, "所属集中器（concentrator_id）");
             		Label label3 = new Label(2,0, "所属群组（groupid）");
             		Label label4 = new Label(3,0, "安装位置（position）");
             		Label label5 = new Label(4,0, "表类型（meter_type）");
             		Label label6 = new Label(5,0, "安装时间（date）");
             		Label label7 = new Label(6,0, "通信速率（comm_rate）");
             		Label label8 = new Label(7,0, "端口号（comm_port）");
             		Label label9 = new Label(8,0, "出错内容（question）");
             		Label label10 = new Label(0,1, "0000201600550");
             		Label label11 = new Label(1,1, "1");
             		Label label12 = new Label(2,1, "1");
             		Label label13 = new Label(3,1, "南京市大江集团A座");
             		Label label14 = new Label(4,1, "1");
             		Label label15 = new Label(5,1, "2016年9月18日16:34:16");
             		Label label16 = new Label(6,1, "2400");
             		Label label17 = new Label(7,1, "31");
             		Label label18 = new Label(8,1, " ");
             		Label label19 = new Label(0,2, "电表编号唯一不可重复");
             		Label label20 = new Label(1,2, "填写所属集中器编号或名称");
             		Label label21 = new Label(2,2, "填写所属群组编号或名称");
             		Label label22 = new Label(3,2, "50字以内的电表详细地址");
             		Label label23 = new Label(4,2, "单相表为1，三相表为2");
             		Label label24 = new Label(5,2, "搜狗/百度/QQ输入法敲击键盘S+J键可快速打出时间");
             		Label label25 = new Label(6,2, "波特率");
             		Label label26 = new Label(7,2, "电表端口号默认为31");
             		Label label27 = new Label(8,2, "此处标记上一次导入出错的原因");
             		ws.addCell(label1);
             		ws.addCell(label2);
             		ws.addCell(label3);
             		ws.addCell(label4);
             		ws.addCell(label5);
             		ws.addCell(label6);
             		ws.addCell(label7);
             		ws.addCell(label8);
             		ws.addCell(label9);
             		ws.addCell(label10);
             		ws.addCell(label11);
             		ws.addCell(label12);
             		ws.addCell(label13);
             		ws.addCell(label14);
             		ws.addCell(label15);
             		ws.addCell(label16);
             		ws.addCell(label17);
             		ws.addCell(label18);
             		ws.addCell(label19);
             		ws.addCell(label20);
             		ws.addCell(label21);
             		ws.addCell(label22);
             		ws.addCell(label23);
             		ws.addCell(label24);
             		ws.addCell(label25);
             		ws.addCell(label26);
             		ws.addCell(label27);*/
             		  for (int i = 0; i < list1.size(); i++) {  //在"Sheet1"的2行2列处写入“This is a sample”
             			  Map map2 =(Map) list1.get(i);
             			  String node_name = (String) map2.get("node_name");
                           String node_address=(String) map2.get("node_address");
                           String node_type=(String) map2.get("node_type");
                           String v_mix=(String) map2.get("v_mix");
                           String v_max=(String) map2.get("v_max");
                           String i_max=(String) map2.get("i_max");
                           String tp_max=(String) map2.get("tp_max");
                           String question=(String) map2.get("question");
                           Label l1 = new Label(0,i+3, node_name);
                           Label l2 = new Label(1,i+3, node_address);
                           Label l3 = new Label(2,i+3, node_type);
                           Label l5 = new Label(4,i+3, v_mix);
                           Label l6 = new Label(5,i+3, v_max);
                           Label l7 = new Label(6,i+3, i_max);
                           Label l9 = new Label(8,i+3, tp_max);
                           Label l4 = new Label(3,i+3, question);
                           ws.addCell(l1);
                           ws.addCell(l2);
                           ws.addCell(l3);
                           ws.addCell(l5);
                           ws.addCell(l6);
                           ws.addCell(l7);
                           ws.addCell(l9);
                           ws.addCell(l4);
                          
                       }   
             		wwb.write(); //执行写入
             		wwb.close(); //关闭目标EXCEL
             		wb.close(); //关闭文件流
               }  
               //返回插入结果 

               //return  "文件["+fileName+"]插入失败,错误列表已生成"+filename2+"点此链接:"+disName2+"查看";

               //return  "{‘文件["+fileName+"]插入失败,错误列表已生成"+filename2+"点此链接:"+disName2+"查看’}";
               Map result = new HashMap();
               result.put("flag", 1);
               result.put("fileName", fileName);
               result.put("filename2", filename2);
               result.put("disName2", disName2);

               return  result;
              
		  }catch(Exception  e){
			  e.printStackTrace();

		  }
		    return null;
     	}
}

