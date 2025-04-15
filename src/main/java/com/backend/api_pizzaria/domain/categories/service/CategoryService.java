package com.backend.api_pizzaria.domain.categories.service;

import com.backend.api_pizzaria.domain.categories.Category;
import com.backend.api_pizzaria.domain.categories.ICategoryRepository;
import com.backend.api_pizzaria.domain.categories.dtos.CategoryRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    ICategoryRepository categoryRepository;

    public Category createCategory(CategoryRequestDTO request){
        Category category = new Category();
        category.setName(request.name());

        return categoryRepository.save(category);
    }

    public List<Category> listAll() {
        return categoryRepository.findAll();
    }
}
