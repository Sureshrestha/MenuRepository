package com.training.menu.controller;

import com.training.menu.models.Category;
import com.training.menu.models.CreateCategoryRequest;
import com.training.menu.models.MenuException;
import com.training.menu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class MenuController {

	private CategoryService categoryService;

	public MenuController(@Autowired CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping("/category")
	public ResponseEntity<?> createNewCategory(@RequestHeader HttpHeaders headers,
			@RequestBody CreateCategoryRequest createCategoryRequest) {

		Category category = categoryService.createNewCategory(createCategoryRequest);
		return ResponseEntity.ok(category);

	}
	/*
	 * Page size = 20 Total categories = 450 Page 1 -> 0-19th index ->(n-1)*20
	 * -(n*20-1) Page 2 -> 20-39th index
	 * 
	 */

	@GetMapping("/category")
	public ResponseEntity<?> listAllCategories(@RequestHeader HttpHeaders headers, @RequestParam int pageSize,
			@RequestParam int pageNumber, @RequestParam String direction, @RequestParam String properties) {
		try {
			return ResponseEntity.ok(categoryService.listAllCategories(pageSize, pageNumber, direction, properties));
		} catch (MenuException e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable String categoryId) {
		try {
			categoryService.delete(categoryId);
			return ResponseEntity.ok("category id is deleted successfully!");
		} catch (MenuException m) {
			return ResponseEntity.ok(m.getMessage());

		}

	}

}
