package com.hd.ibus.util;

public class Page {

	// 当前页面的页数  
    private int pageIndex;  
    // 页面大小  
    private int pageSize;  
    // 数据总量  
    private int totalData = -1;  
    // 剩余数据量  
    private int surplusData;  
    // 页面总量  
    private int totalPage;  
    // 是否仅取第一页  
    private boolean firstPage;  
    // 是否可以工作。默认为false，只有设置了数据总量才为true。  
    private boolean ready = false;  
    
    // 默认当前页面页数为第一页  
    private static final int DEFAULT_PAGE_INDEX = 1;  
    // 默认页面大小为10  
    private static final int DEFAULT_PAGE_SIZE = 10;  
      
    /** 
     * 以默认当前页面和页面大小构造一个分页对象。 
     * 其中，默认当前页数为1，默认页面大小为10。 
     */  
    public Page() {  
        this.pageIndex = DEFAULT_PAGE_INDEX;  
        this.pageSize = DEFAULT_PAGE_SIZE;  
    }  
      
    /** 
     * 以指定的当前页面页数和页面大小构造一个分页对象。 
     * @param pageIndex 当前页数，若参数值不大于0，则使用默认值1。 
     * @param pageSize 页面大小，若参数值不大于0，则使用默认值10。 
     */  
    public Page(int pageIndex, int pageSize) {  
        this.pageIndex = pageIndex > 0 ? pageIndex : DEFAULT_PAGE_INDEX;  
        this.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;  
    }  
      
    /** 
     * 以指定的页面大小构造一个表示第一页的分页对象。 
     * @param pageSize 页面大小，若参数值不大于0，则使用默认值10。 
     * @return 构造好的第一页分页对象。 
     */  
    public static Page newFirstPage(int pageSize) {  
        Page page = new Page(1, pageSize);  
        page.setFirstPage(true);  
        page.setTotalData(pageSize);  
      
        return page;  
    }  
    
    /** 
     * 设置数据总量。在使用时，需提前调用此方法进行设置。 
     * 当数据总量设置好之后，会计算页面总量、修正当前页面页数、计算剩余数据量， 
     * 并设置该分页对象已经准备好，可以正常工作。 
     * @param totalData 数据总量。 
     */  
    public void setTotalData(int totalData) {  
        this.totalData = totalData;  
      
        this.totalPage = this.totalData / pageSize +  
                (this.totalData % pageSize == 0 ? 0 : 1);  
      
        if (this.pageIndex > this.totalPage) {  
            this.pageIndex = this.totalPage;  
        }  
      
        this.surplusData = this.totalData - (this.pageIndex - 1) * this.pageSize;  
      
        this.ready = true;  
    }  
    
    public static void main(String[] args){
    	System.out.println(0/10);
    }
    
    /** 
     * 获取当前分页对象的页面范围，包含当前页面的起始行和结束行。 
     * 如果未先调用setTotalData()方法设置数据总量，则会抛出运行时异常。 
     * @return 当前分页对象的页面范围。 
     * @throws java.lang.IllegalArgumentException 异常。 
     */  
    public PageScope getPageScope() throws IllegalArgumentException {  
        if (!ready) {  
            throw new IllegalArgumentException("请先调用设置数据总量方法！");  
        }  
      
        if (this.pageIndex > this.totalPage) {  
            return null;  
        }  
      
        PageScope scope = new PageScope();  
        int startLine = (this.pageIndex - 1) * this.pageSize;  
        int endLine;  
        if (this.surplusData < this.pageSize) {  
            endLine = startLine + this.surplusData - 1;  
        } else {  
            endLine = startLine + this.pageSize - 1;  
        }  
        if (startLine < 0) {  
            startLine = 0;  
        }  
        if (endLine < 0) {  
            endLine = 0;  
        }  
        scope.setStartLine(startLine);  
        scope.setEndLine(endLine);  
      
        return scope;  
    }

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getSurplusData() {
		return surplusData;
	}

	public void setSurplusData(int surplusData) {
		this.surplusData = surplusData;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public int getTotalData() {
		return totalData;
	}  
    
    
    
}
