package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.DaO.CategoryRepository;
import com.flyhub.ideamanagementsystem.Entity.Category;
import com.flyhub.ideamanagementsystem.exception.CategoryNotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepo;
	
	public List<Category> findAllCategories(){
		return categoryRepo.findAll();
	}
	
	public Category findCategory(Long id){
		return categoryRepo.findById(id).orElseThrow(()->
		new CategoryNotFoundException("Category with id "+id+" doesn't exist."));
	}
	
	public Category createCategory(Category category) {
		return categoryRepo.save(category);
	}
	
	public void deleteCategory(Long id) {
		if(Objects.nonNull(findCategory(id))) {
        categoryRepo.deleteById(id);
		}
    }
}
