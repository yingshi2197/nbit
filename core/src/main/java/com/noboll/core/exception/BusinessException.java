package com.noboll.core.exception;


/**
 * 
* @ClassName: BusinessException
* @Description: 业务异常类
* @author kent.wang@noboll.com.cn
* @date 2015年4月11日 下午1:16:59
*
 */
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BusinessException(String msg) {
		super(msg);
	}
	
	
}
