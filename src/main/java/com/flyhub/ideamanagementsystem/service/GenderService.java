package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.DaO.GenderRepository;
import com.flyhub.ideamanagementsystem.Entity.Gender;

@Service
public class GenderService {
	@Autowired
	GenderRepository genderRepo;
	
	public List<Gender> showAllGenders(){
		return genderRepo.findAll();
	}
	
	public Gender findGender(Long id){
		return genderRepo.findById(id).get();
	}
	
	public Gender createGender(Gender gender) {
		return genderRepo.save(gender);
	}
	
	public void deleteGender(Long id) {
        genderRepo.deleteById(id);
    }
}
