package com.project.bookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.bookstore.dto.CategoryCreateRequestDto;
import com.project.bookstore.dto.CategoryUpdateRequestDto;
import com.project.bookstore.entities.Category;
import com.project.bookstore.exception.CategoryNotFoundException;
import com.project.bookstore.repository.CategoryRepository;



@ExtendWith(MockitoExtension.class)
public class CategorServiceImplTest {
	

	private CategoryRepository categoryRepository;
	
	private CategoryServiceImpl categoryService;
	
	private Category category;
	
	@BeforeEach
	public void setup() {
		
		categoryRepository = mock(CategoryRepository.class);
		category = mock(Category.class);
		categoryService = new CategoryServiceImpl(categoryRepository);
	}
	
	
	@Test
	public void testGetAllCategories_itShouldReturnCategoryList() {
		
		Category user1 = new Category(1L,"Korku");
		
		List<Category> categoryList = Collections.singletonList(user1);
		
		when(categoryRepository.findAll()).thenReturn(categoryList);
		
		List<Category> result = categoryService.getAllCategories();
		
		assertEquals(categoryList,result);
	}
	
	@Test
	public void testGetCategoryById_whenCategoryIdExist_itShouldReturnCategory() {
		
		Long categoryId = 1L;
		
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
		
		Category result = categoryService.getOneCategoryById(categoryId);
		
		assertEquals(category,result);
	}
	
	@Test
	public void testGetCategoryById_whenUserDoesNotIdExist_itShouldThrowCategoryNotFondExceptionr() {
		
		Long categoryId = 1L;

		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
		
		assertThrows(CategoryNotFoundException.class, () -> categoryService.getOneCategoryById(categoryId) );
	}
	
	
	@Test
	public void testDeleteCategoryById_whenCategoryIdExist_itShoulDeleteCategory() {
		
		Category category = new Category(1L,"Korku");
		
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
		
		categoryService.deleteOneCategoryById(1L);
	}
	
	@Test
	public void testDeleteCategoryById_whenUserDoesNotIdExist_itShouldThrowCategoryNotFondExceptionr() {
		
		Long categoryId = 1L;

		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
		
		assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteOneCategoryById(categoryId) );
	}
	
	
	@Test
	public void testUpdateCategory_whenCategoryDoesNotIdExist_itShouldThrowCategoryNotFondException() {
		
		Long categoryId = 1L;
		CategoryUpdateRequestDto category = new CategoryUpdateRequestDto();

		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
		
		assertThrows(CategoryNotFoundException.class, () -> categoryService.updateOneCategoryById(categoryId,category) );
	}
	
	@Test
	public void testUpdateCategory_itShouldReturnUpdatedCategoryRequestDto() {
		
		Category category = new Category(1L,"Korku");
		CategoryUpdateRequestDto updateCategoryRequest = new CategoryUpdateRequestDto();
		long categoryId= 1L;
		
		given(categoryRepository.findById(1L)).willReturn(Optional.of(category));
		given (categoryRepository.save(category)).willReturn(category);
	
		updateCategoryRequest.setName("Macera");
	
		Category updateCategory = categoryService.updateOneCategoryById(categoryId, updateCategoryRequest);
	
		assertThat(updateCategory.getName()).isEqualTo("Macera");
	
	}
	
	@Test
	public void testCreateCategory_itShouldReturnCreadetCategoryRequestDto() {
		
		
		CategoryCreateRequestDto categoryRequestDto = new CategoryCreateRequestDto();
		categoryRequestDto.setName("Korku");
		
		Category savedCategory = new Category(1L,"Korku");
		when(categoryRepository.save(any())).thenReturn(savedCategory);
		
		Category saveCategory = categoryService.createOneCategory(categoryRequestDto);
		
		assertEquals(saveCategory.getId(),1L);
	}

}
