package com.project.bookstore.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryAlreadyExistsException(String name) {
		super(String.format("Brand name already exists"));
	}

}
