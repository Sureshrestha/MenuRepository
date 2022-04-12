package com.training.menu.models;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	
	 private final HttpStatus httpStatusCode;
	    private final String message;
	    public UserException(String message, HttpStatus httpStatusCode, Throwable throwable) {
	        super(message, throwable);
	        this.message = message;
	        this.httpStatusCode = httpStatusCode;
	    }
	    public UserException(String message, HttpStatus httpStatusCode) {
	        super(message);
	        this.message = message;
	        this.httpStatusCode = httpStatusCode;
	    }

}
