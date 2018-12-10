package com.youxu.common.exception;
/**
 * ´íÎóÂëĞ¯´øÒì³£
 */
public class ErrorCodeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -745643562680374744L;
	/**
	 * ´íÎó´úÂë
	 */
	private final int errorCode;
	

	public int getErrorCode() {
		return errorCode;
	}

	public ErrorCodeException(int errorCode){
		this.errorCode = errorCode;
	}
}
