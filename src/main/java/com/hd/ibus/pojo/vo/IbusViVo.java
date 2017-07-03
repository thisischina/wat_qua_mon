package com.hd.ibus.pojo.vo;

import java.io.Serializable;

import com.hd.ibus.pojo.IbusVi;
import com.hd.ibus.util.PageBean;

public class IbusViVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3068485364484547274L;
	private IbusVi ibusVi;
	private PageBean pageBean;
	public IbusVi getIbusVi() {
		return ibusVi;
	}
	public void setIbusVi(IbusVi ibusVi) {
		this.ibusVi = ibusVi;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
