package com.hd.ibus.pojo.vo;

import java.io.Serializable;

import com.hd.ibus.pojo.IbusOperation;
import com.hd.ibus.util.PageBean;

public class IbusOperationVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IbusOperation ibusOperation;
	private PageBean pageBean;
	public IbusOperation getIbusOperation() {
		return ibusOperation;
	}
	public void setIbusOperation(IbusOperation ibusOperation) {
		this.ibusOperation = ibusOperation;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
