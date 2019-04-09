package com.neu.edu.exception;

public class BitcoinException extends Exception {

	public BitcoinException(String message)
	{
		super("OrderException-"+message);
	}
	
	public BitcoinException(String message, Throwable cause)
	{
		super("OrderException-"+message,cause);
	}
	
}
