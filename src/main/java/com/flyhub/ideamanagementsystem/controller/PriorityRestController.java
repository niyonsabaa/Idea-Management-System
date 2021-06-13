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
	
	@GetMapping("/{id}")
	public Priority findPriority(@PathVariable("id") Long id){
		return priorityService.findPriority(id);
	}
	
	@PostMapping
	public Priority createPriority(@RequestBody Priority priority) {
		return priorityService.createPriority(priority);
	}
	
	@DeleteMapping("/{id}")
	public void deletePriority(@PathVariable("id") Long id) {
		priorityService.deletePriority(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Priority> updatePriority(@RequestBody Priority priority, @PathVariable("id") Long id ){
		Priority oldPriority = priorityService.findPriority(id);
		if(Objects.isNull(oldPriority)) {
			return ResponseEntity.notFound().build();
		}
		priority.setId(id);			
		return ResponseEntity.ok(this.priorityService.createPriority(priority));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Priority> updateSomePriorityFields(@RequestBody Priority priority, @PathVariable("id") Long id){
		Priority oldPriority = priorityService.findPriority(id);
		if(Objects.isNull(oldPriority)) {
			return ResponseEntity.notFound().build();
		}
		priority.setId(id);			
		return ResponseEntity.ok(this.priorityService.createPriority(priority));
	}
}
