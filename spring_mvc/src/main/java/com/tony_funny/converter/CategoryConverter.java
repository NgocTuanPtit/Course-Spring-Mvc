package com.tony_funny.converter;

import org.springframework.stereotype.Component;

import com.tony_funny.dto.CategoryDTO;
import com.tony_funny.dto.NewDTO;
import com.tony_funny.entity.CategoryEntity;

@Component
public class CategoryConverter {

	public CategoryDTO toDTO(CategoryEntity categoryEntity) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCode(categoryEntity.getCode());
		categoryDTO.setName(categoryEntity.getName());
		return categoryDTO;
		
	}
	
	public CategoryEntity toEntity(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setCode(categoryDTO.getCode());
		categoryEntity.setName(categoryDTO.getName());
		return categoryEntity;
	}
}
