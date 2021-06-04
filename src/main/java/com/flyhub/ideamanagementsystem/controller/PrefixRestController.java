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

import com.flyhub.ideamanagementsystem.service.PrefixService;
import com.flyhub.ideamanagementsystem.Entity.Prefix;
@RestController
@RequestMapping("api/v1/prefix")
public class PrefixRestController {
	@Autowired 
	PrefixService prefixService;
	
	@GetMapping
	public List<Prefix> findAllPrefix(){
		return prefixService.findAllPrefix();
	}
	
	@GetMapping("/{id}")
	
	public Optional<Prefix> findPrefix(@PathVariable("id") Long id) {
		return prefixService.findPrefix(id);
	}
	
	@PostMapping
	public Prefix createPrefix(@RequestBody Prefix prefix) {
		return prefixService.createPrefix(prefix);
	}
	
	@DeleteMapping("/{id}")
	public void deletePrefix(@PathVariable("id") Long id) {
		prefixService.deletePrefix(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Prefix> updatePrefix(@RequestBody Prefix prefix, @PathVariable("id") Long id){
		Optional<Prefix> oldPrefix = prefixService.findPrefix(id);
		if(!oldPrefix.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		prefix.setId(id);
		return ResponseEntity.ok(this.prefixService.createPrefix(prefix));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Prefix> updateSomePrefixFields(@RequestBody Prefix prefix, @PathVariable("id") Long id){
		Optional<Prefix> oldPrefix = prefixService.findPrefix(id);
		if(!oldPrefix.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		prefix.setId(id);
		return ResponseEntity.ok(this.prefixService.createPrefix(prefix));
	}
}
