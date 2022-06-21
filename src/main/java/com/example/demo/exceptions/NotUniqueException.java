package com.example.demo.exceptions;

public class NotUniqueException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotUniqueException(String myMessage) {
		super(myMessage);
	}
	
	public NotUniqueException() {
		super();
	}
	

}
