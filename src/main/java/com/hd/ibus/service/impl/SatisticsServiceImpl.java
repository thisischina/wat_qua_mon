package com.hd.ibus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hd.ibus.mapper.IbusSatisticsMapper;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.SatisticsService;

@Service("satisticsService")
public class SatisticsServiceImpl implements SatisticsService {
	@Resource
	private IbusSatisticsMapper satisticsMapper;
	
	public DataGridResultInfo nodePageData(Integer pageNow,
			Integer pageSize, String nodeAddress,String gatewayid,String startdate,String enddate) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", (pageNow - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("nodeAddress", nodeAddress);
		map.put("gatewayid", gatewayid);
		map.put("startdate", startdate);
		map.put("enddate", enddate);
		List<Map> groups = this.satisticsMapper.findList(map);
		Integer total = this.satisticsMapper.findTotal(map);
		return new DataGridResultInfo(total, groups);
	}

	public List<Map> findAllNode(int group_id) {
		List<Map> map = satisticsMapper.findAllNode(group_id);
		// TODO Auto-generated method stub
		return map;
	}

	public List<Map> findAllGroup(int gateway_id) {
		List<Map> map = satisticsMapper.findAllGroup(gateway_id);
		// TODO Auto-generated method stub
		return map;
	}

	public List<Map> findAllGateway() {
		List<Map> map = satisticsMapper.findAllGateway();
		// TODO Auto-generated method stub
		return map;
	}
	
	public DataGridResultInfo warnPageData(Integer pageNow,
			Integer pageSize, String nodeAddress,String gatewayid,String startdate,String enddate) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", (pageNow - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("nodeAddress", nodeAddress);
		map.put("gatewayid", gatewayid);
		map.put("startdate", startdate);
		map.put("enddate", enddate);
		List<Map> groups = this.satisticsMapper.findList1(map);
		Integer total = this.satisticsMapper.findTotal1(map);
		return new DataGridResultInfo(total, groups);
	}

	public DataGridResultInfo findAllWarn(Integer pageNow, Integer pageSize,
			String gatewayid) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start", (pageNow - 1) * pageSize);
		map.put("pageSize", pageSize);
		
		if("".equals(gatewayid)||gatewayid==null){
			map.put("gatewayid", null);
		}else{
			map.put("gatewayid", gatewayid);
		}
		
		List<Map> list=this.satisticsMapper.findAllWarn(map);
		Integer total = this.satisticsMapper.findAllWarnCount(map);
		return new DataGridResultInfo(total,list);
	}

	public String updateState(Integer id) {
		boolean result = 1 == this.satisticsMapper.updateState(id);
		return result + "";
	}

	public Integer findCount() {
		Integer count=this.satisticsMapper.findCount();
		return count;
	}
	
	
}
