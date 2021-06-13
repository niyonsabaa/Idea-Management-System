package com.flyhub.ideamanagementsystem.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/{id}")
	public Category findCategory(@PathVariable("id") Long id){
		return categoryService.findCategory(id);
	}
	
	@PostMapping
	public Category createCategory(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable("id") Long id) {
		categoryService.deleteCategory(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("id") Long id ){
		Category oldCategory = categoryService.findCategory(id);
		if(Objects.isNull(oldCategory)) {
			return ResponseEntity.notFound().build();
		}
		category.setId(id);			
		return ResponseEntity.ok(this.categoryService.createCategory(category));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Category> updateSomeCategoryFields(@RequestBody Category category, @PathVariable("id") Long id){
		Category oldCategory = categoryService.findCategory(id);
		if(Objects.isNull(oldCategory)) {
			return ResponseEntity.notFound().build();
		}
		category.setId(id);			
		return ResponseEntity.ok(this.categoryService.createCategory(category));
	}
}
