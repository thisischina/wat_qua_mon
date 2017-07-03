package com.hd.ibus.pojo.vo;

import java.io.Serializable;

import com.hd.ibus.pojo.IbusTp;
import com.hd.ibus.util.PageBean;

public class IbusTpVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8928041351831218675L;
	private IbusTp ibusTp;
	private PageBean pageBean;
	public IbusTp getIbusTp() {
		return ibusTp;
	}
	public void setIbusTp(IbusTp ibusTp) {
		this.ibusTp = ibusTp;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
}
