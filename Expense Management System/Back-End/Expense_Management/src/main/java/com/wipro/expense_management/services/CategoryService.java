package com.wipro.expense_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.expense_management.entity.Category;
import com.wipro.expense_management.exception.ResourceNotFoundException;
import com.wipro.expense_management.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
       return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
    	 Optional<Category> category1=categoryRepository.findById(id);
    	 if(category1.isPresent()) {
    		 category.setCategoryId(category1.get().getCategoryId());
    		 categoryRepository.save(category);
    		 return category;
    	 }else {
    		 throw new ResourceNotFoundException("Category not found..!");
    	 }
        
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
