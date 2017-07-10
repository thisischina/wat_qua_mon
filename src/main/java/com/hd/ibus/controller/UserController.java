package com.hd.ibus.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hd.ibus.pojo.User;
import com.hd.ibus.service.UserService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;
import com.hd.ibus.util.shenw.PageHelp;
import com.hd.ibus.util.shenw.PageStr;
import com.hd.ibus.util.shenw.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hd.ibus.result.DataGridResultInfo;

/**
 * Created by GitHub:thisischina on 2017年7月10日10:25:23.
 * Controller
 * 用户
 */

@Controller
@RequestMapping("user")
public class UserController {
	@Resource
	private UserService userService;

	private PageHelp pageHelp=PageHelp.getInstance();

	@RequestMapping("tolist")
	public String toUserList(HttpServletRequest request,Model model,Integer pageNow){
		System.out.println("№user_list");

		if(pageNow!=null&&pageNow==0){
			//初始化
			pageNow = PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_NOW);
			Integer pageSize = PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_SIZE) ;

			PageBean pageBean=new PageBean();

			pageBean.setPageNow(pageNow);
			pageBean.setPageSize(pageSize);
			pageHelp.setPageBean(pageBean);

			//清除搜索条件
			pageHelp.setSelectStr(null);
			model.addAttribute(pageHelp);
		}else{
			model.addAttribute(pageHelp);
		}

		return "user/user_list";
	}

	@RequestMapping("toadd")
	public String toAddUser(HttpServletRequest request,Model model){
		System.out.println("№user_list");
		return "user/user_add";
	}

	@RequestMapping("toupdate")
	public String toUpdate(HttpServletRequest request,Model model,Integer id){
		System.out.println("№toupdate");
		User u=new User();
		u.setId(id);
		pageHelp.setObject(u);
		//存储更新记录所在页数

		User user=userService.selectByKey(pageHelp);
		pageHelp.setObject(user);

		model.addAttribute(user);
		model.addAttribute(pageHelp);

		return "user/user_update";
	}

	@RequestMapping("update")
	public @ResponseBody int update(HttpServletRequest request,HttpServletResponse response,Model model)
			throws IOException {
		System.out.println("№:update");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String id= PageStr.getParameterStr("id",request,model);
		String name= PageStr.getParameterStr("name",request,model);
		String tel= PageStr.getParameterStr("tel",request,model);
		String email= PageStr.getParameterStr("email",request,model);
		String unitId= PageStr.getParameterStr("unitId",request,model);
		String roleId= PageStr.getParameterStr("roleId",request,model);
		String power= PageStr.getParameterStr("power",request,model);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		User user=new User();
		user.setId(Integer.parseInt(id));
		if(!name.equals("")){
			user.setName(name);
		}if(!tel.equals("")){
			user.setTel(tel);
		}if(!email.equals("")){
			user.setEmail(email);
		}if(!unitId.equals("")){
			user.setUnitId(Integer.parseInt(unitId));
		}if(!roleId.equals("")){
			user.setRoleId(Integer.parseInt(roleId));
		}if(!power.equals("")){
			user.setPower(power);
		}
		userService.updateUser(user);

		model.addAttribute(pageHelp);

		return  Value.IntNumOne;
	}

	/**
	 * 带可查询的分页列表
	 * @param request
	 * @param pageNow
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getlist")
	public @ResponseBody DataGridResultInfo getSelectListPage(HttpServletRequest request,HttpServletResponse response,Integer pageNow,Integer pageSize,Model model)
			throws IOException {
		System.out.println("№:getlist"+pageNow);

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String selectStr= PageStr.getParameterStr("account",request,model);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		User station=new User();
		if(!selectStr.equals("")){
			station.setAccount(selectStr);

			pageHelp.setObject(station);
			pageHelp.setSelectStr(selectStr);
			model.addAttribute(pageHelp);
		}else {
			pageHelp.setObject(null);
		}
		return userService.findList(pageHelp,pageNow);
	}

	/**
	 * 确认是否存在同一账号
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("confirmexist")
	public @ResponseBody DataGridResultInfo confirmExist(HttpServletRequest request,Model model)
			throws IOException {
		String account= PageStr.getParameterStr("account",request,model);

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
	public int addUser(User user,HttpServletRequest request,Model model){
		String account=PageStr.getParameterStr("account",request,model);
		String password=PageStr.getParameterStr("password",request,model);
		String name=PageStr.getParameterStr("name",request,model);
		String tel=PageStr.getParameterStr("tel",request,model);
		String email=PageStr.getParameterStr("email",request,model);
		String unitId=PageStr.getParameterStr("unitId",request,model);
		String roleId=PageStr.getParameterStr("roleId",request,model);
		String power=PageStr.getParameterStr("power",request,model);

		if(!account.equals("")){
			user.setAccount(account);
		}if(!password.equals("")){
			user.setPassword(password);
		}if(!name.equals("")){
			user.setName(name);
		}if(!tel.equals("")){
			user.setTel(tel);
		}if(!email.equals("")){
			user.setEmail(email);
		}if(!unitId.equals("")){
			user.setUnitId(Integer.parseInt(unitId));
		}if(!roleId.equals("")){
			user.setRoleId(Integer.parseInt(roleId));
		}if(!power.equals("")){
			user.setPower(power);
		}

		userService.insertUser(user);

		return Value.IntNumOne;
	}


	@ResponseBody
	@RequestMapping("delete")
	public int deleteUser(HttpServletRequest request,Model model){
		String id=PageStr.getParameterStr("id",request,model);
		userService.deleteUser(Integer.parseInt(id));

		return Value.IntNumOne;
	}
}
