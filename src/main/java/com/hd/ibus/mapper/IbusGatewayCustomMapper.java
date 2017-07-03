package com.hd.ibus.mapper;

import java.util.List;
import java.util.Map;

import com.hd.ibus.pojo.IbusGateway;
import com.hd.ibus.pojo.vo.IbusGatewayVo;

public interface IbusGatewayCustomMapper {

	List<IbusGateway> findList(IbusGatewayVo ibusGatewayVo);
	List<Map> findList2(IbusGatewayVo ibusGatewayVo);
	int findTotal(IbusGatewayVo ibusGatewayVo);
	
	IbusGateway findOneByGatewayPort(Integer gatewayPort);
	
	int createTable(Map map);
	
	int deleteTable(Map map);
}
