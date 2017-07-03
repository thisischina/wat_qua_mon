package com.hd.ibus.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hd.ibus.mapper.IbusProjectCustomMapper;
import com.hd.ibus.mapper.IbusProjectMapper;
import com.hd.ibus.pojo.IbusProject;
import com.hd.ibus.pojo.vo.IbusProjectVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusProjectService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;

@Service("ibusProjectService")
public class IbusProjectServiceImpl implements IbusProjectService {
	
	@Resource
	private IbusProjectMapper ibusProjectDao;
	
	@Resource
	private IbusProjectCustomMapper ibusProjectCustom;

	public String saveIbusProject(IbusProjectVo ibusProjectVo) {
		boolean result = 1==this.ibusProjectDao.insertSelective(ibusProjectVo.getIbusProject());
		return result+"";
	}

	public String updateIbusProject(IbusProjectVo ibusProjectVo) {
		boolean result = 1==this.ibusProjectDao.updateByPrimaryKeySelective(ibusProjectVo.getIbusProject());
		return result+"";
	}

	public DataGridResultInfo findList(IbusProjectVo ibusProjectVo,Integer pageNow,Integer pageSize) {
		pageNow = pageNow == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_NOW) : pageNow;
		pageSize = pageSize == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_SIZE) : pageSize;
		ibusProjectVo = ibusProjectVo == null ? new IbusProjectVo() : ibusProjectVo;

		PageBean pageBean = new PageBean(pageNow, pageSize);
		ibusProjectVo.setPageBean(pageBean);

		List<IbusProject> groups = this.ibusProjectCustom.findList(ibusProjectVo);
		Integer total = this.ibusProjectCustom.findTotal(ibusProjectVo);
			
		return new DataGridResultInfo(total, groups);
	}

	public IbusProject findById(Integer id) {
		return this.ibusProjectDao.selectByPrimaryKey(id); 
	}

	public String deleteIbusProject(Integer id) {
		boolean result = 1==this.ibusProjectDao.deleteByPrimaryKey(id);
		return result+"";
	}
	
}
