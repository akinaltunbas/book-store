package com.project.bookstore.dto;

import com.project.bookstore.entities.Category;

import lombok.Data;

@Data
public class CategoryUpdateRequestDto {
	
	private String name;
	
	public void mapCategoryUpdateRequestDto(Category category) {
		category.setName(this.getName());
	}

}
