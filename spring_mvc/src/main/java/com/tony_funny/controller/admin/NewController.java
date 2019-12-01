package com.tony_funny.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tony_funny.Ultils.MessageUltils;
import com.tony_funny.dto.NewDTO;
import com.tony_funny.service.ICategoryService;
import com.tony_funny.service.INewService;

@Controller(value = "newControllerOfAdmin")
public class NewController {
	
	@Autowired
	private INewService newService;
	
	@Autowired
	private ICategoryService categoryService;

	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam int page, @RequestParam int limit) {
		NewDTO model = new NewDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/new/list");
		Pageable pageable = new PageRequest(page-1, limit);
		model.setListResult(newService.findAll(pageable));
		model.setTotalPage((int)Math.ceil((double)newService.getTotalPage()/model.getLimit()));
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/new/edit");
		NewDTO model = new NewDTO();
		if(id != null) {
			//= Chỉnh sửa bài viết
			model = newService.findByID(id);
		}
		if(request.getParameter("message") != null) {
			Map<String, String> messMap = MessageUltils.getMessage(request.getParameter("message"));
			mav.addObject("message", messMap.get("message"));
			mav.addObject("alert", messMap.get("alert"));
		}
		//= Thêm mới bài viết
		mav.addObject("model", model);
		mav.addObject("categories", categoryService.findAll());
		return mav;
	}
}