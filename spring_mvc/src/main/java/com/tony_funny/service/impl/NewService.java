package com.tony_funny.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tony_funny.converter.NewConverter;
import com.tony_funny.dto.NewDTO;
import com.tony_funny.entity.CategoryEntity;
import com.tony_funny.entity.NewEntity;
import com.tony_funny.repository.CategoryRepository;
import com.tony_funny.repository.NewRepository;
import com.tony_funny.service.INewService;

@Service
public class NewService implements INewService {
	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private NewConverter newConverter;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> listResult =  newRepository.findAll(pageable).getContent();
		for (NewEntity item : listResult) {
			NewDTO newDTO = newConverter.toDTO(item);
			models.add(newDTO);
		}
		return models;
	}
	
	@Override
	public int getTotalPage() {
		// lấy tổng số bản ghi có trong table "new"
		return (int) newRepository.count();
	}

	@Override
	public NewDTO findByID(Long id) {
		NewEntity entity = newRepository.findOne(id);
		return newConverter.toDTO(entity);
	}

	
	//= Hàm thự hiện việc cập nhật hoặc thêm mới bài viết
	@Override
	@Transactional
	public NewDTO save(NewDTO newDTO) {
		CategoryEntity category = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		NewEntity entity = new NewEntity();
		if(newDTO.getId() != null) {
			//= update bai viet
			entity = newRepository.findOne(newDTO.getId());
			entity.setCategory(category);
			entity = newConverter.toEntity(entity, newDTO);
		}else {
			//= them moi bai viet
			entity = newConverter.toEntity(newDTO);
			entity.setCategory(category);
		}
		return newConverter.toDTO(newRepository.save(entity));
	}

	@Override
	public void delete(long[] id) {
		for (long i : id) {
			newRepository.delete(i);
		}
	}
}