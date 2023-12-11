package com.project.bookstore.dto;

import javax.validation.constraints.NotBlank;

import com.project.bookstore.entities.Book;
import com.project.bookstore.entities.Category;

import lombok.Data;

@Data
public class BookCreateRequestDto {
	
	@NotBlank(message="Name canot be empty")
	private String name;
	private String author;
	private double price;
	private int stock;
	
//	@NotBlank(message="Category cannot be empty")
	private Category category;
	
	public void mapBookCreateRequestDto(Book book) {
		book.setName(this.getName());
		book.setAuthor(this.getAuthor());
		book.setPrice(this.getPrice());
		book.setStock(this.getStock());
		book.setCategory(this.getCategory());;
	}

}
