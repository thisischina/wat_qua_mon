package com.hd.ibus.pojo.vo;

import java.io.Serializable;

import com.hd.ibus.pojo.IbusGateway;
import com.hd.ibus.util.PageBean;

public class IbusGatewayVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7749014135878222501L;

	private IbusGateway ibusGateway;
	
	private PageBean pageBean;

	public IbusGateway getIbusGateway() {
		return ibusGateway;
	}

	public void setIbusGateway(IbusGateway ibusGateway) {
		this.ibusGateway = ibusGateway;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
