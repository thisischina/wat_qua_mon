package com.hd.ibus.pojo.vo;

import java.io.Serializable;

import com.hd.ibus.pojo.IbusTpvi;
import com.hd.ibus.util.PageBean;

public class IbusTpviVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IbusTpvi ibusTpvi;
	
	private PageBean pageBean;
	
	private String startTime;
	
	private String endTime;
	
	private String tableName;
	
	

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public IbusTpvi getIbusTpvi() {
		return ibusTpvi;
	}

	public void setIbusTpvi(IbusTpvi ibusTpvi) {
		this.ibusTpvi = ibusTpvi;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
