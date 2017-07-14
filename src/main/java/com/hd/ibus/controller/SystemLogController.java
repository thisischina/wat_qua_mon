package com.hd.ibus.controller;

import com.hd.ibus.pojo.SystemLog;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.SystemLogService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;
import com.hd.ibus.util.shenw.PageHelp;
import com.hd.ibus.util.shenw.PageStr;
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
 * 系统日志
 */

@Controller
@RequestMapping("systemlog")
public class SystemLogController {
	@Resource
	private SystemLogService systemLogService;

	private PageHelp pageHelp=PageHelp.getInstance();

	@RequestMapping("tolist")
	public String toSystemLogList(HttpServletRequest request,Model model,Integer pageNow){
		System.out.println("№tolist");

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

		return "systemlog/systemlog_list";
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
	public @ResponseBody
	DataGridResultInfo getSelectListPage(HttpServletRequest request, HttpServletResponse response, Integer pageNow, Model model)
			throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String selectStr= PageStr.getParameterStr("content",request);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		SystemLog systemLog=new SystemLog();
		if(!selectStr.equals("")){
			systemLog.setContent(selectStr);

			pageHelp.setObject(systemLog);
			pageHelp.setSelectStr(selectStr);
			model.addAttribute(pageHelp);
		}else {
			pageHelp.setObject(null);
		}
		return systemLogService.findList(pageHelp,pageNow);
	}

}
