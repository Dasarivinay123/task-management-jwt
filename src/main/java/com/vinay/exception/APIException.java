package com.vinay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class APIException extends RuntimeException{

	private String message;

	public APIException(String message) {
		super(message);
		this.message = message;
	}
	
	
}
