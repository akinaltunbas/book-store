package com.project.bookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.bookstore.dto.BookCreateRequestDto;
import com.project.bookstore.dto.BookUpdateRequestDto;
import com.project.bookstore.entities.Book;
import com.project.bookstore.exception.BookAlreadyExistsException;
import com.project.bookstore.exception.BookNotFoundException;
import com.project.bookstore.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
	

	private BookRepository bookRepository;
	
	private BookServiceImpl bookService;
	
	@Mock
	private CategoryServiceImpl categoryService;
	
	private Book book;
	
	@BeforeEach
	public void setup() {
		
		bookRepository = mock(BookRepository.class);
		book = mock(Book.class);
		bookService = new BookServiceImpl(bookRepository);
		
	}
	
	@Test
	public void testGetAllBooks_itShouldReturnBookList() {
		
		Book book = new Book();
		
		List<Book> bookList = Collections.singletonList(book);
		
		when(bookRepository.findAll()).thenReturn(bookList);
		
		List<Book> result = bookService.getAllBooks();
		
		assertEquals(bookList,result);
	}
	
	@Test
	public void testGetBookById_whenBookIdExist_itShouldReturnBook() {
		
		Long bookId = 1L;
		
		when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
		
		Book result = bookService.getOneBookById(bookId);
		
		assertEquals(book,result);
	}
	
	@Test
	public void testGetBookById_whenBookDoesNotExist_itShouldThrowBookNotFondException() {
		
		Long bookId = 1L;
		
		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
		
		assertThrows(BookNotFoundException.class, () -> bookService.getOneBookById(bookId));
	}
	
	@Test
	public void testDeleteBookById_whenBookIdExist_itShouldDeleteBook() {
		
		Book book = new Book(1L,"Sefiller","Victor Hugo",200.00,1000,categoryService.getOneCategoryById(1L));
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		
		bookService.deleteOneBookById(1L);
		
	}
	
	@Test
	public void testDeleteBookById_whenBookDoesNotExist_itShouldThrowBookNotFoundException() {
		
		Long bookId = 1L;
		
		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
		
		assertThrows(BookNotFoundException.class, () -> bookService.deleteOneBookById(bookId));
	}
	
	@Test
	public void testCreateBook_itShouldReturnCreatedBookRequestDto() {
		
		BookCreateRequestDto bookRequestDto = new BookCreateRequestDto();
		bookRequestDto.setName("Sefiller");
		bookRequestDto.setAuthor("Victor Hugo");
		bookRequestDto.setPrice(200.00);
		bookRequestDto.setStock(1000);
		bookRequestDto.setCategory(categoryService.getOneCategoryById(1L));
		
		Book savedBook = new Book(1L,"Sefiller","Victor Hugo",200.00,1000,categoryService.getOneCategoryById(1L));
		when(bookRepository.save(any())).thenReturn(savedBook);
		
		Book saveBook = bookService.cretateOneBook(bookRequestDto);
		
		assertEquals(saveBook.getId(),1L);
	}
	
	@Test
	public void testSearhBookByName_itShouldReturnBook() {
		
		String name = "Sefiller";
		
		when(bookRepository.findByName(name)).thenReturn(book);
		
		Book result = bookService.searchOneBookName(name);
		
		assertEquals(book,result);
	}
	
	@Test
	public void testSearchAuthorBooks_itShouldReturnBooks() {
		
		String author = "Victor Hugo";
		
		List<Book> books = null;
		
		when(bookRepository.findByAuthor(author)).thenReturn(books);
		
		Book result = (Book) bookService.searchAuthorBooks(author);
		
		assertEquals(books,result);
	}
	
	@Test
	public void  checkIfBookNameExists_whenBookNameExist() {
		
		Book book = new Book(1L,"Sefiller","Victor Hugo",200.00,1000,categoryService.getOneCategoryById(1L));
		
		when(bookRepository.existsByName("Sefiller"));
		
		assertThrows(BookAlreadyExistsException.class, () -> bookService.checkIfBookNameExists("Sefiller"));
		
		
	}
	
	@Test
	public void testUpdateBook_itShouldReturnUpdateUserRequestDto() {
		
		Book book = new Book(1L,"Sefiller","Victor Hugo",200.00,1000,categoryService.getOneCategoryById(1L));
		BookUpdateRequestDto updateBookRequest = new BookUpdateRequestDto();
		Long bookId = 1L;
		
		given(bookRepository.findById(1L)).willReturn(Optional.of(book));
		given(bookRepository.save(book)).willReturn(book);
		
		updateBookRequest.setName("Sefiller");
		updateBookRequest.setAuthor("Victor Hugo");
		updateBookRequest.setPrice(300.00);
		updateBookRequest.setStock(1000);
		updateBookRequest.setCategory(categoryService.getOneCategoryById(1L));
		
		Book updateBook = bookService.updatetOneBookById(bookId, updateBookRequest);
		
		assertThat(updateBook.getPrice()).isEqualTo(300.00);
	}
	
	@Test
	public void updateBook_whenBookDoesNotIdExist_itShouldThrowBookNotFoundException() {
		
		Long bookId = 1L;
		BookUpdateRequestDto updateBookRequest = new BookUpdateRequestDto();
		
		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
		
		assertThrows(BookNotFoundException.class, () -> bookService.updatetOneBookById(bookId, updateBookRequest));
	}
	
}
