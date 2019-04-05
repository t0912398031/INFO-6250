package com.neu.edu.exception;

public class AdvertException extends Exception {

	public AdvertException(String message) {
		super("AdvertException-" + message);
	}

	public AdvertException(String message, Throwable cause) {
		super("AdvertException-" + message, cause);
	}
}