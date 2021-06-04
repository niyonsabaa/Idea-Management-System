package com.flyhub.ideamanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.service.PostfixService;
import com.flyhub.ideamanagementsystem.Entity.Postfix;
@RestController
@RequestMapping("api/v1/postfix")
public class PostfixRestController {
	@Autowired
	PostfixService postfixService;
	
	@GetMapping
	public List<Postfix> findAllPostfix(){
		return postfixService.findAllPostfix();
	}
	
	@GetMapping("/{id}")
	public Optional<Postfix> findPostfix(@PathVariable("id") Long id) {
		return postfixService.findPostfix(id);
	}
	
	@PostMapping
	public Postfix createPostfix(@RequestBody Postfix postfix) {
		return postfixService.createPostfix(postfix);
	}
	
	@DeleteMapping("/{id}")
	public void deletePostfix(@PathVariable("id") Long id) {
		postfixService.deletePostfix(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Postfix> updatePostfix(@RequestBody Postfix postfix, @PathVariable("id") Long id){
		Optional<Postfix> oldPostfix = postfixService.findPostfix(id);
		if(!oldPostfix.isPresent()) {
			ResponseEntity.notFound().build();
		}
		postfix.setId(id);
		return ResponseEntity.ok(this.postfixService.createPostfix(postfix));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Postfix> updateSomePostfixFields(@RequestBody Postfix postfix, @PathVariable("id") Long id){
		Optional<Postfix> oldPostfix = postfixService.findPostfix(id);
		if(!oldPostfix.isPresent()) {
			ResponseEntity.notFound().build();
		}
		postfix.setId(id);
		return ResponseEntity.ok(this.postfixService.createPostfix(postfix));
	}
}
