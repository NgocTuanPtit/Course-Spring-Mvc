package com.tony_funny.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tony_funny.dto.NewDTO;

public interface INewService {
	List<NewDTO> findAll(Pageable pageable);
	int getTotalPage();
	NewDTO findByID(Long id);
	NewDTO save(NewDTO newDTO);
	void delete(long[] id);
}