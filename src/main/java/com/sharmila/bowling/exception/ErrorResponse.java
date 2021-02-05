package com.sharmila.bowling.exception;

public class ErrorResponse {

	private String errormsg;

	public ErrorResponse(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
}
