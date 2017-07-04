package com.hd.ibus.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hd.ibus.pojo.User;
import com.hd.ibus.service.UserService;
import com.hd.ibus.util.PageHelp;
import com.hd.ibus.util.PageStr;
import com.hd.ibus.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.pojo.vo.IbusUserVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusUserService;
import com.hd.ibus.util.MD5Util;

@Controller
@RequestMapping("user")
public class userController {
	@Resource
	private UserService userService;

	@RequestMapping("user_list")
	public String user(HttpServletRequest request,Model model){
		System.out.println("№user_list");
		return "user/user_list";
	}
	
	@RequestMapping("listall")
	public @ResponseBody DataGridResultInfo findList(HttpServletRequest request, Integer pageNow,Integer pageSize,PageHelp pageHelp)
			throws IOException {
		System.out.println("№listall");
		return userService.findList(pageHelp,pageNow,pageSize);
	}

	@RequestMapping("listbysomething")
	public @ResponseBody DataGridResultInfo getListBySomething(HttpServletRequest request, Integer pageNow,Integer pageSize,PageHelp pageHelp,Model model)
			throws IOException {
		System.out.println("№listbysome");

		String account= PageStr.getParameterStr("account",request,model);
		pageHelp = new PageHelp();
		User user;
		if(!account.equals("")){
			user=new User();
			user.setAccount(account);
			pageHelp.setObject(user);
		}

		return userService.findList(pageHelp,pageNow,pageSize);
	}

}
