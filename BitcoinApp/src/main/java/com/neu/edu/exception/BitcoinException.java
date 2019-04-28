package com.neu.edu.exception;

public class BitcoinException extends Exception {

	public BitcoinException(String message)
	{
		super("BitcoinException-"+message);
	}
	
	public BitcoinException(String message, Throwable cause)
	{
		super("BitcoinException-"+message,cause);
	}
	
}
