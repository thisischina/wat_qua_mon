package com.hd.ibus.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hd.ibus.mapper.IbusWarnCustomMapper;
import com.hd.ibus.pojo.IbusWarn;
import com.hd.ibus.pojo.vo.IbusWarnVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusWarnService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;

@Service("ibusWarnService")
public class IbusWarnServiceImpl implements IbusWarnService {

	@Resource
	private IbusWarnCustomMapper ibusWarnCustomDao;

	public DataGridResultInfo findList(IbusWarnVo ibusWarnVo,Integer pageNow,Integer pageSize) {
		pageNow = PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_NOW);
		pageSize = PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_SIZE);
		ibusWarnVo = ibusWarnVo == null ? new IbusWarnVo() : ibusWarnVo;
		PageBean pageBean = new PageBean(pageNow, pageSize);
		ibusWarnVo.setPageBean(pageBean);
		List<IbusWarn> list =  this.ibusWarnCustomDao.findList(ibusWarnVo); 
		int total = this.ibusWarnCustomDao.findTotal(ibusWarnVo);
		return new DataGridResultInfo(total,list);
	}

	public String saveWarn(Map map) {
		long start = System.currentTimeMillis();
		boolean result = this.ibusWarnCustomDao.insertSelective(map)==1?true:false;
		long end = System.currentTimeMillis();
		System.out.println("service saveWarn:"+(end-start));
		return result+"";
	}
	
	public void saveWarn2(Map map) {
		this.ibusWarnCustomDao.insertSelective(map);
	}
	
}
