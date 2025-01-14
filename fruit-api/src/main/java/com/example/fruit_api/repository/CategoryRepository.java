package com.example.fruit_api.repository;

import com.example.fruit_api.dto.CategoryDto;
import com.example.fruit_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
