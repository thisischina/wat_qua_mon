package com.hd.ibus.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusOperationService;

@Controller
@RequestMapping("operation")
public class OperationController {
	
	@Resource
	private IbusOperationService ibusOperationService;

	@RequestMapping("/findAll")
	public @ResponseBody DataGridResultInfo findOneByTable(HttpServletRequest request,HttpServletResponse response,Integer pageNow){
		IbusUser ibusUser = (IbusUser)request.getSession().getAttribute("user");
		String name = request.getParameter("name");
		String typeName = request.getParameter("typeName");
		if(ibusUser!=null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", ibusUser.getId());
			map.put("name", name);
			map.put("typeName", typeName);
			if(pageNow==null){
				pageNow=1;
			}else{
				
			}
			if(ibusUser.getRole()==1){
				return this.ibusOperationService.findAll(map, pageNow); 
			}else if(ibusUser.getRole()==2){
				return this.ibusOperationService.findAllByUserId(map, pageNow);
			}
		}
		return null;
	}
}
