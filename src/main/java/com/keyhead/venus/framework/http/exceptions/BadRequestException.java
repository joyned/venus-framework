package com.keyhead.venus.framework.http.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 8696704456125929772L;

	public final int CODE = 400;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
