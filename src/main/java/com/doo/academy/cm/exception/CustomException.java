package com.doo.academy.cm.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

	public CustomException(HttpStatus httpStatus, String msg) {
		super(msg);
		this.httpStatus = httpStatus;
	}
}
