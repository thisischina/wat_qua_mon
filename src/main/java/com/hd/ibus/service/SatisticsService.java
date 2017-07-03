package com.hd.ibus.service;

import java.util.List;
import java.util.Map;
import com.hd.ibus.result.DataGridResultInfo;
public interface SatisticsService {

		 public DataGridResultInfo nodePageData(Integer pageNow,
				Integer pageSize, String nodeAddress,String gatewayid,String startdate,String enddate);
		 public List<Map>  findAllNode(int group_id);
		 public List<Map> findAllGroup(int gateway_id);
		 public List<Map>  findAllGateway();
		 public DataGridResultInfo warnPageData(Integer pageNow,
					Integer pageSize, String nodeAddress,String gatewayid,String startdate,String enddate);
		 public DataGridResultInfo findAllWarn(Integer pageNow,
					Integer pageSize,String gatewayid);
		public String updateState(Integer id);
		public Integer findCount();
}
