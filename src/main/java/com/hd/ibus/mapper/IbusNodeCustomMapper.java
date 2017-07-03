package com.hd.ibus.mapper;

import java.util.List;
import java.util.Map;

import com.hd.ibus.pojo.IbusNode;
import com.hd.ibus.pojo.vo.IbusNodeVo;

public interface IbusNodeCustomMapper {
	List<IbusNode> findList(IbusNodeVo ibusNodeVo);
	List<Map> findList2(IbusNodeVo ibusNodeVo);
	
	int findTotal(IbusNodeVo ibusNodeVo);

	List getNodeByGateway(int gatewayId);
	
	List<IbusNode> findListByGroupId(Integer groupId);
	
	//根据gatewayId和nodeAddr查处节点，确保节点唯一
	IbusNode findOneByNodeAddr(Map map);
	
	int updateByAddress(Map map);
	
	int updateIsOnlineByAddress(Map map);

	List<Map> findNodeBygatewayId(Integer gatewayId);
	
	 int insertFromExcel(Map map);
	 int insertFromUrl(Map map);
}
