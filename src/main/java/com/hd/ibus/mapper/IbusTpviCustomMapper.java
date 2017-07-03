package com.hd.ibus.mapper;

import java.util.List;
import java.util.Map;

import com.hd.ibus.pojo.IbusTpvi;
import com.hd.ibus.pojo.vo.IbusTpviVo;

public interface IbusTpviCustomMapper {
	
	Map findAllById(Map map);
	
	IbusTpvi findOneById(Map map);

	int insertSelective(Map map);
	
	Map findAll(Map map);
	
	Map findOne(Map map);
	
	List<Map> findAllMaxTime(Map map);
	
	List<Map> findAlls(IbusTpviVo ibusTpviVo);
	
	int findTotal(IbusTpviVo ibusTpviVo);
}
