package com.flyhub.ideamanagementsystem.controller;

import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.flyhub.ideamanagementsystem.Entity.Idea;
import com.flyhub.ideamanagementsystem.service.IdeaService;

@RestController
@RequestMapping("api/v1/ideas")
public class IdeaRestController {
	@Autowired
	private IdeaService ideaService;

	@GetMapping
	public List<Idea> findAllIdeas() {
		return ideaService.findAllIdeas();
	}

	@GetMapping("/{id}")
	public Idea findIdea(@PathVariable("id") Long id) {
		return ideaService.findIdea(id);
	}

	@PostMapping
	public Idea createIdea(@RequestBody Idea idea, @RequestParam("userId") Long userId,
			@RequestParam("categoryId") Long categoryId, @RequestParam("priorityId") Long priorityId) {
		return ideaService.createIdea(idea, userId, categoryId, priorityId);
	}

	@DeleteMapping("/{id}")
	public void deleteIdea(@PathVariable("id") Long id) {
		ideaService.deleteIdea(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Idea> updateIdea(@PathVariable("id") Long id, @RequestBody Idea idea,
			@RequestParam("userId") Long userId, @RequestParam("categoryId") Long categoryId,
			@RequestParam("priorityId") Long priorityId) {
		Idea oldIdea = ideaService.findIdea(id);
		if(Objects.isNull(oldIdea)) {
			return ResponseEntity.notFound().build();
		}
		idea.setId(id);			
		return ResponseEntity.ok(this.ideaService.createIdea(idea, userId, categoryId, priorityId));
	}
	
	// @PatchMapping("/{id}")

}
