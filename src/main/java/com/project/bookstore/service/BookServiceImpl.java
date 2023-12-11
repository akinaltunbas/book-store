package com.project.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.bookstore.dto.BookCreateRequestDto;
import com.project.bookstore.dto.BookUpdateRequestDto;
import com.project.bookstore.entities.Book;
import com.project.bookstore.entities.Category;
import com.project.bookstore.exception.BookAlreadyExistsException;
import com.project.bookstore.exception.BookNotFoundException;
import com.project.bookstore.exception.CategoryNotFoundException;
import com.project.bookstore.repository.BookRepository;
import com.project.bookstore.repository.CategoryRepository;

@Service
public class BookServiceImpl implements BookService{
	
	private final BookRepository bookRepository;
	private final CategoryService categoryService;
	
	
	public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService) {
		this.bookRepository = bookRepository;
		this.categoryService = categoryService;
	}

	@Override
	public Book cretateOneBook(BookCreateRequestDto newBookRequest) {
	//	Category category = categoryService.getOneCategoryById(newBookRequest.getCategoryId());
	//	if(category == null)
		// throw new CategoryNotFoundException(newBookRequest.getCategoryId());
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
		return bookRepository.getByAuthor(Author);
	}

	
}
