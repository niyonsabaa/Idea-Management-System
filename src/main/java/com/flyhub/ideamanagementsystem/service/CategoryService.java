package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.flyhub.ideamanagementsystem.DaO.CategoryRepository;
import com.flyhub.ideamanagementsystem.Entity.Category;
import com.flyhub.ideamanagementsystem.Entity.Gender;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepo;
	
	public List<Category> findAllCategories(){
		return categoryRepo.findAll();
	}
	
	public Category findCategory(Long id){
		return categoryRepo.findById(id).get();
	}
	
	public Category createCategory(Category category) {
		return categoryRepo.save(category);
	}
	
	public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
