package com.flyhub.ideamanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.DaO.PriorityRepository;
import com.flyhub.ideamanagementsystem.Entity.Priority;

@Service
public class PriorityService {
	@Autowired
	private PriorityRepository priorityRepo;
	
	public List<Priority> findAllPriorities(){
		return priorityRepo.findAll();
	}
	
	public Priority findPriority(Long id){
		return priorityRepo.findById(id).get();
	}
}
