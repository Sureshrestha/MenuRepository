package com.training.menu.service.impl;

import com.training.menu.dao.CategoryDAO;
import com.training.menu.dto.CategoryEntity;
import com.training.menu.models.Category;
import com.training.menu.models.CreateCategoryRequest;
import com.training.menu.models.MenuException;
import com.training.menu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryServiceImpl(@Autowired CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Category createNewCategory(CreateCategoryRequest createCategoryRequest) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .categoryName(createCategoryRequest.getCategoryName())
                .categoryImageURL(createCategoryRequest.getCategoryImageURL())
                .build();

        categoryDAO.save(categoryEntity);

        return Category.builder()
                .categoryName(categoryEntity.getCategoryName())
                .categoryImageURL(categoryEntity.getCategoryImageURL())
                .categoryId(categoryEntity.getCategoryId())
                .build();
    }

    @Override
    public List<Category> listAllCategories() {
        List<CategoryEntity> categoryDTOS = categoryDAO.findAll();
        List<Category> categories = new ArrayList<>();
        for (CategoryEntity categoryDTO : categoryDTOS) {
            categories.add(Category.builder()
                    .categoryName(categoryDTO.getCategoryName())
                    .categoryImageURL(categoryDTO.getCategoryImageURL())
                    .categoryId(categoryDTO.getCategoryId())
                    .build());
        }
        return categories;
    }

	@Override
	public void delete(String categoryId) throws MenuException {
		
		CategoryEntity categoryEntity= categoryDAO.findByCategoryId(categoryId);
		if(categoryEntity != null) {
			categoryDAO.delete(categoryDAO.findByCategoryId(categoryId));
			return;
		} throw new MenuException ("category Id is not found", HttpStatus.BAD_REQUEST);
		
//		categoryDAO.deleteByCategoryId(categoryId);
		
	}
}
