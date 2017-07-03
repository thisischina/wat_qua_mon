package com.hd.ibus.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hd.ibus.mapper.IbusTpviCustomMapper;
import com.hd.ibus.mapper.IbusTpviMapper;
import com.hd.ibus.pojo.IbusTpvi;
import com.hd.ibus.pojo.vo.IbusTpviVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusTpviService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;

@Service("ibusTpviService")
public class IbusTpviServiceImpl implements IbusTpviService {

	@Resource
	private IbusTpviMapper ibusTpviDao;
	
	@Resource
	private IbusTpviCustomMapper ibusTpviCustomDao;
	
	public String saveTpvi(Map ibusTpvi) {
		boolean result = 1==this.ibusTpviDao.insertSelective(ibusTpvi);
		//System.out.println("00000000000000000000000000000000000000000000000000000000000000000");
		return result+"";
	}
	
	public String saveTpvi2(Map ibusTpvi) {
		long start = System.currentTimeMillis();
		boolean result = 1==this.ibusTpviCustomDao.insertSelective(ibusTpvi);
		
		long end = System.currentTimeMillis();
		System.out.println("service saveTpvi2:"+(end-start));
		return result+"";
	}
	public void saveTpvi22(Map ibusTpvi) {
		this.ibusTpviCustomDao.insertSelective(ibusTpvi);
	}
	

	public Map findAllById(Map map) {
		return this.ibusTpviCustomDao.findAllById(map); 
	}

	public IbusTpvi findOneById(Map map) {
		return this.ibusTpviCustomDao.findOneById(map);
	}

	public Map findAll(Map map) {
		return this.ibusTpviCustomDao.findAll(map); 
	}

	public Map findOne(Map map) {
		return this.ibusTpviCustomDao.findOne(map); 
	}

	public DataGridResultInfo findAlls(IbusTpviVo ibusTpviVo, Integer pageNow,
			Integer pageSize) {
		ibusTpviVo = ibusTpviVo == null ? new IbusTpviVo() : ibusTpviVo;
		PageBean pageBean = new PageBean(pageNow, pageSize);
		ibusTpviVo.setPageBean(pageBean);
		System.out.println("start:"+pageBean.getStart()+",pageNow:"+pageNow);
		List<Map> list = this.ibusTpviCustomDao.findAlls(ibusTpviVo);
		int total = this.ibusTpviCustomDao.findTotal(ibusTpviVo);
		
		return new DataGridResultInfo(total,list);
	}

	public List<Map> findAllMaxTime(Map map) {
		return this.ibusTpviCustomDao.findAllMaxTime(map);
	}

	

}
