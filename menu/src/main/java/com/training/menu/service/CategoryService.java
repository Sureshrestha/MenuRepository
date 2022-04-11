package com.training.menu.service;

import com.training.menu.models.Category;
import com.training.menu.models.CreateCategoryRequest;
import com.training.menu.models.MenuException;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

public interface CategoryService {
    Category createNewCategory(CreateCategoryRequest createCategoryRequest);

    List<Category> listAllCategories(int pageSize, int pageNumber, String direction, String properties);
    
    void delete(String categoryId) throws MenuException;
}
