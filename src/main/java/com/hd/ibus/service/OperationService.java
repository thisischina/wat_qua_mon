package com.hd.ibus.service;

import java.util.Map;

import com.hd.ibus.pojo.Operation;
import com.hd.ibus.result.DataGridResultInfo;

public interface OperationService {

    int insertOperation(Operation operation);

//	DataGridResultInfo findAllByUserId(Map map,Integer pageNow);
//
//	DataGridResultInfo findAll(Map map,Integer pageNow);
}
