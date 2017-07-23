package com.hd.ibus.mapper;

import java.util.List;
import java.util.Map;

public interface IbusSatisticsMapper {
	
	List<Map> findList(Map<String, Object> map);
	Integer findTotal(Map<String, Object> map);
	List<Map> findAllGroup(int gateway_id);
	List<Map> findAllGateway();
	List<Map> findAllNode(int group_id);
	List<Map> findList1(Map<String, Object> map);
	Integer findTotal1(Map<String, Object> map);
	Integer findCount();
	List<Map> findAllWarn(Map<String, Object> map);
	Integer findAllWarnCount(Map<String, Object> map);
	Integer updateState(Integer id);
}
