package com.project.bookstore.exception;

public class BookAlreadyExistsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookAlreadyExistsException(String name) {
		super(String.format("Book name already exists"));
	}

}
