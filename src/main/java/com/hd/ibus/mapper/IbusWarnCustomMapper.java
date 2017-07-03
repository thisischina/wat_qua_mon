package com.hd.ibus.mapper;

import java.util.List;
import java.util.Map;

import com.hd.ibus.pojo.IbusWarn;
import com.hd.ibus.pojo.vo.IbusWarnVo;

public interface IbusWarnCustomMapper {
	List<IbusWarn> findList(IbusWarnVo ibusWarnVo);
	
	int findTotal(IbusWarnVo ibusWarnVo);
	
	int insertSelective(Map map);
}
