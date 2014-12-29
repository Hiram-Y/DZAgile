package com.decade.agile.exception;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-5-31
 * @preserve protected
 */
public class DCException extends Exception{
	private static final long serialVersionUID = 1L;

	public DCException() {
		super();
	}

	public DCException(String msg) {
		super(msg);
	}
	
	public DCException(Throwable cause) {
		super(cause);
	}
	
	public DCException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DCException(int code, Throwable cause){
		super(cause);
	}
	
	public DCException(int code,String msg) {
		super(msg);
	}
	
	public DCException(int code,String msg, Throwable cause) {
		super(msg, cause);
	}
}
