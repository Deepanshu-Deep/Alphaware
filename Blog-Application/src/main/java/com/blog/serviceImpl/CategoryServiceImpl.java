package com.blog.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.CategoryException;
import com.blog.model.Category;
import com.blog.repository.CategoryRepository;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long categoryId) {

        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("Category not found with id: " + categoryId));
    }

    @Override
    public Category updateCategory(Long categoryId, Category categoryDetails) {
    	
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if(categoryOptional.isPresent()) {
        	
            Category existingCategory = categoryOptional.get();
            
            existingCategory.setName(categoryDetails.getName());
            
            return categoryRepository.save(existingCategory);
            
        } else {
        	
            throw new CategoryException("Category not found with id: " + categoryId);
        }
        
    }

    @Override
    public void deleteCategory(Long categoryId) {
    	
    	Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("Category not found with id: " + categoryId));
      
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getAllCategories() {
    	
        return categoryRepository.findAll();
    }


	
}
