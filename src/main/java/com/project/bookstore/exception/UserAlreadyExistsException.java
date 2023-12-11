package com.project.bookstore.exception;

public class UserAlreadyExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String name) {
		super(String.format("User name already exists"));
	}

}
