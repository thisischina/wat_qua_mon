package com.hd.ibus.result;



/**
 * 自定义系统异常类
 */
public class ExceptionResultInfo extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 系统统一使用的结果类，包括了 提示信息类型和信息内�?
	private ResultInfo resultInfo;

	public ExceptionResultInfo(ResultInfo resultInfo) {
		super(resultInfo.getMessage());
		this.resultInfo = resultInfo;
	}

	public ResultInfo getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}

}
