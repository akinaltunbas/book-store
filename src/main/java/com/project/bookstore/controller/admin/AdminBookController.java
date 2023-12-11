package com.project.bookstore.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookstore.dto.BookCreateRequestDto;
import com.project.bookstore.dto.BookUpdateRequestDto;
import com.project.bookstore.dto.constants.ResponseMessages;
import com.project.bookstore.entities.Book;
import com.project.bookstore.service.BookService;

@RestController
@RequestMapping("/admin/book")
public class AdminBookController {
	
	private BookService bookService;

	public AdminBookController(BookService bookService) {

		this.bookService = bookService;
	}
	
	@PostMapping("/createBook")
	public ResponseEntity<String> createOneBook(@Valid @RequestBody BookCreateRequestDto newBokkRequest) {
		bookService.cretateOneBook(newBokkRequest);
		return ResponseEntity.ok(ResponseMessages.BOOK_CREATED.getMessage());
		
	}
	
	@GetMapping("/listBook")
	public ResponseEntity<List<Book>> getAllBooks() {
		return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
		
	}
	
	@GetMapping("/getBook/{bookId}")
	public ResponseEntity<Book> getOneBook(@PathVariable Long bookId){
		return new ResponseEntity<>(bookService.getOneBookById(bookId), HttpStatus.OK);
	}
	
	@PutMapping("/updateBook/{bookId}")
	public ResponseEntity<String> updateOneBook(@PathVariable Long bookId, @RequestBody BookUpdateRequestDto updateBookReqest) {
		bookService.updatetOneBookById(bookId, updateBookReqest);
		return ResponseEntity.ok(ResponseMessages.BOOK_UPDATED.getMessage());
	}
	
	@DeleteMapping("/deleteBook/{bookId}")
	public ResponseEntity<String> deleteOneBook(@PathVariable  Long bookId) {
		bookService.deleteOneBookById(bookId);
		return ResponseEntity.ok(ResponseMessages.BOOK_DELETED.getMessage());
	}

}
