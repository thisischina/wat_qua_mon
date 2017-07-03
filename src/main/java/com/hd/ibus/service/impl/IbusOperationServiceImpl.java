package com.hd.ibus.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hd.ibus.mapper.IbusOperationCustomMapper;
import com.hd.ibus.mapper.IbusOperationMapper;
import com.hd.ibus.pojo.IbusOperation;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusOperationService;
import com.hd.ibus.util.PageBean;

@Service("ibusOperationService")
public class IbusOperationServiceImpl implements IbusOperationService {
	
	@Resource
	private IbusOperationMapper ibusOperationDao;
	
	@Resource
	private IbusOperationCustomMapper ibusOperationCustomDao;

	public int insertOperation(IbusOperation ibusOperation) {
		return this.ibusOperationDao.insertSelective(ibusOperation);
	}

	public DataGridResultInfo findAllByUserId(Map map,Integer pageNow) {
		PageBean pageBean = new PageBean(pageNow,10);
		//pageBean.setPageSize(10);
		//pageNow = pageNow==null?1:pageNow;
		//pageBean.setPageNow(pageNow);
		map.put("pageBean", pageBean);
		List<Map> list = this.ibusOperationCustomDao.findAllByUserId(map);
		int total = this.ibusOperationCustomDao.findTotalByUserId(map);
		return new DataGridResultInfo(total,list);
	}

	public DataGridResultInfo findAll(Map map,Integer pageNow) {
		PageBean pageBean = new PageBean(pageNow,10);
		//pageBean.setPageSize(10);
		//pageNow = pageNow==null?1:pageNow;
		//pageBean.setPageNow(pageNow);
		map.put("pageBean", pageBean);
		System.out.println(pageBean.getPageNow());
		List<Map> list = this.ibusOperationCustomDao.findAll(map);
		int total = this.ibusOperationCustomDao.findTotal(map);
		return new DataGridResultInfo(total,list);
	}
	
	

}
