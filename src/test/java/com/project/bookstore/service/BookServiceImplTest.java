package com.project.bookstore.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.bookstore.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
	
	@Mock
	BookRepository bookRepository;
	
	@InjectMocks
	BookServiceImpl bookService;

}
