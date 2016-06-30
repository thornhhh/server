package com.call110.common.util;


/**
 * @description 全局变量类 
 */
public final class GlobalContext {
//	private static final Logger logger = Logger.getLogger(GlobalContext.class);

	/**
	 * 时间格式
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	/**
	 * 页面分页信息
	 */
	public static final Integer PAGE_SIZE  = 15; // 每页记录数
	//密码初始值
	public static final String PASSWORD_DEFAULT_VALUE = "12345678";
	//返回成功
	public static final String RESULT_SUCCESS = "success";
	//返回失败
	public static final String RESULT_FAILURE = "failure";
	//返回错误信息
	public static final String RESULT_ERRORMESSAGE = "errorMessage";
	//返回成功信息
	public static final String RESULT_SUCCESSMESSAGE = "successMessage";
	//系统根文件目录
	public static final String basePath = GlobalContext.class.getClassLoader().getResource("").toString().substring(6);
}
