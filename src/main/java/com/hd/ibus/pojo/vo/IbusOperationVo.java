package com.hd.ibus.pojo.vo;

import java.io.Serializable;

import com.hd.ibus.pojo.Operation;
import com.hd.ibus.util.PageBean;

public class IbusOperationVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Operation operation;
	private PageBean pageBean;

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
