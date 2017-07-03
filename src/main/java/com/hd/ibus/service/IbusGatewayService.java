package com.hd.ibus.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import com.hd.ibus.pojo.IbusGateway;
import com.hd.ibus.pojo.vo.IbusGatewayVo;
import com.hd.ibus.result.DataGridResultInfo;

public interface IbusGatewayService {
	
	String saveIbusGateway2();

	String saveIbusGateway(IbusGatewayVo ibusGatewayVo,IbusExistService ibusExistService,IbusTpviService ibusTpviService,IbusWarnService ibusWarnService);
	
	String updateIbusGateway(IbusGatewayVo ibusGatewayVo);
	
	public DataGridResultInfo findList(IbusGatewayVo ibusGatewayVo,Integer pageNow,Integer pageSize);
	
	IbusGateway findById(Integer id);
	
	String deleteIbusGateway(Integer id,IbusExistService ibusExistService);
	public List<IbusGateway> findAll();
   
	IbusGateway findOneByGatewayPort(Integer port);
	
	boolean createTable(Map map);
}
