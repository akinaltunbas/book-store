package com.project.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.project.bookstore.dto.BookCreateRequestDto;
import com.project.bookstore.dto.BookUpdateRequestDto;
import com.project.bookstore.entities.Book;
import com.project.bookstore.exception.BookAlreadyExistsException;
import com.project.bookstore.exception.BookNotFoundException;
import com.project.bookstore.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService{
	
	private final BookRepository bookRepository;

	
	
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book cretateOneBook(BookCreateRequestDto newBookRequest) {
		checkIfBookNameExists(newBookRequest.getName());
		Book book = new Book();
		newBookRequest.mapBookCreateRequestDto(book);
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book getOneBookById(Long bookId) {
		return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
	}

	@Override
	public Book updatetOneBookById(Long bookId, BookUpdateRequestDto updateBookRequest) {
		Optional<Book> book = bookRepository.findById(bookId);
		if(book.isPresent()) {
			Book updateBook = book.get();
			updateBookRequest.mapBookUpdateRequestDto(updateBook);
			return bookRepository.save(updateBook);
		}else {
			throw new BookNotFoundException(bookId);
		}
		
	}

	@Override
	public void deleteOneBookById(Long bookId) {
		bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
		bookRepository.deleteById(bookId);
	}

	@Override
	public void checkIfBookNameExists(String name) {
		if(bookRepository.existsByName(name)) {
			throw new BookAlreadyExistsException(name);
		}
		
	}

	@Override
	public Book searchOneBookName(String name) {	
		return bookRepository.findByName(name);
	}

	@Override
	public List<Book> searchAuthorBooks(String Author) {
		return bookRepository.findByAuthor(Author);
	}

	
}
