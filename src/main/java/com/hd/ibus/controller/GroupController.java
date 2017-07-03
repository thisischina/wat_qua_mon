package com.hd.ibus.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hd.ibus.pojo.IbusGroup;
import com.hd.ibus.pojo.vo.IbusGroupVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusGroupService;
import com.hd.ibus.service.IbusOperationService;

@Controller
@RequestMapping("group")
public class GroupController {
	@Resource
	private IbusGroupService ibusGroupService;
	
	@Resource
	private IbusOperationService ibusOperationService;

	@RequestMapping("/directAddGroup")
	public String index2(HttpServletRequest request,Model model){
		
		return "system/group/addGroup";
	}
	
	@RequestMapping("/directUpdateGroup")
	public String directUpdateGroup(HttpServletRequest request,Model model,Integer id){
		IbusGroup ibusGroup = this.ibusGroupService.findById(id);
		model.addAttribute(ibusGroup);
		return "system/group/updateGroup";
	}
	
	@RequestMapping("/findList")
	public @ResponseBody DataGridResultInfo findList(HttpServletRequest request,HttpServletResponse response, Integer pageNow,Integer pageSize,IbusGroupVo ibusGroupVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		
		return this.ibusGroupService.findList(ibusGroupVo, pageNow, pageSize);
	}
	
	@RequestMapping("/saveIbusGroup")
	public @ResponseBody String saveIbusGroup(HttpServletRequest request,HttpServletResponse response,IbusGroupVo ibusGroupVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		return this.ibusGroupService.saveIbusGroup(ibusGroupVo);
	}
	
//	@RequestMapping("/main_pic")
//	public ResponseEntity<byte[]> mainPic(String pic_name) throws Exception {
//		HttpHeaders hh = new HttpHeaders();
//		hh.setContentType(MediaType.IMAGE_JPEG);
//		
//		return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(  
//				new FileInputStream(pic_name)), HttpStatus.OK);
//	}
	
	
//	@RequestMapping("/main_pic")
//	public ResponseEntity<byte[]> mainPic(Integer groupId) throws Exception {
//		HttpHeaders hh = new HttpHeaders();
//		hh.setContentType(MediaType.IMAGE_JPEG);
//		IbusGroup ibusGroup = this.ibusGroupService.findById(groupId);
//		String pic_name="";
//		if(ibusGroup!=null&&ibusGroup.getFileUrl()!=null){
//			System.out.println("进来了");
//			System.out.println("name"+ibusGroup.getFileName());
//			pic_name = "c:\\"+ibusGroup.getFileName();
//			byte[] b = Base64.decodeBase64(ibusGroup.getFileUrl());
//			System.out.println("1");
//		    for(int i=0;i<b.length;++i)
//		    {
//		        if(b[i]<0)
//		        {//调整异常数据
//		            b[i]+=256;
//		        }
//		        System.out.println("2");
//		    }
//		    //生成jpeg图片
//		    System.out.println("3");
//		    OutputStream out = new FileOutputStream("c:\\"+ibusGroup.getFileName());    
//		    out.write(b);
//		    out.flush();
//		    System.out.println("4");
//		    out.close();
//		}
//		return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(  
//				new FileInputStream(pic_name)), HttpStatus.OK);
//	}
	
	
	

//	@RequestMapping("/saveIbusGroup2")
//	public String saveIbusGroupTest(HttpServletRequest request,Model model,HttpServletResponse response,MultipartFile fileMainPic)
//			throws IOException {
//			byte[] data = fileMainPic.getBytes();
//			
//			System.out.println("---------------------:"+new String(Base64.encodeBase64(data))); 
//		    //return new String(Base64.encodeBase64(data));
//			return "";
//	}
	
	@RequestMapping("/saveIbusGroup2")
	public String saveIbusGroup2(HttpServletRequest request,Model model,HttpServletResponse response,MultipartFile fileMainPic) 
			throws IOException {
		//如果存在文件上传 执行操作
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		String fileName=null;
		//String compress_name = null;
		String picture = null;
		if(fileMainPic.getSize() != 0) {
			fileName = fileMainPic.getOriginalFilename();
			//compress_name = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
			//File f = new File(PropertiesUtils.getValue(Config.CONFIG, 666 + "") + compress_name);
			//FileCopyUtils.copy(fileMainPic.getBytes(), f);
			//System.out.println("file-------------------------fileName:"+fileName+",fileMainPic:"+fileMainPic+",compress_name:"+compress_name+",f:"+f);
			byte[] data = fileMainPic.getBytes();
			picture = new String(Base64.encodeBase64(data));
		}
		String groupName = request.getParameter("groupName");
		String gatewayId = request.getParameter("gatewayId");
		String groupName2 = request.getParameter("groupName2");
		
		System.out.println("groupName:"+groupName+",gatewayId:"+gatewayId+",groupName2:"+groupName2);
		IbusGroupVo ibusGroupVo = new IbusGroupVo();
		ibusGroupVo.setIbusGroup(new IbusGroup());
		
		System.out.println("ibusGroupVo:"+ibusGroupVo+",ibusGroupVo--group:"+ibusGroupVo.getIbusGroup());
		ibusGroupVo.getIbusGroup().setGroupName(groupName);
		ibusGroupVo.getIbusGroup().setGatewayId(Integer.parseInt(gatewayId));
		ibusGroupVo.getIbusGroup().setGroupName2(groupName2);
		ibusGroupVo.getIbusGroup().setGroupState(1);
		ibusGroupVo.getIbusGroup().setFileName(fileName);
		ibusGroupVo.getIbusGroup().setFileUrl(picture);
		this.ibusGroupService.saveIbusGroup(ibusGroupVo);
		
		
		model.addAttribute("flag", "group");
		
		return "system/group/group";
	}
	
	@RequestMapping("/updateIbusGroup2")
	public String updateIbusGroup2(HttpServletRequest request,Model model,HttpServletResponse response,MultipartFile fileMainPic)
			throws IOException {
		//如果存在文件上传 执行操作
				String fileName=null;
				//String compress_name = null;
				String picture = null;
				if(fileMainPic != null) {
					fileName = fileMainPic.getOriginalFilename();
					//compress_name = UUID.randomUUID().toString() + ".jpg";
					//File f = new File(PropertiesUtils.getValue(Config.CONFIG, 666 + "") + compress_name);
					//FileCopyUtils.copy(fileMainPic.getBytes(), f);
					//System.out.println("file-------------------------fileName:"+fileName+",fileMainPic:"+fileMainPic+",compress_name:"+compress_name+",f:"+f);
					byte[] data = fileMainPic.getBytes();
					picture = new String(Base64.encodeBase64(data));
				}
				request.setCharacterEncoding("utf-8"); 
				response.setContentType("text/html;charset=UTF-8"); 
				response.setCharacterEncoding("utf-8");
				String groupName = request.getParameter("groupName");
				String gatewayId = request.getParameter("gatewayId");
				String groupName2 = request.getParameter("groupName2");
				String id = request.getParameter("id");
				System.out.println("groupName:"+groupName+",gatewayId:"+gatewayId+",groupName2:"+groupName2);
				IbusGroupVo ibusGroupVo = new IbusGroupVo();
				ibusGroupVo.setIbusGroup(new IbusGroup());
				//System.out.println("wwwwwwwwwwwwww:"+PropertiesUtils.getValue(Config.CONFIG, 666 + "") + compress_name);
				//System.out.println("ibusGroupVo:"+ibusGroupVo+",ibusGroupVo--group:"+ibusGroupVo.getIbusGroup());
				ibusGroupVo.getIbusGroup().setId(Integer.parseInt(id));
				ibusGroupVo.getIbusGroup().setGroupName(groupName);
				ibusGroupVo.getIbusGroup().setGatewayId(Integer.parseInt(gatewayId));
				ibusGroupVo.getIbusGroup().setGroupName2(groupName2);
				ibusGroupVo.getIbusGroup().setGroupState(1);
				ibusGroupVo.getIbusGroup().setFileName(fileName);
				ibusGroupVo.getIbusGroup().setFileUrl(picture);
				this.ibusGroupService.updateIbusGroup(ibusGroupVo);
				
				
				
				model.addAttribute("flag", "group");
				
				return "system/group/group";
	}
	
	
	@RequestMapping("/updateIbusGroup")
	public @ResponseBody String updateIbusGroup(HttpServletRequest request,HttpServletResponse response,IbusGroupVo ibusGroupVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		
		return this.ibusGroupService.updateIbusGroup(ibusGroupVo);
	}
	
	@RequestMapping("/deleteIbusGroup")
	public @ResponseBody String deleteIbusGroup(HttpServletRequest request,HttpServletResponse response,Integer id)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		return this.ibusGroupService.deleteIbusGroup(id);
	}
	
	@RequestMapping("/findAll")
	public @ResponseBody List<IbusGroup> findAll(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		Integer gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
		
		
		
		return this.ibusGroupService.findListByGatewayId(gatewayId);
	}
	
	
	@RequestMapping("/findById")
	public @ResponseBody IbusGroup findById(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		String groupId = request.getParameter("groupId");
		if(groupId==null||groupId.equals("")){
			return null;
		}
		return this.ibusGroupService.findById(Integer.parseInt(groupId));
	}
	
}
