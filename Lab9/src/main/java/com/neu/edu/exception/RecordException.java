package com.neu.edu.exception;

public class RecordException extends Exception {

	public RecordException(String message)
	{
		super("RecordException-"+message);
	}
	
	public RecordException(String message, Throwable cause)
	{
		super("RecordException-"+message,cause);
	}
	
}
