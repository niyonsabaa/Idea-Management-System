package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.flyhub.ideamanagementsystem.DaO.GenderRepository;
import com.flyhub.ideamanagementsystem.Entity.Gender;

@Service
public class GenderService {
	@Autowired
	GenderRepository genderRepo;
	
	public List<Gender> showAllGenders(){
		return genderRepo.findAll();
	}
	
	public Optional<Gender> findGender(Long id){
		return genderRepo.findById(id);
	}
	
	public Gender createGender(@RequestBody Gender gender) {
		return genderRepo.save(gender);
	}
	
	public void deleteGender(Long id) {
        genderRepo.deleteById(id);
    }
}
