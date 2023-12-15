package com.project.bookstore.exception;

public class BookNameNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNameNotFoundException(String name) {
		super(String.format("Book name not found" ));
	}

}
