package com.training.menu.service.impl;

import com.training.menu.dao.CategoryDAO;
import com.training.menu.dto.CategoryEntity;
import com.training.menu.models.Category;
import com.training.menu.models.CreateCategoryRequest;
import com.training.menu.models.MenuException;
import com.training.menu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
		CategoryEntity categoryEntity = CategoryEntity.builder().categoryId(UUID.randomUUID().toString())
				.categoryName(createCategoryRequest.getCategoryName())
				.categoryImageURL(createCategoryRequest.getCategoryImageURL()).build();

		categoryDAO.save(categoryEntity);

		return Category.builder().categoryName(categoryEntity.getCategoryName())
				.categoryImageURL(categoryEntity.getCategoryImageURL()).categoryId(categoryEntity.getCategoryId())
				.build();
	}

	@Override
	public List<Category> listAllCategories(int pageSize, int pageNumber, String direction, String properties)
			throws MenuException {
		if (pageSize <= 0) {
			throw new MenuException("Page Size cannot be less than or equal to zero", HttpStatus.BAD_REQUEST);
		}
		if (pageNumber < 0) {
			throw new MenuException("Page Number cannot be less than zero", HttpStatus.BAD_REQUEST);
		}
		if (!(direction.equalsIgnoreCase("Ascending") || direction.equalsIgnoreCase("ASC")
				|| direction.equalsIgnoreCase("DESC") || direction.equalsIgnoreCase("descending")|| direction.isEmpty())) {
			throw new MenuException("Direction can be either Ascending or Descending", HttpStatus.BAD_REQUEST);
		}
		if (!(properties.equals("categoryName") || properties.equals("categoryId")
				|| properties.equals("categoryImageURL") || properties.isEmpty())) {
			throw new MenuException("Could not find property.", HttpStatus.BAD_REQUEST);
		}

		Direction dir = Direction.ASC;
		if (direction.equalsIgnoreCase("Ascending") || direction.equalsIgnoreCase("ASC") || direction.isEmpty()) {
			dir.isAscending();
		} else
			dir.isDescending();

		if(properties.isEmpty())
		{
			properties = "id";
		}
		Page<CategoryEntity> categoryDTOS = categoryDAO
				.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(dir, properties)));
		List<Category> categories = new ArrayList<>();
		for (CategoryEntity categoryDTO : categoryDTOS.getContent()) {
			categories.add(Category.builder().categoryName(categoryDTO.getCategoryName())
					.categoryImageURL(categoryDTO.getCategoryImageURL()).categoryId(categoryDTO.getCategoryId())
					.build());
		}
		return categories;
	}

	@Override
	public void delete(String categoryId) throws MenuException {

		CategoryEntity categoryEntity = categoryDAO.findByCategoryId(categoryId);
		if (categoryEntity != null) {
			categoryDAO.delete(categoryDAO.findByCategoryId(categoryId));
			return;
		}
		throw new MenuException("category Id is not found", HttpStatus.BAD_REQUEST);

//		categoryDAO.deleteByCategoryId(categoryId);

	}
}
