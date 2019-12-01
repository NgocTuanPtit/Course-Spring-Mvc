package com.tony_funny.converter;

import org.springframework.stereotype.Component;

import com.tony_funny.dto.NewDTO;
import com.tony_funny.entity.NewEntity;

@Component
public class NewConverter {

	public NewDTO toDTO(NewEntity entity) {
		NewDTO newDTO = new NewDTO();
		newDTO.setId(entity.getId());
		newDTO.setTitle(entity.getTitle());
		newDTO.setContent(entity.getContent());
		newDTO.setShortDescription(entity.getShortDescription());
		newDTO.setCategoryCode(entity.getCategory().getCode());
		return newDTO;
	}
	
	public NewEntity toEntity(NewDTO dto) {
		NewEntity entity = new NewEntity();
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	}
	
	public NewEntity toEntity(NewEntity entity, NewDTO dto) {
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	}
}
