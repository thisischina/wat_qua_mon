package com.hd.ibus.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hd.ibus.mapper.IbusGroupCustomMapper;
import com.hd.ibus.mapper.IbusGroupMapper;
import com.hd.ibus.pojo.IbusGroup;
import com.hd.ibus.pojo.vo.IbusGroupVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusGroupService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;

@Service("ibusGroupService")
public class IbusGroupServiceImpl implements IbusGroupService {

	@Resource
	private IbusGroupMapper ibusGroupDao;
	
	@Resource
	private IbusGroupCustomMapper ibusGroupCustom;

	public String saveIbusGroup(IbusGroupVo ibusGroupVo) {
		boolean result = 1==this.ibusGroupDao.insertSelective(ibusGroupVo.getIbusGroup());
		return result+"";
	}

	public String updateIbusGroup(IbusGroupVo ibusGroupVo) {
		boolean result = 1==this.ibusGroupDao.updateByPrimaryKeySelective(ibusGroupVo.getIbusGroup());
		return result+"";
	}

	public DataGridResultInfo findList(IbusGroupVo ibusGroupVo,Integer pageNow,Integer pageSize) {
		pageNow = pageNow == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_NOW) : pageNow;
		pageSize = pageSize == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_SIZE) : pageSize;
		ibusGroupVo = ibusGroupVo == null ? new IbusGroupVo() : ibusGroupVo;

		PageBean pageBean = new PageBean(pageNow, pageSize);
		ibusGroupVo.setPageBean(pageBean);

		//List<IbusGroup> groups = this.ibusGroupCustom.findList(ibusGroupVo);
		List<Map> groups = this.ibusGroupCustom.findList2(ibusGroupVo);
		Integer total = this.ibusGroupCustom.findTotal(ibusGroupVo);
			
		return new DataGridResultInfo(total, groups);
	}

	public IbusGroup findById(Integer id) {
		return this.ibusGroupDao.selectByPrimaryKey(id); 
	}

	public String deleteIbusGroup(Integer id) {
		boolean result = 1==this.ibusGroupDao.deleteByPrimaryKey(id);
		return result+"";
	}

	public List<IbusGroup> findAll() {
		return this.ibusGroupCustom.findList(null); 
	}

	public List<IbusGroup> findListByGatewayId(Integer gatewayId) {
		return this.ibusGroupCustom.findListByGatewayId(gatewayId); 
	}
}
