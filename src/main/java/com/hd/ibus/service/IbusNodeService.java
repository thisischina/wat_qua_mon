package com.hd.ibus.service;

import java.util.List;
import java.util.Map;

import com.hd.ibus.pojo.IbusNode;
import com.hd.ibus.pojo.vo.IbusNodeVo;
import com.hd.ibus.result.DataGridResultInfo;

public interface IbusNodeService {
	String saveIbusNode(IbusNodeVo ibusNodeVo,IbusGatewayService ibusGatewayService);

	String nodalPoint(Integer gatewayId,IbusGatewayService ibusGatewayService);
	
	String nodeControl(Integer gatewayId,Integer nodeAddr,IbusGatewayService ibusGatewayService);
	
	String updateIbusNode(IbusNodeVo ibusNodeVo);
	int insertFromExcel(Map map);
	int insertFromUrl(Map map);
	public DataGridResultInfo findList(IbusNodeVo ibusNodeVo,Integer pageNow,Integer pageSize);
	IbusNode findById(Integer id);
	
	String deleteIbusNode(Integer id);

	List getNodeByGateway(int gatewayId);
	
	List<IbusNode> findAll();
	
	List<IbusNode> findListByGroupId(Integer groupId);
	
	String updateByAddress(Integer nodeState,String nodes,Integer gatewayId);
	
	String updateIsOnlineByAddress(Integer nodeIsonline,String nodes,Integer gatewayId);

	List findNodeByGatewayId(Integer gatewayId);
}
