package com.youxu.common.exception;
/**
 * ������Я���쳣
 */
public class ErrorCodeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -745643562680374744L;
	/**
	 * �������
	 */
	private final int errorCode;
	

	public int getErrorCode() {
		return errorCode;
	}

	public ErrorCodeException(int errorCode){
		this.errorCode = errorCode;
	}
}
