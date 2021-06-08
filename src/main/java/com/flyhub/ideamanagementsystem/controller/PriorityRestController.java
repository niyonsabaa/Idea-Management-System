package com.flyhub.ideamanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.Entity.Priority;
import com.flyhub.ideamanagementsystem.service.PriorityService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/priority")
public class PriorityRestController {
	@Autowired
	private PriorityService priorityService;
	@GetMapping
	public List<Priority> findAllPriorities(){
		return priorityService.findAllPriorities();
	}
}
