package com.hd.ibus.util;

/**
 * 系统参数配置类，配置了系统用到参数
 */
public class Config {

	/**
	 * 系统语言环境，默认为中文zh
	 */
	public static final String LANGUAGE = "zh";

	/**
	 * 系统国家环境，默认为中国CN
	 */
	public static final String COUNTRY = "CN";
	
	/**
	 * servletContext
	 */
	public static final String SERVLETCONTEXT_KEY="servletContext";
	
	/**
	 * 提示信息配置文件名
	 */
	public static final String MESSAGE = "properties.messages";
	
	/**
	 * 系统配置文件名
	 */
	public static final String CONFIG = "properties.config";
	
	/**
	 * session中存放登录用户的key名称
	 */
	public static final String ACTIVEUSER_KEY = "activeUser";
	
	/**
	 * 公开权限，用户无需登录即可使用
	 */
	public static final String ANONYMOUS_ACTIONS = "anonymousActions";
	
	/**
	 * 管理员名称
	 */
	public static final String ADMIN = "admin";
	
	/**
	 * 页大小
	 */
	public static final String PAGE_SIZE = "pageSize";
	/**
	 * 默认当前页
	 */
	public static final String PAGE_NOW = "pageNow";
	/**
	 * 验证码
	 */
	public static final String VERIFY_CODE = "verifyCode";
}
