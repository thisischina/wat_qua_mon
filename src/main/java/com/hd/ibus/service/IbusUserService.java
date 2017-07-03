package com.hd.ibus.service;

import java.util.List;

import com.hd.ibus.pojo.IbusGroup;
import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.pojo.vo.IbusGroupVo;
import com.hd.ibus.pojo.vo.IbusUserVo;
import com.hd.ibus.result.DataGridResultInfo;

public interface IbusUserService {
String saveIbusUser(IbusUserVo ibusUserVo);
	
	String updateIbusUser(IbusUserVo ibusUserVo);
	
	public DataGridResultInfo findList(IbusUserVo ibusUserVo,Integer pageNow,Integer pageSize);
	
	IbusUser findById(Integer id);
	
	String deleteIbusUser(Integer id);
	
	public List<IbusUser> findAll();

	IbusUser checkAccount(String userName, String password);

	List<IbusUser> validateIbusUser(
			String userName);
	
}
