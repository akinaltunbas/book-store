package com.project.bookstore.dto;

import javax.validation.constraints.NotBlank;

import com.project.bookstore.entities.Category;
import lombok.Data;

@Data
public class CategoryCreateRequestDto {
	
	@NotBlank(message="Name cannot be empty")
	private String name;

	public void mapCategoryCreateRequestDto(Category category) {
		category.setName(this.getName());
		
	}

}
