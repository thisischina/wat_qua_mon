package com.hd.ibus.pojo.vo;

import java.io.Serializable;

import com.hd.ibus.pojo.IbusNode;
import com.hd.ibus.util.PageBean;

public class IbusNodeVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9065079222730384506L;

	private IbusNode ibusNode;
	
	private PageBean pageBean;

	public IbusNode getIbusNode() {
		return ibusNode;
	}

	public void setIbusNode(IbusNode ibusNode) {
		this.ibusNode = ibusNode;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
