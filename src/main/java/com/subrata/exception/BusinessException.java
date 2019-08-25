package com.subrata.exception;


/**
 * A base class for all business related exceptions.
 * Exceptions can be defined based on business scenarios.
 */
public class BusinessException extends Exception {

	static final long serialVersionUID = 4157262600607325995L;
	
	/**
	 * BusinessException	 
	 */
	public BusinessException(String msg) {
		super(msg);
	}
}
