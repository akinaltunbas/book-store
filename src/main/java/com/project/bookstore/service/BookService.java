package com.project.bookstore.service;

import java.util.List;

import com.project.bookstore.dto.BookCreateRequestDto;
import com.project.bookstore.dto.BookUpdateRequestDto;
import com.project.bookstore.entities.Book;

public interface BookService {
	
	public Book cretateOneBook(BookCreateRequestDto newBookRequest);
	
	public List<Book> getAllBooks();
	
	public Book getOneBookById(Long bookId);
	
	public Book updatetOneBookById(Long bookId, BookUpdateRequestDto updateBookRequest);
	
	public void deleteOneBookById(Long bookId);
	
	public void checkIfBookNameExists(String name);
	
	public Book searchOneBookName(String name);
	
	public List<Book> searchAuthorBooks(String author);	

}
