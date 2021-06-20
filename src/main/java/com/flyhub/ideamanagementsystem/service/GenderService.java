package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.DaO.GenderRepository;
import com.flyhub.ideamanagementsystem.Entity.Gender;
import com.flyhub.ideamanagementsystem.exception.GenderNotFoundException;

@Service
public class GenderService {
	@Autowired
	GenderRepository genderRepo;
	
	public List<Gender> showAllGenders(){
		List<Gender> genders = genderRepo.findAll();	
		return genders;
	}
	
	public Gender findGender(Long id){
		return genderRepo.findById(id).orElseThrow(()-> new GenderNotFoundException("Gender with id "+id+" doesn't exist."));
	}
	
	public Gender createGender(Gender gender) {
		return genderRepo.save(gender);
	}
	
	public void deleteGender(Long id) {
		if(Objects.nonNull(findGender(id))) {
			genderRepo.deleteById(id);
		}        
    }
}
