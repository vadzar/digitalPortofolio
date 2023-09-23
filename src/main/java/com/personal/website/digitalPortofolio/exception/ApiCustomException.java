package com.personal.website.digitalPortofolio.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiCustomException {
	private final String message;
	private final HttpStatus httpStatus;
	private final ZonedDateTime zonedDateTime;
	private final String detail;

	public ApiCustomException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime, String detail) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.zonedDateTime = zonedDateTime;
		this.detail = detail;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ZonedDateTime getZonedDateTime() {
		return zonedDateTime;
	}

	public String getDetail() {
		return detail;
	}
}
