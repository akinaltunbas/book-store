package com.project.bookstore.controller.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookstore.entities.Book;
import com.project.bookstore.service.BookServiceImpl;

@RestController
@RequestMapping("/user/book")
public class UserBookController {
	
	private BookServiceImpl bookService;

	public UserBookController(BookServiceImpl bookService) {
		this.bookService = bookService;
		
	}
	
	@GetMapping("/listBook")
	public ResponseEntity<List<Book>> getAllBooks() {
		return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
		
	}
	
	@GetMapping("/searchBook/{bookName}")
	public ResponseEntity<Book> searchOneBook(@PathVariable String bookName){
		return new ResponseEntity<>(bookService.searchOneBookName(bookName),HttpStatus.OK);
	}
	
	@GetMapping("/searchAuthor/{author}")
	public ResponseEntity<List<Book>> searchAuthorBooks(@PathVariable String author){
		return new ResponseEntity<>(bookService.searchAuthorBooks(author),HttpStatus.OK);
	}
	

}
