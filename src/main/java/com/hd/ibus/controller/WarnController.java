package com.hd.ibus.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.ibus.pojo.IbusWarn;
import com.hd.ibus.pojo.vo.IbusWarnVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusWarnService;

@Controller
@RequestMapping("warn")
public class WarnController {
	
	@Resource
	private IbusWarnService ibusWarnService;
	
	
	@RequestMapping("/findList")
	public @ResponseBody DataGridResultInfo findList(HttpServletRequest request,HttpServletResponse response,Integer pageNow,Integer pageSize)
			throws IOException {
		int nodeId = Integer.parseInt(request.getParameter("nodeId"));
		int gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
		IbusWarnVo ibusWarnVo = new IbusWarnVo();
		IbusWarn ibusWarn = new IbusWarn();
		ibusWarn.setNodeId(nodeId);
		ibusWarn.setGatewayId(gatewayId);
		ibusWarnVo.setIbusWarn(ibusWarn);
		return this.ibusWarnService.findList(ibusWarnVo,pageNow,pageSize);
	}
}
