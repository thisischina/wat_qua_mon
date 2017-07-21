package com.hd.ibus.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hd.ibus.pojo.User;
import com.hd.ibus.service.UserService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;
import com.hd.ibus.util.shenw.PageHelp;
import com.hd.ibus.util.shenw.PageStr;
import com.hd.ibus.util.shenw.Value;
import org.springframework.http.HttpRequest;
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
		System.out.println("№user/user_list");

		pageHelp.getInit(model,pageNow);

		return "user/user_list";
	}

	@RequestMapping("toadd")
	public String toAddUser(HttpServletRequest request,Model model){
		System.out.println("№toadd");
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

		String id= PageStr.getParameterStr("id",request);
		String name= PageStr.getParameterStr("name",request);
		String tel= PageStr.getParameterStr("tel",request);
		String email= PageStr.getParameterStr("email",request);
		String unitId= PageStr.getParameterStr("unitId",request);
		String roleId= PageStr.getParameterStr("roleId",request);
		String power= PageStr.getParameterStr("power",request);

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

		pageHelp.setObject(user);
		model.addAttribute(pageHelp);

		return  Value.IntNumOne;
	}

	/**
	 * 更新密码
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("updatepw")
	public @ResponseBody int updatePassword(HttpServletRequest request,HttpServletResponse response,Model model)
			throws IOException {
		System.out.println("№:updatepw");

		String id= PageStr.getParameterStr("id",request);
		String account= PageStr.getParameterStr("account",request);
		String password= PageStr.getParameterStr("password",request);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		User user=new User();
		user.setId(Integer.parseInt(id));

		if(!account.equals("")){
			user.setAccount(account);
		}
		if(!password.equals("")){
			user.setPassword(password);
		}
		userService.updateUserPassword(user);

		model.addAttribute(pageHelp);

		return  Value.IntNumOne;
	}
	/**
	 * 带可查询的分页列表
	 * @param request
	 * @param pageNow
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getlist")
	public @ResponseBody DataGridResultInfo getSelectListPage(HttpServletRequest request,HttpServletResponse response,Integer pageNow,Model model)
			throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String selectStr= PageStr.getParameterStr("account",request);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		User user=new User();
		if(!selectStr.equals("")){
			user.setAccount(selectStr);

			pageHelp.setObject(user);
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
		String account= PageStr.getParameterStr("account",request);

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

	@RequestMapping("addobject")
	public @ResponseBody int addUser(HttpServletRequest request){
		String account=PageStr.getParameterStr("account",request);
		String password=PageStr.getParameterStr("password",request);
		String name=PageStr.getParameterStr("name",request);
		String tel=PageStr.getParameterStr("tel",request);
		String email=PageStr.getParameterStr("email",request);
		String unitId=PageStr.getParameterStr("unitId",request);
		String roleId=PageStr.getParameterStr("roleId",request);
		String power=PageStr.getParameterStr("power",request);

		User user=new User();
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

		//默认用户启用状态
		user.setState(Value.USER_STATE_OPEN);
		userService.insertUser(user);

		return Value.IntNumOne;
	}


	@ResponseBody
	@RequestMapping("delete")
	public int deleteUser(Integer id){
		userService.deleteUser(id);

		return Value.IntNumOne;
	}

	/**
	 * 登陆验证
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("login")
	public String userLogin(HttpServletRequest request){
		String account= PageStr.getParameterStr("account",request);
		String password= PageStr.getParameterStr("password",request);
		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		User user;
		if(!account.equals("")&&!password.equals("")){
			user=new User();
			user.setAccount(account);
			user.setPassword(password);
			pageHelp.setObject(user);
		}else {
			pageHelp.setObject(null);
		}
		user=userService.login(pageHelp);

		if(user!=null){

			if(user.getState()==null){
//				未设置状态默认停用
				user.setState(Value.USER_STATE_CLOSE);
			}

//			判断账号停启用状态
			try {
				if(user.getState()==Value.USER_STATE_CLOSE){
					return "redirect:/index.jsp?flag=-2";
				}
			}catch (Exception e){
				e.printStackTrace();
				return "redirect:/index.jsp?flag=-2";
			}

			HttpSession session=request.getSession();
			session.setAttribute("user",user);
			return "redirect:/index/index_five";
		}
		else{
			return "redirect:/index.jsp?flag=-1";
		}
	}

	/**
	 * 注销
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		return "redirect:/index.jsp";
	}

	/**
	 * 用户分配站点页面
	 * @return
	 */
	@RequestMapping("tosetstation")
	public String toSetStation(HttpServletRequest request,Model model,Integer pageNow,Integer id){
		pageHelp.getInit(model,pageNow);

		System.out.println("№tosetstation");
//		存储分配的对象
		User u=new User();
		u.setId(id);
		pageHelp.setObject(u);

		return "user/setstation";
	}

	/**
	 * 用户分配站点
	 * @return
	 */
	@ResponseBody
	@RequestMapping("setstation")
	public String setStation(HttpServletRequest request){
		User user=(User)pageHelp.getObject();

		String string[]=request.getParameterValues("stationId");
		if(string==null){
			return "";
		}
		StringBuffer s=new StringBuffer();

		for (String str:string) {
			if(string.length>1){
				s.append(str+",");
			}else{
				s.append(str);
			}
		}

		user.setPower(s.toString());

		userService.updateUser(user);
		return "";
	}

}
