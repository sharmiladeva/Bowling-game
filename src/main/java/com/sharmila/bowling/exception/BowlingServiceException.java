package com.sharmila.bowling.exception;

public class BowlingServiceException extends Exception{

	private String errormsg;

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	public BowlingServiceException(String errorMsg)
	{
		this.errormsg=errorMsg;
	}
	
}
