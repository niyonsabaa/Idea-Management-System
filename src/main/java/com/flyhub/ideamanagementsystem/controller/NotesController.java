package com.flyhub.ideamanagementsystem.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.flyhub.ideamanagementsystem.DaO.IdeaRepository;
import com.flyhub.ideamanagementsystem.DaO.NotesRepository;
import com.flyhub.ideamanagementsystem.Entity.Idea;
import com.flyhub.ideamanagementsystem.Entity.Notes;
import com.flyhub.ideamanagementsystem.service.NotesService;

@Controller
public class NotesController {
	@Autowired
	IdeaRepository ideaRepo;
	@Autowired
	NotesRepository notesRepo;
	@Autowired 
	NotesService notesService;
	@GetMapping("/add_note/{id}")
	public String showAddNotesForm(@PathVariable("id") long id,Model model) {
		Idea idea = ideaRepo.findById(id).get();
		model.addAttribute("idea",idea);
		model.addAttribute("notes", new Notes());
		return "add_notes";
	}
	@PostMapping("/save_note/{id}")
	public String saveNote(Notes note, @PathVariable("id") long id,RedirectAttributes redirectAttributes) {			
		Idea idea = ideaRepo.findById(id).get();
		notesRepo.save(
				new Notes(idea,note.getNoteContent())
				);
		
		redirectAttributes.addFlashAttribute("message", "Your Note has been saved Successfully.");
		return "redirect:/ideaList";
	}
	
	@GetMapping("/notesPage/{pageNo}")
	public String findPaginatedNotes(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;
		Page<Notes> page = notesService.findPaginatedNotes(pageNo, pageSize, sortField, sortDir);
		List<Notes> listNotes = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listNotes", listNotes);
		return "notesList";
	}
	
	@GetMapping("/viewNotes")
	public String showNotes(Model model) {
		return findPaginatedNotes(1, "id", "asc", model);
	}	
	
}
