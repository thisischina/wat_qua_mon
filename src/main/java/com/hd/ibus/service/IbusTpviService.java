package com.hd.ibus.service;

import java.util.List;
import java.util.Map;

import com.hd.ibus.pojo.IbusTpvi;
import com.hd.ibus.pojo.vo.IbusTpviVo;
import com.hd.ibus.result.DataGridResultInfo;

public interface IbusTpviService {

	String saveTpvi(Map map);
	
	String saveTpvi2(Map map);
	void saveTpvi22(Map map);
	
	Map findAllById(Map map);
	
	IbusTpvi findOneById(Map map);
	
	Map findAll(Map map);
	
	Map findOne(Map map);
	
	List<Map> findAllMaxTime(Map map);
	
	DataGridResultInfo findAlls(IbusTpviVo ibusTpviVo,Integer pageNow ,Integer pageSize);
}
