package com.hd.ibus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hd.ibus.mapper.IbusExistMapper;
import com.hd.ibus.pojo.IbusExist;
import com.hd.ibus.service.IbusExistService;

@Service("ibusExistService")
public class IbusExistServiceImpl implements IbusExistService {

	@Resource
	private IbusExistMapper ibusExistDao;

	public String saveExist(IbusExist ibusExist) {
		boolean result = this.ibusExistDao.insertSelective(ibusExist)==1?true:false;
		return result+"";
	}

	public List<IbusExist> findAll() {
		return this.ibusExistDao.selectAll();
	}

	public IbusExist findByTableName(String tableName) {
		return this.ibusExistDao.findByTableName(tableName); 
	}

	public int deleteExist(int id) {
		return this.ibusExistDao.deleteByPrimaryKey(id); 
	}

	public int deleteExistByTableName(String tableName) {
		return this.ibusExistDao.deleteByTableName(tableName); 
	}
	
}
