package com.hd.ibus.mapper;

import java.util.List;

import com.hd.ibus.pojo.IbusProject;
import com.hd.ibus.pojo.vo.IbusProjectVo;

public interface IbusProjectCustomMapper {

	List<IbusProject> findList(IbusProjectVo ibusProjectVo);
	
	int findTotal(IbusProjectVo ibusProjectVo);
}
