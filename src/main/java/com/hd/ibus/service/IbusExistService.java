package com.hd.ibus.service;

import java.util.List;

import com.hd.ibus.pojo.IbusExist;

public interface IbusExistService {

	String saveExist(IbusExist ibusExist);
	
	List<IbusExist> findAll();
	
	IbusExist findByTableName(String tableName);
	
	int deleteExist(int id);
	
	int deleteExistByTableName(String tableName);
}
