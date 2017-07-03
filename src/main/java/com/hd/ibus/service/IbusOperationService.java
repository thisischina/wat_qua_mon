package com.hd.ibus.service;

import java.util.Map;

import com.hd.ibus.pojo.IbusOperation;
import com.hd.ibus.result.DataGridResultInfo;

public interface IbusOperationService {

	int insertOperation(IbusOperation ibusOperation);
	
	DataGridResultInfo findAllByUserId(Map map,Integer pageNow);
	
	DataGridResultInfo findAll(Map map,Integer pageNow);
}
