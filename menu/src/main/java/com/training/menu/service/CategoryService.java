package com.training.menu.service;

import com.training.menu.models.Category;
import com.training.menu.models.CreateCategoryRequest;
import com.training.menu.models.MenuException;

import java.util.List;

public interface CategoryService {
    Category createNewCategory(CreateCategoryRequest createCategoryRequest);

    List<Category> listAllCategories();
    
    void delete(String categoryId) throws MenuException;
}
