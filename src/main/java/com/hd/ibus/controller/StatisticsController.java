package com.hd.ibus.controller;

import com.hd.ibus.pojo.MonitorData;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.MonitorDataService;
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
 * 数据统计
 */

@Controller
@RequestMapping("statistics")
public class StatisticsController {
	@Resource
	private MonitorDataService monitorDataService;

	private PageHelp pageHelp=PageHelp.getInstance();

	@RequestMapping("monitordata")
	public String toList(HttpServletRequest request,Model model,Integer pageNow){
		System.out.println("№statistics/monitor_data");

		pageHelp.getInit(model,pageNow);

		return "statistics/monitor_data";
	}

	/**
	 * 带可查询的分页列表
	 * @param request
	 * @param pageNow
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getmonitordata")
	public @ResponseBody DataGridResultInfo getMonitorData(HttpServletRequest request,HttpServletResponse response,Integer pageNow,Model model)
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

}
