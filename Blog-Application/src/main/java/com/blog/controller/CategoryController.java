package com.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.blog.model.Category;
import com.blog.serviceImpl.CategoryServiceImpl;

@RestController
@RequestMapping("api/")
public class CategoryController {

	
	private CategoryServiceImpl categoryService;
	
	@PostMapping("category/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		
        Category createdCategory = categoryService.createCategory(category);
        
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    
    @GetMapping("category")
    public ResponseEntity<List<Category>> getAllCategories() {
    	
        List<Category> categories = categoryService.getAllCategories();
        
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

   
    @GetMapping("category/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
    	
        Category category = categoryService.getCategoryById(categoryId);
        
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    
    @PutMapping("category/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category categoryDetails) {
    	
        Category updatedCategory = categoryService.updateCategory(categoryId, categoryDetails);
        
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    
    @DeleteMapping("category/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
    	
        categoryService.deleteCategory(categoryId);
        
        return new ResponseEntity<String>("Comment deleted successfully", HttpStatus.OK);
    }
	
	
	
}
