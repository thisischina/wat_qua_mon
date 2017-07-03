package com.hd.ibus.pojo.vo;

import java.io.Serializable;

import com.hd.ibus.pojo.IbusGroup;
import com.hd.ibus.util.PageBean;

public class IbusGroupVo implements Serializable {
	public IbusGroup getIbusGroup() {
		return ibusGroup;
	}

	public void setIbusGroup(IbusGroup ibusGroup) {
		this.ibusGroup = ibusGroup;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3817231542610983296L;

	private IbusGroup ibusGroup;
	
	private PageBean pageBean;
	
	
}
