package com.hd.ibus.service;

import java.util.List;

import com.hd.ibus.pojo.IbusGroup;
import com.hd.ibus.pojo.vo.IbusGroupVo;
import com.hd.ibus.result.DataGridResultInfo;

public interface IbusGroupService {
String saveIbusGroup(IbusGroupVo ibusGroupVo);
	
	String updateIbusGroup(IbusGroupVo ibusGroupVo);
	
	public DataGridResultInfo findList(IbusGroupVo ibusGroupVo,Integer pageNow,Integer pageSize);
	
	IbusGroup findById(Integer id);
	
	String deleteIbusGroup(Integer id);
	
	public List<IbusGroup> findAll();
	
	public List<IbusGroup> findListByGatewayId(Integer gatewayId);
	
}
