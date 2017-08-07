package com.hd.ibus.controller;

import com.hd.ibus.pojo.Equipment;
import com.hd.ibus.pojo.Station;
import com.hd.ibus.pojo.User;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.EquipmentService;
import com.hd.ibus.service.StationService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by GitHub:thisischina .
 * Controller
 * 设备
 */

@Controller
@RequestMapping("equipment")
public class EquipmentController {
	@Resource
	private EquipmentService equipmentService;
	@Resource
	private StationService stationService;

	private PageHelp pageHelp=PageHelp.getInstance();

	@RequestMapping("tolist")
	public String toEquipmentList(Model model,Integer pageNow){
		System.out.println("№tolist");

		pageHelp.getInit(model,pageNow);

		return "equipment/equipment_list";
	}

	@RequestMapping("toadd")
	public String toAddEquipment(HttpServletRequest request,Model model){
		System.out.println("№toadd");
		//		初始化
		pageHelp.getInit(model,0);
		//查询已分配的站点
		pageHelp.setUserPower(userPowerStr(request));
		List<Station> stationList=stationService.listByUserPower(pageHelp);

		model.addAttribute("stationList",stationList);
		return "equipment/equipment_add";
	}

	@RequestMapping("toupdate")
	public String toUpdate(Model model,Integer id,HttpServletRequest request){
		System.out.println("№toupdate");

		Equipment s=new Equipment();
		s.setId(id);//存储更新记录所在页数
		pageHelp.setObject(s);

		Equipment equipment=equipmentService.selectByKey(pageHelp);
		pageHelp.setObject(equipment);

		model.addAttribute(equipment);
		model.addAttribute(pageHelp);

		//查询已分配的站点
		pageHelp.setUserPower(userPowerStr(request));
		List<Station> stationList=stationService.listByUserPower(pageHelp);

		model.addAttribute("stationList",stationList);
		return "equipment/equipment_update";
	}

	@RequestMapping("update")
	public @ResponseBody int update(HttpServletRequest request,HttpServletResponse response,Model model)
			throws IOException ,ParseException {
		System.out.println("№:update");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String id= PageStr.getParameterStr("id",request);
		String name= PageStr.getParameterStr("name",request);
		String number= PageStr.getParameterStr("number",request);
		String typeId= PageStr.getParameterStr("typeId",request);
		String lifetime= PageStr.getParameterStr("lifetime",request);
		String manufactor= PageStr.getParameterStr("manufactor",request);
		String max= PageStr.getParameterStr("max",request);
		String min= PageStr.getParameterStr("min",request);
		String samplingFrequency= PageStr.getParameterStr("samplingFrequency",request);
		String installTime= PageStr.getParameterStr("installTime",request);
		String stationId= PageStr.getParameterStr("stationId",request);
		String state= PageStr.getParameterStr("state",request);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		Equipment equipment=new Equipment();
		equipment.setId(Integer.parseInt(id));
		if(!name.equals("")){
			equipment.setName(name);
		}if(!number.equals("")){
			equipment.setNumber(number);
		}if(!typeId.equals("")){
			equipment.setTypeId(Integer.parseInt(typeId));
		}if(!lifetime.equals("")){
			equipment.setLifetime(lifetime);
		}if(!manufactor.equals("")){
			equipment.setManufactor(manufactor);
		}if(!max.equals("")){
			equipment.setMax(Long.parseLong(max));
		}if(!min.equals("")){
			equipment.setMin(Long.parseLong(min));
		}if(!samplingFrequency.equals("")){
			equipment.setSamplingFrequency(Long.parseLong(samplingFrequency));
		}if(!installTime.equals("")){
			equipment.setInstallTime(DateUtils.getAmericanDate(installTime,"MM/dd/yyyy"));
		}if(!stationId.equals("")){
			equipment.setStationId(Integer.parseInt(stationId));
		}if(!state.equals("")){
			equipment.setState(Integer.parseInt(state));
		}


		equipmentService.updateEquipment(equipment);

		pageHelp.setObject(equipment);
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
		Equipment equipment=new Equipment();
		if(!selectStr.equals("")){
			equipment.setName(selectStr);

			pageHelp.setObject(equipment);
			pageHelp.setSelectStr(selectStr);
			model.addAttribute(pageHelp);
		}else {
			pageHelp.setObject(null);
	}

		//查询已分配的站点
		pageHelp.setUserPower(userPowerStr(request));
		return equipmentService.findList(pageHelp,pageNow);
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
		Equipment equipment;
		if(!name.equals("")){
			equipment=new Equipment();
			equipment.setName(name);
			pageHelp.setObject(equipment);
		}else {
			pageHelp.setObject(null);
		}

		return equipmentService.getNameCount(pageHelp);
	}

	@ResponseBody
	@RequestMapping("addequipment")
	public int addEquipment (HttpServletRequest request,Model model) throws ParseException{
		String name= PageStr.getParameterStr("name",request);
		String number= PageStr.getParameterStr("number",request);
		String typeId= PageStr.getParameterStr("typeId",request);
		String lifetime= PageStr.getParameterStr("lifetime",request);
		String manufactor= PageStr.getParameterStr("manufactor",request);
		String max= PageStr.getParameterStr("max",request);
		String min= PageStr.getParameterStr("min",request);
		String samplingFrequency= PageStr.getParameterStr("samplingFrequency",request);
		String installTime= PageStr.getParameterStr("installTime",request);
		String stationId= PageStr.getParameterStr("stationId",request);
		String state= PageStr.getParameterStr("state",request);

		Equipment equipment=new Equipment();
		if(!name.equals("")){
			equipment.setName(name);
		}if(!number.equals("")){
			equipment.setNumber(number);
		}if(!typeId.equals("")){
			equipment.setTypeId(Integer.parseInt(typeId));
		}if(!lifetime.equals("")){
			equipment.setLifetime(lifetime);
		}if(!manufactor.equals("")){
			equipment.setManufactor(manufactor);
		}if(!max.equals("")){
			equipment.setMax(Long.parseLong(max));
		}if(!min.equals("")){
			equipment.setMin(Long.parseLong(min));
		}if(!samplingFrequency.equals("")){
			equipment.setSamplingFrequency(Long.parseLong(samplingFrequency));
		}if(!installTime.equals("")){
			equipment.setInstallTime(new Date());
		}if(!stationId.equals("")){
			equipment.setStationId(Integer.parseInt(stationId));
		}if(!state.equals("")){
			equipment.setState(Integer.parseInt(state));
		}

		equipmentService.insertEquipment(equipment);

		return Value.IntNumOne;
	}


	@ResponseBody
	@RequestMapping("delete")
	public int deleteEquipment(HttpServletRequest request,Integer id){
		equipmentService.deleteEquipment(id);

		return Value.IntNumOne;
	}

	public String userPowerStr(HttpServletRequest request){
		HttpSession session=request.getSession();

		User user=(User)session.getAttribute("user");
		String power=null;
		if(user!=null){
			power=user.getPower();
		}

		return power;
	}
}
