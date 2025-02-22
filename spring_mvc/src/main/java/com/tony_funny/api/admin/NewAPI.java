package com.tony_funny.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tony_funny.dto.NewDTO;
import com.tony_funny.entity.NewEntity;
import com.tony_funny.service.INewService;

@RestController(value = "newAPIOfAdmin")
public class NewAPI {

	@Autowired
	private INewService newService;
	
	@PostMapping("/api/new")
	public NewDTO createNew(@RequestBody NewDTO newDTO) {
		return newService.save(newDTO);
	}
	
	@PutMapping("/api/new")
	public NewDTO editNew(@RequestBody NewDTO newDTO) {
		return newService.save(newDTO);
	}
	
	@DeleteMapping("/api/new")
	public void deleteNew(@RequestBody long[] ids) {
		newService.delete(ids);
	}
	
}
