package com.hd.ibus.mapper;

import java.util.List;
import java.util.Map;

import com.hd.ibus.pojo.IbusGroup;
import com.hd.ibus.pojo.vo.IbusGroupVo;

public interface IbusGroupCustomMapper {
    List<IbusGroup> findList(IbusGroupVo ibusGroupVo);
    List<Map> findList2(IbusGroupVo ibusGroupVo);
	int findTotal(IbusGroupVo ibusGroupVo);
	
	List<IbusGroup> findListByGatewayId(Integer gatewayId);
	
}
