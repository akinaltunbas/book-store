package com.project.bookstore.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookstore.dto.CategoryCreateRequestDto;
import com.project.bookstore.dto.CategoryUpdateRequestDto;
import com.project.bookstore.dto.constants.ResponseMessages;
import com.project.bookstore.entities.Category;
import com.project.bookstore.service.CategoryService;

@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {
	
	private CategoryService categoryService;

	public AdminCategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	

	@PostMapping("/createCategory")
	public ResponseEntity<String> createOneCategory(@Valid @RequestBody CategoryCreateRequestDto newCategoryRequest ) {
		categoryService.createOneCategory(newCategoryRequest);
		return ResponseEntity.ok(ResponseMessages.CATEGORY_CREATED.getMessage());
	}
	
	@GetMapping("/listCategory")
	public ResponseEntity<List<Category>> getAllCategories() {
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
	}
	
	@GetMapping("/getCategory/{categoryId}")
	public ResponseEntity<Category> getOneCategory(@PathVariable Long categoryId) {
		return new ResponseEntity<>(categoryService.getOneCategoryById(categoryId), HttpStatus.OK);
	}
	
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<String> updateOneCategory(@PathVariable Long categoryId, @RequestBody CategoryUpdateRequestDto updateCatgoryequest){
		categoryService.updateOneCategoryById(categoryId, updateCatgoryequest);
		return ResponseEntity.ok(ResponseMessages.CATEGORY_UPDATED.getMessage());
	}
	
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<String> deleteOneCategory(@PathVariable Long categoryId) {
		categoryService.deleteOneCategoryById(categoryId);
		return ResponseEntity.ok(ResponseMessages.CATEGORY_DELETED.getMessage());
	}

}
