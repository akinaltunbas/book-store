package com.project.bookstore.service;

import java.util.List;


import com.project.bookstore.dto.CategoryCreateRequestDto;
import com.project.bookstore.dto.CategoryUpdateRequestDto;
import com.project.bookstore.entities.Category;

public interface CategoryService {
	
	public Category createOneCategory(CategoryCreateRequestDto newCategoryRequest);
	
	public List<Category> getAllCategories();
	
	public Category getOneCategoryById(Long categryId);
	
	public Category updateOneCategoryById(Long categoryId, CategoryUpdateRequestDto updateCategoryRequest);
	
	public void deleteOneCategoryById(Long categoryId);
	
	public void checkIfCategoryNameExists(String name);
	

}
