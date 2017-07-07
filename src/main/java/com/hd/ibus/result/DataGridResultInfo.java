package com.hd.ibus.result;

import java.util.List;

import com.hd.ibus.util.Config;
import com.hd.ibus.util.PropertiesUtils;


/**
 * 数据表格
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
public class DataGridResultInfo {
	
	/**
	 * 总记录数
	 */
	private int total;
	
	/**
	 * 数据�?
	 */
	public List list;
	
	/**
	 * 总页�?
	 */
	public int pageCount;

	public int pageNow;

	public DataGridResultInfo() {
		super();
	}
	
	
	public DataGridResultInfo(int total, List list) {
		super();
		this.total = total;
		this.list = list;
		this.pageCount = (total-1)/PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_SIZE)+1;
	}

	
	public DataGridResultInfo(List list) {
		super();
		this.list = list;
	}


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	
}
