package com.hd.ibus.mapper;

import java.util.List;
import java.util.Map;

import com.hd.ibus.pojo.IbusProject;
import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.pojo.vo.IbusProjectVo;
import com.hd.ibus.pojo.vo.IbusUserVo;

public interface IbusUserCustomMapper {

	List<IbusUser> findList(IbusUserVo ibusUserVo);
	
	int findTotal(IbusUserVo ibusUserVo);

	List<IbusUser> findByAccount(Map map);

	List<IbusUser> validateIbusUser(Map map);
}
