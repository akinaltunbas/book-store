package com.project.bookstore.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookstore.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	boolean existsByName(String name);
	
	Book findByName(String name);
	
	List<Book> findByAuthor(String Author);
	
	List<Book> getByCategory(Long categoryId);
}
