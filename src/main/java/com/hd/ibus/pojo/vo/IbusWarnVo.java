package com.hd.ibus.pojo.vo;

import java.io.Serializable;

import com.hd.ibus.pojo.IbusWarn;
import com.hd.ibus.util.PageBean;

public class IbusWarnVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IbusWarn ibusWarn;
	private PageBean pageBean;
	public IbusWarn getIbusWarn() {
		return ibusWarn;
	}
	public void setIbusWarn(IbusWarn ibusWarn) {
		this.ibusWarn = ibusWarn;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
