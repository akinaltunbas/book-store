package com.project.bookstore.exception;

public class CategoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException(final Long id) {
		super(String.format("Category not found with id : %s", id.toString()));
	}



}
