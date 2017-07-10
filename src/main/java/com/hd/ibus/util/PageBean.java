package com.hd.ibus.util;

import java.io.Serializable;

/**
 * mysql 分页 ： select * from x where id limit satrt,pageSize;
 * 
 * limit 起始页的前一个位置，每页大小
 * 
 * @author Administrator
 *
 */
public class PageBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int pageNow;
	private int pageSize;
	private int start;

	public PageBean() {
		super();
	}

	public PageBean(int pageNow, int pageSize) {
		this.pageNow = pageNow;
		this.pageSize = pageSize;

		this.start = (pageNow - 1) * pageSize;
	}

	public void getStartRow(int pageNow, int pageSize) {
		this.pageNow = pageNow;
		this.pageSize = pageSize;

		this.start = (pageNow - 1) * pageSize;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
