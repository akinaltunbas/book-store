package com.project.bookstore.exception;

public class BookNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(final Long id) {
		super(String.format("Book not found with id : %s", id.toString()));
	}

}
