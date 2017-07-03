package com.hd.ibus.pojo.vo;

import java.io.Serializable;

import com.hd.ibus.pojo.IbusGroup;
import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.util.PageBean;

public class IbusUserVo implements Serializable {

	private static final long serialVersionUID = -3817231542610983296L;

	private IbusUser ibusUser;
	
	private PageBean pageBean;

	public IbusUser getIbusUser() {
		return ibusUser;
	}

	public void setIbusUser(IbusUser ibusUser) {
		this.ibusUser = ibusUser;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
