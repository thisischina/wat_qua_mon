package com.hd.ibus.pojo.vo;

import java.io.Serializable;

import com.hd.ibus.pojo.IbusProject;
import com.hd.ibus.util.PageBean;

public class IbusProjectVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8228911820262692506L;

	private IbusProject ibusProject;
	
	private PageBean pageBean;

	public IbusProject getIbusProject() {
		return ibusProject;
	}

	public void setIbusProject(IbusProject ibusProject) {
		this.ibusProject = ibusProject;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
