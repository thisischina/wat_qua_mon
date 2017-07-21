package com.hd.ibus.controller;

import com.hd.ibus.pojo.Role;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.RoleService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GitHub:thisischina .
 * Controller
 * 角色
 */

@Controller
@RequestMapping("role")
public class RoleController {
	@Resource
	private RoleService roleService;

	private PageHelp pageHelp=PageHelp.getInstance();

	@RequestMapping("tolist")
	public String toRoleList(Model model,Integer pageNow){
		System.out.println("№tolist");

		pageHelp.getInit(model,pageNow);

		return "role/role_list";
	}

	@RequestMapping("toadd")
	public String toAddRole(){
		System.out.println("№role_list");
		return "role/role_add";
	}

	@RequestMapping("toupdate")
	public String toUpdate(Model model,Integer id){
		System.out.println("№toupdate");

		Role s=new Role();
		s.setRoleId(id);//存储更新记录所在页数
		pageHelp.setObject(s);

		Role role=roleService.selectByKey(pageHelp);
		pageHelp.setObject(role);

		model.addAttribute(role);
		model.addAttribute(pageHelp);

		return "role/role_update";
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
		String power= PageStr.getParameterStr("power",request);
		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		Role role=new Role();
		role.setRoleId(Integer.parseInt(id));
		if(!name.equals("")){
			role.setName(name);
		}
		if(!power.equals("")){
			role.setPower(power);
		}
		roleService.updateRole(role);

		pageHelp.setObject(role);
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

		String selectStr= PageStr.getParameterStr("name",request);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		Role role=new Role();
		if(!selectStr.equals("")){
			role.setName(selectStr);

			pageHelp.setObject(role);
			pageHelp.setSelectStr(selectStr);
			model.addAttribute(pageHelp);
		}else {
			pageHelp.setObject(null);
	}
		return roleService.findList(pageHelp,pageNow);
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
		String name= PageStr.getParameterStr("name",request);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		Role role;
		if(!name.equals("")){
			role=new Role();
			role.setName(name);
			pageHelp.setObject(role);
		}else {
			pageHelp.setObject(null);
		}

		return roleService.getNameCount(pageHelp);
	}

	@ResponseBody
	@RequestMapping("addrole")
	public int addRole(HttpServletRequest request,Model model){
		String name= PageStr.getParameterStr("name",request);
		String power= PageStr.getParameterStr("power",request);

		Role role=new Role();
		if(!name.equals("")){
			role.setName(name);
		}if(!power.equals("")){
			role.setPower(power);
		}
		roleService.insertRole(role);

		return Value.IntNumOne;
	}


	@ResponseBody
	@RequestMapping("delete")
	public int deleteRole(HttpServletRequest request,Integer id){
		roleService.deleteRole(id);

		return Value.IntNumOne;
	}
}
