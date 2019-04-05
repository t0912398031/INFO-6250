package com.neu.edu.exception;

public class ClientException extends Exception {

	public ClientException(String message)
	{
		super("ClientException-"+message);
	}
	
	public ClientException(String message, Throwable cause)
	{
		super("ClientException-"+message,cause);
	}
	
}
