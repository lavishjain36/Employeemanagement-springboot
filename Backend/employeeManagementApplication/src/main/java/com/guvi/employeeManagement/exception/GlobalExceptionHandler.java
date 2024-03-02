package com.guvi.employeeManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorObject errorObject = new ErrorObject(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

	/*
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException ex){
		ErrorObject obj = new ErrorObject();
		obj.setStatus(HttpStatus.NOT_FOUND.value());
		obj.setMessage(ex.getMessage());
		obj.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorObject>(obj,HttpStatus.NOT_FOUND);
	}
	*/
   /*
	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleNoDataFoundException(NoDataFoundException ex){
		ErrorObject obj1 = new ErrorObject();
		obj1.setStatus(HttpStatus.NO_CONTENT.value());
		obj1.setMessage(ex.getMessage());
		obj1.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorObject>(obj1,HttpStatus.OK);
	}*/
	
}
