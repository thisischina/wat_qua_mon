package com.hd.ibus.service.impl;
import javax.annotation.Resource;

import com.hd.ibus.mapper.OperationMapper;
import org.springframework.stereotype.Service;

import com.hd.ibus.pojo.Operation;
import com.hd.ibus.service.OperationService;

@Service("operationService")
public class OperationServiceImpl implements OperationService {

    @Resource
    private OperationMapper operationDao;

//	@Resource
//	private OperationCustomMapper ibusOperationCustomDao;

    public int insertOperation(Operation operation) {
        return this.operationDao.insert(operation);
    }

//	public DataGridResultInfo findAllByUserId(Map map,Integer pageNow) {
//		PageBean pageBean = new PageBean(pageNow,10);
//		//pageBean.setPageSize(10);
//		//pageNow = pageNow==null?1:pageNow;
//		//pageBean.setPageNow(pageNow);
//		map.put("pageBean", pageBean);
//		List<Map> list = this.ibusOperationCustomDao.findAllByUserId(map);
//		int total = this.ibusOperationCustomDao.findTotalByUserId(map);
//		return new DataGridResultInfo(total,list);
//	}
//
//	public DataGridResultInfo findAll(Map map,Integer pageNow) {
//		PageBean pageBean = new PageBean(pageNow,10);
//		//pageBean.setPageSize(10);
//		//pageNow = pageNow==null?1:pageNow;
//		//pageBean.setPageNow(pageNow);
//		map.put("pageBean", pageBean);
//		System.out.println(pageBean.getPageNow());
//		List<Map> list = this.ibusOperationCustomDao.findAll(map);
//		int total = this.ibusOperationCustomDao.findTotal(map);
//		return new DataGridResultInfo(total,list);
//	}


}
