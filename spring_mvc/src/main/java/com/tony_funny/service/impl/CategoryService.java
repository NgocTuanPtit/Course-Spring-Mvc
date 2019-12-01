package com.tony_funny.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tony_funny.entity.CategoryEntity;
import com.tony_funny.repository.CategoryRepository;
import com.tony_funny.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{

	
	@Autowired	
	private CategoryRepository categoryRepository;

	@Override
	public Map<String, String> findAll() {
		Map<String, String> mapCategory = new HashMap<String, String>();
		List<CategoryEntity> categories = categoryRepository.findAll();
		for (CategoryEntity item : categories) {
			mapCategory.put(item.getCode(), item.getName());
		}
		return mapCategory;
	}

}
