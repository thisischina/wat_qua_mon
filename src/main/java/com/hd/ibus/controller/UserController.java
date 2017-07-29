package com.hd.ibus.controller;

import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hd.ibus.pojo.Role;
import com.hd.ibus.pojo.Unit;
import com.hd.ibus.pojo.User;
import com.hd.ibus.service.RoleService;
import com.hd.ibus.service.UnitService;
import com.hd.ibus.service.UserService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;
import com.hd.ibus.util.shenw.PageHelp;
import com.hd.ibus.util.shenw.PageStr;
import com.hd.ibus.util.shenw.Value;
import org.apache.commons.collections.list.TreeList;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hd.ibus.result.DataGridResultInfo;
import sun.reflect.generics.tree.Tree;

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
	@Resource
	private RoleService roleService;
	@Resource
	private UnitService unitService;

	private PageHelp pageHelp=PageHelp.getInstance();

	@RequestMapping("tolist")
	public String toUserList(HttpServletRequest request,Model model,Integer pageNow){
		System.out.println("№user/user_list");

		pageHelp.getInit(model,pageNow);

		return "user/user_list";
	}

	@RequestMapping("toadd")
	public String toAddUser(Model model){
		System.out.println("№toadd");
		setOtherData(model);
		return "user/user_add";
	}

	@RequestMapping("toupdate")
	public String toUpdate(HttpServletRequest request,Model model,Integer id){
		System.out.println("№toupdate");
		setOtherData(model);

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
		String state= PageStr.getParameterStr("state",request);

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
		}if(!state.equals("")){
			user.setState(Integer.parseInt(state));
		}if(Integer.parseInt(roleId)==Value.RoleAdmin){
			user.setPower("admin");
		}else {
			user.setPower("0");
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
		}if(Integer.parseInt(roleId)==Value.RoleAdmin){
			user.setPower("admin");
		}else {
			user.setPower("0");
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

			Integer userRole=Value.RoleAdmin;
			session.setAttribute("userRole",userRole);

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
	public String toSetStation(Model model,String id){
		System.out.println("№tosetstation");
//		初始化
		pageHelp.getInit(model,0);

		model.addAttribute("userid",id);

		return "user/setstation";
	}

	/**
	 * 字段power数据处理
	 * @return str
	 */
	public String setPowerStr(String str){
		StringBuilder builder=new StringBuilder();

		String []strings=str.split(",");
		Integer []stringss=new Integer[strings.length];
		for (int i=0;i<strings.length;i++){
			stringss[i]=Integer.valueOf(strings[i]);
		}
		List<Integer> list = Arrays.asList(stringss);//数组转集合
		Set<Integer> set=new TreeSet<Integer>(list);
		builder.append(set.toString().replaceAll("\\[","(").replaceAll("\\]",")"));

		return builder.toString();
	}

	/**
	 * 加载角色、单位数据
	 * @param model
	 */
	public void setOtherData(Model model){
		List<Role> roleList=roleService.selectAll();
		List<Unit> unitList=unitService.selectAll();

		model.addAttribute("roleList",roleList);
		model.addAttribute("unitList",unitList);
	}
}
