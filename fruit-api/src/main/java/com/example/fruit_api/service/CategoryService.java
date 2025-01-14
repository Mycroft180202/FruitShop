package com.example.fruit_api.service;

import com.example.fruit_api.dto.CategoryDto;
import com.example.fruit_api.common.api.exception.ResourceNotFoundException;
import com.example.fruit_api.models.Category;
import com.example.fruit_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CategoryDto findCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return convertToDto(category);
    }

    public CategoryDto createCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return convertToDto(savedCategory);
    }

    public CategoryDto updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        category.setCategoryName(categoryDetails.getCategoryName());
        // Cập nhật các trường khác nếu có

        Category updatedCategory = categoryRepository.save(category);
        return convertToDto(updatedCategory);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        categoryRepository.delete(category);
    }

    private CategoryDto convertToDto(Category category) {
        return new CategoryDto(category.getCategoryId(), category.getCategoryName());
    }
}