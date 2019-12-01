package com.tony_funny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tony_funny.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByCode(String code);
}
