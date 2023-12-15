package com.project.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.bookstore.exception.BookAlreadyExistsException;
import com.project.bookstore.exception.BookNameNotFoundException;
import com.project.bookstore.exception.BookNotFoundException;
import com.project.bookstore.exception.CategoryAlreadyExistsException;
import com.project.bookstore.exception.CategoryNotFoundException;
import com.project.bookstore.exception.InternalException;
import com.project.bookstore.exception.UserAlreadyExistsException;
import com.project.bookstore.exception.UserNotFoundException;


@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler{
	

	@ExceptionHandler(InternalException.class)
	public final ResponseEntity<String> handleInternalExceptions(InternalException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	 public final ResponseEntity<String> handleAllExceptions(Exception e) {
	       return new ResponseEntity<>("Error Happened, Our teams have been warned about it", HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	@ExceptionHandler(CategoryNotFoundException.class)
	public final ResponseEntity<String> categoryNotFoundException(CategoryNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(CategoryAlreadyExistsException.class) 
	public final ResponseEntity<String> categoryAlreadyExistsException(CategoryAlreadyExistsException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(BookNotFoundException.class) 
	public final ResponseEntity<String> bookNotFoundException(BookNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(BookNameNotFoundException.class) 
	public final ResponseEntity<String> bookNameNotFoundException(BookNameNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(BookAlreadyExistsException.class) 
	public final ResponseEntity<String> bookAlreadyExistException(BookAlreadyExistsException e) {
		return new ResponseEntity<String>( e.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class) 
	public final ResponseEntity<String> userNotFoundException(UserNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class) 
	public final ResponseEntity<String> userAlreadyExistException(UserAlreadyExistsException e) {
		return new ResponseEntity<String>( e.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
}
