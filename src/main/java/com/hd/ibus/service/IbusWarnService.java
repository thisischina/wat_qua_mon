package com.hd.ibus.service;

import java.util.Map;

import com.hd.ibus.pojo.vo.IbusWarnVo;
import com.hd.ibus.result.DataGridResultInfo;

public interface IbusWarnService {

	DataGridResultInfo findList(IbusWarnVo ibusWarnVo,Integer pageNow,Integer pageSize);
	
	String saveWarn(Map map);
	void saveWarn2(Map map);
}
