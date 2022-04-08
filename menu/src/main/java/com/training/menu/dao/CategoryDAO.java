package com.training.menu.dao;

import com.training.menu.dto.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByCategoryId(String categoryId);
    void deleteByCategoryId(String id);
}
