package com.hd.ibus.controller;

import com.hd.ibus.pojo.Unit;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.UnitService;
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
 * 单位
 */

@Controller
@RequestMapping("unit")
public class UnitController {
	@Resource
	private UnitService unitService;

	private PageHelp pageHelp=PageHelp.getInstance();

	@RequestMapping("tolist")
	public String toUnitList(Model model,Integer pageNow){
		System.out.println("№tolist");

		pageHelp.getInit(model,pageNow);

		return "unit/unit_list";
	}

	@RequestMapping("toadd")
	public String toAddUnit(){
		System.out.println("№toadd");
		return "unit/unit_add";
	}

	@RequestMapping("toupdate")
	public String toUpdate(Model model,Integer id){
		System.out.println("№toupdate");

		Unit s=new Unit();
		s.setUnitId(id);//存储更新记录所在页数
		pageHelp.setObject(s);

		Unit unit=unitService.selectByKey(pageHelp);
		pageHelp.setObject(unit);

		model.addAttribute(unit);
		model.addAttribute(pageHelp);

		return "unit/unit_update";
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

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		Unit unit=new Unit();
		unit.setUnitId(Integer.parseInt(id));
		if(!name.equals("")){
			unit.setName(name);
		}
		unitService.updateUnit(unit);

		pageHelp.setObject(unit);
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
		Unit unit=new Unit();
		if(!selectStr.equals("")){
			unit.setName(selectStr);

			pageHelp.setObject(unit);
			pageHelp.setSelectStr(selectStr);
			model.addAttribute(pageHelp);
		}else {
			pageHelp.setObject(null);
	}
		return unitService.findList(pageHelp,pageNow);
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
		Unit unit;
		if(!name.equals("")){
			unit=new Unit();
			unit.setName(name);
			pageHelp.setObject(unit);
		}else {
			pageHelp.setObject(null);
		}

		return unitService.getNameCount(pageHelp);
	}

	@ResponseBody
	@RequestMapping("addunit")
	public int addUnit(HttpServletRequest request,Model model){
		String name= PageStr.getParameterStr("name",request);

		Unit unit=new Unit();
		if(!name.equals("")){
			unit.setName(name);
		}

		unitService.insertUnit(unit);

		return Value.IntNumOne;
	}


	@ResponseBody
	@RequestMapping("delete")
	public int deleteUnit(HttpServletRequest request,Integer id){
		unitService.deleteUnit(id);

		return Value.IntNumOne;
	}
}
