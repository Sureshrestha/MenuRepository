package com.training.menu.models;

import org.springframework.http.HttpStatus;

public class MenuException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	 private final HttpStatus httpStatusCode;
	    private final String message;
	    public MenuException(String message, HttpStatus httpStatusCode, Throwable throwable) {
	        super(message, throwable);
	        this.message = message;
	        this.httpStatusCode = httpStatusCode;
	    }
	    public MenuException(String message, HttpStatus httpStatusCode) {
	        super(message);
	        this.message = message;
	        this.httpStatusCode = httpStatusCode;
	    }

}
