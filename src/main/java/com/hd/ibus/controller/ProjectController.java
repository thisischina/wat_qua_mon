package com.hd.ibus.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.ibus.pojo.IbusOperation;
import com.hd.ibus.pojo.IbusProject;
import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.pojo.vo.IbusProjectVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusOperationService;
import com.hd.ibus.service.IbusProjectService;

@Controller
@RequestMapping("project")
public class ProjectController {

	@Resource
	private IbusProjectService ibusProjectService;
	
	@Resource
	private IbusOperationService ibusOperationService;

	@RequestMapping("/directAddProject")
	public String index2(HttpServletRequest request,Model model){
		
		return "system/project/addProject";
	}
	
	@RequestMapping("/directUpdateProject")
	public String directUpdateProject(HttpServletRequest request,Model model,Integer id){
		IbusProject ibusProject = this.ibusProjectService.findById(id);
		model.addAttribute(ibusProject);
		return "system/project/updateProject";
	}
	
	@RequestMapping("/findList")
	public @ResponseBody DataGridResultInfo findList(HttpServletRequest request,HttpServletResponse response, Integer pageNow,Integer pageSize,IbusProjectVo ibusProjectVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		return this.ibusProjectService.findList(ibusProjectVo, pageNow, pageSize);
	}
	
	@RequestMapping("/saveIbusProject")
	public @ResponseBody String saveIbusProject(HttpServletRequest request,HttpServletResponse response,IbusProjectVo ibusProjectVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		
		return this.ibusProjectService.saveIbusProject(ibusProjectVo);
	}
	
	@RequestMapping("/updateIbusProject")
	public @ResponseBody String updateIbusProject(HttpServletRequest request,HttpServletResponse response,IbusProjectVo ibusProjectVo)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
	
		
		return this.ibusProjectService.updateIbusProject(ibusProjectVo);
	}
	
	@RequestMapping("/deleteIbusProject")
	public @ResponseBody String deleteIbusProject(HttpServletRequest request,HttpServletResponse response,Integer id)
			throws IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		
		
		return this.ibusProjectService.deleteIbusProject(id);
	}
	
	
	@RequestMapping("/project")
	public  String project(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		
		
		return"system/project";
	}
}
