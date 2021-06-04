package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.DaO.NotesRepository;
import com.flyhub.ideamanagementsystem.Entity.Idea;
import com.flyhub.ideamanagementsystem.Entity.Notes;

@Service
public class NotesService {
	@Autowired
	private NotesRepository notesRepo;
	@Autowired
	private IdeaService ideaService;
	
	public List<Notes> findAllNotes(){
		return notesRepo.findAll();
	}
	
	public Notes findNotes(Long id) {
		return notesRepo.findById(id).get();
	}
	
	public Notes createNotes(Notes notes, Long ideaId) {
		Idea idea = ideaService.findIdea(ideaId);
		notes.setIdea(idea);
		return notesRepo.save(notes);
	}	
	
	public void deleteNotes(Long id) {
		notesRepo.deleteById(id);
	}
	
	public Page<Notes> findPaginatedNotes(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.notesRepo.findAll(pageable);
	}

}
