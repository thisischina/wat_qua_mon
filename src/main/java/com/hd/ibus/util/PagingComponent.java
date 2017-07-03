package com.hd.ibus.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PagingComponent {
	private int pageSize = 10;

	public List<Object> paging(List list,Map map){
		Page page = createPage(map);
		page.setTotalData(list.size());
		PageScope scope = page.getPageScope();
		pageSize = page.getPageSize();
		return queryObject(list,scope);
	}
	
	
	private Page createPage(Map map){
		Page page;
		String pageIndex =  (String) map.get("pageIndex");
		String pageSize = (String) map.get("pageSize");
		if(pageIndex!=null&&pageSize!=null){
			page = new Page(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		}else{
			page = new Page();
			if(pageIndex!=null)
				page.setPageIndex(Integer.parseInt(pageIndex));
			if(pageSize!=null)
				page.setPageSize(Integer.parseInt(pageSize));
		}
		return page;
	}
	
	private List<Object> queryObject(List list,PageScope pageScope){
		List<Object> reList = new ArrayList<Object>();
		int startLine = pageScope.getStartLine();
		int endLine = pageScope.getEndLine();
		for(int i=startLine;i<=endLine;i++){
			reList.add(list.get(i));
		}
		return reList;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
