package com.flyhub.ideamanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.Entity.Category;
import com.flyhub.ideamanagementsystem.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/category")
public class CategoryRestController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<Category> findAllCategories(){
		return categoryService.findAllCategories();
	}

}
