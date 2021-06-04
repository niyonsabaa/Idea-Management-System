package com.flyhub.ideamanagementsystem.exception;

import java.util.Date;

public class ApiException {
	
	String message;
	long timestamp;
	String path;
	String errorName;
	public ApiException( String message, String path,String errorName) {	
		
		this.message = message;	
		this.path = path;
		this.errorName= errorName;
		this.timestamp = new Date().getTime();
	}	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getErrorName() {
		return errorName;
	}
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
	
}
