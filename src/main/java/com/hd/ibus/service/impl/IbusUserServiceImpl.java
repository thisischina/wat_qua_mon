package com.hd.ibus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hd.ibus.mapper.IbusUserCustomMapper;
import com.hd.ibus.mapper.IbusUserMapper;
import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.pojo.vo.IbusUserVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusUserService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.MD5Util;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;

@Service("ibusUserService")
public class IbusUserServiceImpl implements IbusUserService {

	@Resource
	private IbusUserMapper ibusUserDao;
	
	@Resource
	private IbusUserCustomMapper ibusUserCustom;

	public String saveIbusUser(IbusUserVo ibususerVo) {
		String password = ibususerVo.getIbusUser().getPassword();
		password = MD5Util.MD5(password);
		ibususerVo.getIbusUser().setPassword(password);
		boolean result = 1==this.ibusUserDao.insertSelective(ibususerVo.getIbusUser());
		return result+"";
	}

	public String updateIbusUser(IbusUserVo ibususerVo) {
		boolean result = 1==this.ibusUserDao.updateByPrimaryKeySelective(ibususerVo.getIbusUser());
		return result+"";
	}

	public DataGridResultInfo findList(IbusUserVo ibususerVo,Integer pageNow,Integer pageSize) {
		pageNow = pageNow == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_NOW) : pageNow;
		pageSize = pageSize == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_SIZE) : pageSize;
		ibususerVo = ibususerVo == null ? new IbusUserVo() : ibususerVo;

		PageBean pageBean = new PageBean(pageNow, pageSize);
		ibususerVo.setPageBean(pageBean);

		List<IbusUser> users = this.ibusUserCustom.findList(ibususerVo);
		Integer total = this.ibusUserCustom.findTotal(ibususerVo);
			
		return new DataGridResultInfo(total, users);
	}

	public IbusUser findById(Integer id) {
		return this.ibusUserDao.selectByPrimaryKey(id); 
	}

	public String deleteIbusUser(Integer id) {
		boolean result = 1==this.ibusUserDao.deleteByPrimaryKey(id);
		return result+"";
	}

	public List<IbusUser> findAll() {
		return this.ibusUserCustom.findList(null); 
	}

	public IbusUser checkAccount(String userName, String password) {
		Map map = new HashMap();
		map.put("userName", userName);
		map.put("password", password);
		List<IbusUser> list =  this.ibusUserCustom.findByAccount(map); 
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public List<IbusUser> validateIbusUser(String userName) {
		Map map = new HashMap();
		map.put("userName", userName);
		List<IbusUser> list =  this.ibusUserCustom.validateIbusUser(map); 
		return list;
	}
	
}
