package com.wipro.cards.exception;

public class CardAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  CardAlreadyExistsException(String msg) {
		super(msg);
	}
}
