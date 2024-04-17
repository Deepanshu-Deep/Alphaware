package com.blog.service;

import java.util.List;
import com.blog.model.Category;

public interface CategoryService {

    Category createCategory(Category category);
    
    Category getCategoryById(Long categoryId);
    
    Category updateCategory(Long id, Category categoryDetails);
    
    void deleteCategory(Long categoryId);
    
    List<Category> getAllCategories();
   


    
	
}
