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

	@RequestMapping("tolist")
	public String toUserList(HttpServletRequest request,Model model){
		System.out.println("№user_list");
		return "user/user_list";
	}

	@RequestMapping("toadd")
	public String toAddUser(HttpServletRequest request,Model model){
		System.out.println("№user_list");
		return "user/addUser";
	}

	/**
	 * 带可查询的分页列表
	 * @param request
	 * @param pageNow
	 * @param pageSize
	 * @param pageHelp
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("selectListPage")
	public @ResponseBody DataGridResultInfo getSelectListPage(HttpServletRequest request,HttpServletResponse response,Integer pageNow,Integer pageSize,PageHelp pageHelp,Model model)
			throws IOException {
		System.out.println("№:selectListPage");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String account= PageStr.getParameterStr("account",request,model);
		pageHelp =PageHelp.getInstance();

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		User user;
		if(!account.equals("")){
			user=new User();

			user.setAccount(account);
			pageHelp.setObject(user);
		}else {
			pageHelp.setObject(null);
		}

		return userService.findList(pageHelp,pageNow,pageSize);
	}

	@RequestMapping("add")
	public int addUser(HttpServletRequest request,Model model){
		String account=PageStr.getParameterStr("account",request,model);
		String password=PageStr.getParameterStr("password",request,model);

		User u=new User();
		u.setAccount(account);
		u.setPassword(password);
		userService.insert(u);
		model.addAttribute("state",1);
		return 1;
	}

}
