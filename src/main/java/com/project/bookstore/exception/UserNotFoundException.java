package com.project.bookstore.exception;

public class UserNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(final Long id) {
		super(String.format("User Not found with id : %s", id.toString()));
	}

}
