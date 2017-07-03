package com.hd.ibus.mapper;

import java.util.List;
import java.util.Map;

public interface IbusOperationCustomMapper {
	List<Map> findAllByUserId(Map map);
	
	int findTotalByUserId(Map map);
	
	List<Map> findAll(Map map);
	
	int findTotal(Map map);

}
