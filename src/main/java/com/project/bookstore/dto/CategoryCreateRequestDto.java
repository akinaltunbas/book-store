package com.project.bookstore.dto;


import com.project.bookstore.entities.Category;
import lombok.Data;

@Data
public class CategoryCreateRequestDto {
	
	
	private String name;

	public void mapCategoryCreateRequestDto(Category category) {
		category.setName(this.getName());
		
	}

}
