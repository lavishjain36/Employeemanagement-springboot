package com.guvi.employeeManagement.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorObject {
	
	private int status;
	private String message;
	private long timestamp;

	public ErrorObject() {
		
	}
	
	 public ErrorObject(int status, String message, long timestamp) {
	        this.status = status;
	        this.message = message;
	        this.timestamp = timestamp;
	    }
}
