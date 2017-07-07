package com.hd.ibus.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hd.ibus.pojo.User;
import com.hd.ibus.service.UserService;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.shenw.PageHelp;
import com.hd.ibus.util.shenw.PageStr;
import com.hd.ibus.util.shenw.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.ibus.result.DataGridResultInfo;

@Controller
@RequestMapping("user")
public class userController {
	@Resource
	private UserService userService;

	@RequestMapping("tolist")
	public String toUserList(HttpServletRequest request,Model model,PageHelp pageHelp){
		System.out.println("№user_list");
		//
		if(pageHelp!=null&&pageHelp.getPageBean()!=null){
			model.addAttribute(pageHelp);
		}else{
			pageHelp=PageHelp.getInstance();
			PageBean pageBean=new PageBean();
			pageBean.setPageNow(1);
			pageHelp.setPageBean(pageBean);
			model.addAttribute(pageHelp);
		}

		return "user/user_list";
	}

	@RequestMapping("toadd")
	public String toAddUser(HttpServletRequest request,Model model){
		System.out.println("№user_list");
		return "user/addUser";
	}

	@RequestMapping("toupdate")
	public String toUpdate(HttpServletRequest request,Model model,Integer id,Integer pageNow){
		System.out.println("№toupdate");
		PageHelp pageHelp=PageHelp.getInstance();

		User u=new User();
		u.setId(id);
		pageHelp.setObject(u);

		//存储更新记录所在页数
		pageHelp.getPageBean().setPageNow(pageNow);

		User user=userService.selectByKey(pageHelp);

		model.addAttribute(user);
		model.addAttribute(pageHelp);

		return "user/updateuser";
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
	@RequestMapping("getlist")
	public @ResponseBody DataGridResultInfo getSelectListPage(HttpServletRequest request,HttpServletResponse response,Integer pageNow,Integer pageSize,PageHelp pageHelp,Model model)
			throws IOException {
		System.out.println("№:getlist"+pageNow);

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

	/**
	 * 确认是否存在同一账号
	 * @param request
	 * @param pageHelp
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("confirmexist")
	public @ResponseBody DataGridResultInfo confirmExist(HttpServletRequest request,PageHelp pageHelp,Model model)
			throws IOException {
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

		return userService.getAccountCount(pageHelp);
	}

	@ResponseBody
	@RequestMapping("adduser")
	public int addUser(User u,HttpServletRequest request,Model model){
		String account=PageStr.getParameterStr("account",request,model);
		String password=PageStr.getParameterStr("password",request,model);
		String name=PageStr.getParameterStr("name",request,model);
		String tel=PageStr.getParameterStr("tel",request,model);
		String email=PageStr.getParameterStr("email",request,model);

		u.setAccount(account);
		u.setPassword(password);
		u.setName(name);
		u.setTel(tel);
		u.setEmail(email);
		userService.insertUser(u);

		return Value.IntNumOne;
	}

}
