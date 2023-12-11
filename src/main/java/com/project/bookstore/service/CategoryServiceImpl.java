package com.project.bookstore.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.bookstore.dto.CategoryCreateRequestDto;
import com.project.bookstore.dto.CategoryUpdateRequestDto;
import com.project.bookstore.entities.Category;
import com.project.bookstore.exception.CategoryAlreadyExistsException;
import com.project.bookstore.exception.CategoryNotFoundException;
import com.project.bookstore.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private final CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Category createOneCategory(CategoryCreateRequestDto newCategoryRequest) {
		checkIfCategoryNameExists(newCategoryRequest.getName());
		Category category = new Category();
		newCategoryRequest.mapCategoryCreateRequestDto(category);
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getOneCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
	}

	@Override
	public Category updateOneCategoryById(Long categoryId, CategoryUpdateRequestDto updateCategoryRequest) {
		Optional<Category> category = categoryRepository.findById(categoryId); 
		if(category.isPresent()) {
			Category updateCategory = category.get();
			updateCategoryRequest.mapCategoryUpdateRequestDto(updateCategory);
			return categoryRepository.save(updateCategory);
		}else {
			throw new CategoryNotFoundException(categoryId);
		}
	}

	@Override
	public void deleteOneCategoryById(Long categoryId) {
		categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
		categoryRepository.deleteById(categoryId);
		
	}

	@Override
	public void checkIfCategoryNameExists(String name) {
		if(categoryRepository.existsByName(name)) {
			throw new CategoryAlreadyExistsException(name);
		}
		
	}


}
