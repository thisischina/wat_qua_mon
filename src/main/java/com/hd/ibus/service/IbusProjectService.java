package com.hd.ibus.service;

import com.hd.ibus.pojo.IbusProject;
import com.hd.ibus.pojo.vo.IbusProjectVo;
import com.hd.ibus.result.DataGridResultInfo;

public interface IbusProjectService {

	String saveIbusProject(IbusProjectVo ibusProjectVo);
	
	String updateIbusProject(IbusProjectVo ibusProjectVo);
	
	public DataGridResultInfo findList(IbusProjectVo ibusProjectVo,Integer pageNow,Integer pageSize);
	
	IbusProject findById(Integer id);
	
	String deleteIbusProject(Integer id);
}
