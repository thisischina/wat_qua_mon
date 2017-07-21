package com.hd.ibus.controller;

import com.hd.ibus.pojo.Record;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.RecordService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.DateUtils;
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
import java.text.ParseException;

/**
 * Created by GitHub:thisischina .
 * Controller
 * 维修记录
 */

@Controller
@RequestMapping("record")
public class RecordController {
	@Resource
	private RecordService recordService;

	private PageHelp pageHelp=PageHelp.getInstance();

	@RequestMapping("tolist")
	public String toRecordList(Model model,Integer pageNow){
		System.out.println("№tolist");

		pageHelp.getInit(model,pageNow);

		return "record/record_list";
	}

	@RequestMapping("toadd")
	public String toAddRecord(){
		System.out.println("№toadd");
		return "record/record_add";
	}

	@RequestMapping("toupdate")
	public String toUpdate(HttpServletRequest request,Model model,Integer id){
		System.out.println("№toupdate");
		Record u=new Record();
		u.setId(id);
		pageHelp.setObject(u);
		//存储更新记录所在页数

		Record record=recordService.selectByKey(pageHelp);
		pageHelp.setObject(record);

		model.addAttribute(record);
		model.addAttribute(pageHelp);

		return "record/record_update";
	}

	@RequestMapping("update")
	public @ResponseBody int update(HttpServletRequest request,HttpServletResponse response,Model model)
			throws IOException ,ParseException {
		System.out.println("№:update");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String id= PageStr.getParameterStr("id",request);
		String content= PageStr.getParameterStr("content",request);
		String userId= PageStr.getParameterStr("userId",request);
		String recordTime= PageStr.getParameterStr("recordTime",request);
		String stationId= PageStr.getParameterStr("stationId",request);
		String equipmentId= PageStr.getParameterStr("equipmentId",request);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		Record record=new Record();
		record.setId(Integer.parseInt(id));
		if(!content.equals("")){
			record.setContent(content);
		}if(!userId.equals("")){
			record.setUserId(Integer.parseInt(userId));
		}if(!recordTime.equals("")){
			record.setRecordTime(DateUtils.getDate(recordTime,"yyyy-MM-dd HH:mm:ss"));
		}if(!stationId.equals("")){
			record.setStationId(Integer.parseInt(stationId));
		}if(!equipmentId.equals("")){
			record.setEquipmentId(Integer.parseInt(equipmentId));
		}
		recordService.updateRecord(record);

		pageHelp.setObject(record);
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

		String selectStr= PageStr.getParameterStr("content",request);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		Record record=new Record();
		if(!selectStr.equals("")){
			record.setContent(selectStr);

			pageHelp.setObject(record);
			pageHelp.setSelectStr(selectStr);
			model.addAttribute(pageHelp);
		}else {
			pageHelp.setObject(null);
		}
		return recordService.findList(pageHelp,pageNow);
	}


	@ResponseBody
	@RequestMapping("addrecord")
	public int addRecord(HttpServletRequest request,HttpServletResponse response,Model model)
			throws IOException ,ParseException{

		String content= PageStr.getParameterStr("content",request);
		String userId= PageStr.getParameterStr("userId",request);
		String recordTime= PageStr.getParameterStr("recordTime",request);
		String stationId= PageStr.getParameterStr("stationId",request);
		String equipmentId= PageStr.getParameterStr("equipmentId",request);

		Record record=new Record();
		if(!content.equals("")){
			record.setContent(content);
		}if(!userId.equals("")){
			record.setUserId(Integer.parseInt(userId));
		}if(!recordTime.equals("")){
			record.setRecordTime(DateUtils.getDate(recordTime,"yyyy-MM-dd HH:mm:ss"));
		}if(!stationId.equals("")){
			record.setStationId(Integer.parseInt(stationId));
		}if(!equipmentId.equals("")){
			record.setEquipmentId(Integer.parseInt(equipmentId));
		}

		recordService.insertRecord(record);

		return Value.IntNumOne;
	}


	@ResponseBody
	@RequestMapping("delete")
	public int deleteRecord(HttpServletRequest request,Model model,Integer id){
		recordService.deleteRecord(id);

		return Value.IntNumOne;
	}
}
