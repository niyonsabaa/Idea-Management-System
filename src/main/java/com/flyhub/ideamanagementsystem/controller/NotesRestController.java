package com.flyhub.ideamanagementsystem.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.service.NotesService;
import com.flyhub.ideamanagementsystem.Entity.Notes;
@RestController
@RequestMapping("api/v1/notes")
public class NotesRestController {
	@Autowired
	private NotesService notesService;
	
	@GetMapping
	public List<Notes> findAllNotes(){
		return notesService.findAllNotes();		
	}
	
	@GetMapping("/{id}")
	public Notes findNotes(@PathVariable("id") Long id) {
		return notesService.findNotes(id);
	}
	
	@PostMapping
	public Notes createNotes(@RequestBody Notes notes, @RequestParam("ideaId") Long ideaId){
		return notesService.createNotes(notes, ideaId);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Notes> updateNotes(@PathVariable("id") Long id,@RequestBody Notes notes, @RequestParam("ideaId") Long ideaId){
		Notes oldNotes = notesService.findNotes(id);
		if(Objects.isNull(oldNotes)) {
			return ResponseEntity.notFound().build();
		}
		notes.setId(id);
		return ResponseEntity.ok(this.notesService.createNotes(oldNotes, ideaId));
	}
}
