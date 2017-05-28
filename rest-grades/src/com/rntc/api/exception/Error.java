package com.rntc.api.exception;

/**
 * This class represents the response error in the JSON.
 * 
 * @version 1.0
 * @author reis
 * @since 5/28/2017
 */
public class Error {

	private int status;
	private String message;

	public Error(){}
	
	public Error(int status, String message){
		this.status = status;
		this.message = message;
	}
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
