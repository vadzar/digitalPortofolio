package com.personal.website.digitalPortofolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFoundException extends RuntimeException {
	public ApiNotFoundException(String message) {
		super(message);
	}

	public ApiNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
