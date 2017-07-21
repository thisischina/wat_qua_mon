package com.hd.ibus.controller;

import com.hd.ibus.pojo.MonitorData;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.MonitorDataService;
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
 * 检测数据
 */

@Controller
@RequestMapping("monitordata")
public class MonitorDataController {
	@Resource
	private MonitorDataService monitorDataService;

	private PageHelp pageHelp=PageHelp.getInstance();

	@RequestMapping("tolist")
	public String toMonitorDataList(HttpServletRequest request,Model model,Integer pageNow){
		System.out.println("№monitor/monitordata_list");

		pageHelp.getInit(model,pageNow);

		return "monitordata/monitordata_list";
	}

	@RequestMapping("toadd")
	public String toAddMonitorData(HttpServletRequest request,Model model){
		System.out.println("№monitordata_list");
		return "monitordata/monitordata_add";
	}

	@RequestMapping("toupdate")
	public String toUpdate(HttpServletRequest request,Model model,Integer id){
		System.out.println("№toupdate");

		MonitorData s=new MonitorData();
		s.setId(id);//存储更新记录所在页数
		pageHelp.setObject(s);

		MonitorData monitordata=monitorDataService.selectByKey(pageHelp);
		pageHelp.setObject(monitordata);

		model.addAttribute(monitordata);
		model.addAttribute(pageHelp);

		return "monitordata/monitordata_update";
	}

	@RequestMapping("update")
	public @ResponseBody int update(HttpServletRequest request,HttpServletResponse response,Model model)
			throws IOException {
		System.out.println("№:update");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String id= PageStr.getParameterStr("id",request);
		String number= PageStr.getParameterStr("number",request);
		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		MonitorData monitordata=new MonitorData();
		monitordata.setId(Integer.parseInt(id));
		if(!number.equals("")){
			monitordata.setNumber(number);
		}
		monitorDataService.updateMonitorData(monitordata);

		pageHelp.setObject(monitordata);
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

		String selectStr= PageStr.getParameterStr("number",request);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		MonitorData monitordata=new MonitorData();
		if(!selectStr.equals("")){
			monitordata.setNumber(selectStr);

			pageHelp.setObject(monitordata);
			pageHelp.setSelectStr(selectStr);
			model.addAttribute(pageHelp);
		}else {
			pageHelp.setObject(null);
	}
		return monitorDataService.findList(pageHelp,pageNow);
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
		String name= PageStr.getParameterStr("number",request);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		MonitorData monitordata;
		if(!name.equals("")){
			monitordata=new MonitorData();
			monitordata.setNumber(name);
			pageHelp.setObject(monitordata);
		}else {
			pageHelp.setObject(null);
		}

		return monitorDataService.getAccountCount(pageHelp);
	}

	@ResponseBody
	@RequestMapping("addmonitordata")
	public int addMonitorData(HttpServletRequest request,Model model){
		String name= PageStr.getParameterStr("number",request);
		String power= PageStr.getParameterStr("power",request);

		MonitorData monitorData=new MonitorData();
		if(!name.equals("")){
			monitorData.setNumber(name);
		}
		monitorDataService.insertMonitorData(monitorData);

		return Value.IntNumOne;
	}


	@ResponseBody
	@RequestMapping("delete")
	public int deleteMonitorData(HttpServletRequest request,Integer id){
		monitorDataService.deleteMonitorData(id);

		return Value.IntNumOne;
	}
}
