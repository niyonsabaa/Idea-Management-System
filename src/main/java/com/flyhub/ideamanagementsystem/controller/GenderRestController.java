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

import com.flyhub.ideamanagementsystem.Entity.Gender;
import com.flyhub.ideamanagementsystem.service.GenderService;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/gender")
public class GenderRestController {
	@Autowired
	GenderService genderService;	
	
	
	@GetMapping
	public List<Gender> showAllGenders() {
		return genderService.showAllGenders();
	}
	
	@GetMapping("/{id}")
	public Gender findGender(@PathVariable("id") Long id){
		return genderService.findGender(id);
	}
	
	@PostMapping
	public Gender createGender(@RequestBody Gender gender) {
		return genderService.createGender(gender);
	}
	
	@DeleteMapping("/{id}")
	public void deleteGender(@PathVariable("id") Long id) {
		genderService.deleteGender(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Gender> updateGender(@RequestBody Gender gender, @PathVariable("id") Long id ){
		Gender oldGender = genderService.findGender(id);
		if(Objects.isNull(oldGender)) {
			return ResponseEntity.notFound().build();
		}
		gender.setId(id);			
		return ResponseEntity.ok(this.genderService.createGender(gender));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Gender> updateSomeGenderFields(@RequestBody Gender gender, @PathVariable("id") Long id){
		Gender oldGender = genderService.findGender(id);
		if(Objects.isNull(oldGender)) {
			return ResponseEntity.notFound().build();
		}
		gender.setId(id);			
		return ResponseEntity.ok(this.genderService.createGender(gender));
	}
	
}
