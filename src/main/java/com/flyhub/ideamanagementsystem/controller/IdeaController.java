package com.flyhub.ideamanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flyhub.ideamanagementsystem.DaO.CategoryRepository;
import com.flyhub.ideamanagementsystem.DaO.IdeaRepository;
import com.flyhub.ideamanagementsystem.DaO.PriorityRepository;
import com.flyhub.ideamanagementsystem.DaO.UserRepository;
import com.flyhub.ideamanagementsystem.Entity.Category;
import com.flyhub.ideamanagementsystem.Entity.Idea;
import com.flyhub.ideamanagementsystem.Entity.Priority;
import com.flyhub.ideamanagementsystem.Entity.User;
import com.flyhub.ideamanagementsystem.service.IdeaService;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class IdeaController {
	@Autowired
	IdeaRepository ideaRepo;
	@Autowired
	IdeaService ideaService;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	PriorityRepository priorityRepo;
	@Autowired
	UserRepository repo;

	@GetMapping("/idea")
	public String showIdeaScrean(Model model) {
		model.addAttribute("idea", new Idea());
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("priorities", priorityRepo.findAll());
		return "ideas";
	}

	// @GetMapping("/ideaList")
	/*
	 * public String showIdeas(Model model) { model.addAttribute("ideas",
	 * ideaRepo.findAll()); return "ideaList"; }
	 */

	@PostMapping("/register_idea")
	public String registerIdea(@Param("userId") long userId,@Param("categoryId") long categoryId,@Param("priorityId") long priorityId ,Idea idea, BindingResult result,
			RedirectAttributes redirectAttributes) {
		User user = repo.findById(userId).get();
		Category cat = categoryRepo.findById(categoryId).get();
		Priority priority = priorityRepo.findById(priorityId).get();
		ideaRepo.save(new Idea(user, userId, idea.getIdeaTitle(), cat, idea.getDateOfSubmission(),
				idea.getIdeaDescription(), priority, idea.getIdeaExecutiveSummary(),
				idea.getIdeaBackgroundDescription()));
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "Saving Idea Failed");
			return "redirect:/idea";
		}
		redirectAttributes.addFlashAttribute("message", "Your Idea has been saved Successfully.");
		return "redirect:/idea";
	}

	@GetMapping("/idea_edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Idea idea = ideaRepo.findById(id).get();
		model.addAttribute("idea", idea);
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("priorities", priorityRepo.findAll());
		return "edit_idea";
	}

	@GetMapping("/idea_delete/{id}")
	public String deleteIdea(@PathVariable("id") long id, Model model) {
		Idea idea = ideaRepo.findById(id).get();
		ideaRepo.delete(idea);
		return "redirect:/ideaList";
	}

	/*
	 * @PostMapping("/idea_update/{id}") public String
	 * updateIdea(@PathVariable("id") long id, Idea idea, RedirectAttributes
	 * redirectAttributes) { ideaRepo.save(idea);
	 * redirectAttributes.addFlashAttribute("message", "Idea #" + id +
	 * " has been updated Successfully."); return "redirect:/ideaList"; }
	 */

	@PostMapping("/idea_update/{id}")
	public String updateIdeas(@PathVariable("id") long id, @Param("ideaTitle") String ideaTitle,
			@Param("categoryId") Long categoryId, @Param("dateOfSubmission") String dateOfSubmission,
			@Param("ideaDescription") String ideaDescription, @Param("priorityId") Long priorityId,
			@Param("ideaExecutiveSummary") String ideaExecutiveSummary,
			@Param("ideaBackgroundDescription") String ideaBackgroundDescription,
			RedirectAttributes redirectAttributes) {
		Category cat = categoryRepo.findById(categoryId).get();
		Priority priority = priorityRepo.findById(priorityId).get();
		int result = ideaRepo.updateIdea(id, ideaTitle,cat ,priority,dateOfSubmission, ideaDescription,
				ideaExecutiveSummary, ideaBackgroundDescription);
		if (result == 1) {
			redirectAttributes.addFlashAttribute("message", "Idea #" + id + " has been updated Successfully.");
			return "redirect:/ideaList";
		} else {
			redirectAttributes.addFlashAttribute("message", "Failed to update Idea # " + id);
			return "redirect:/ideaList";
		}
	}

	@RequestMapping("/idea_search")

	public String viewSearchPage(Model model, @Param("keyword") String keyword) {
		List<Idea> listIdeas = ideaService.ideaSearch(keyword);
		model.addAttribute("ideas", listIdeas);
		model.addAttribute("keyword", keyword);

		return "ideaSearchList";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 2;
		Page<Idea> page = ideaService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Idea> listIdeas = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listIdeas", listIdeas);
		return "ideaList";
	}
	
	@GetMapping("/myPage/{pageNo}")
	public String findMyPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model,HttpSession session) {
		int pageSize = 2;
		Long userId = (Long)session.getAttribute("userId");
		User user = repo.findById(userId).get();
		Page<Idea> page = ideaService.findMyPaginated(user,pageNo, pageSize, sortField, sortDir);
		List<Idea> listMyIdeas = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listMyIdeas", listMyIdeas);
		return "myIdeas";
	}

	@GetMapping("/ideaList")
	public String showIdeas(Model model) {
		return findPaginated(1, "id", "asc", model);
	}
	
	@GetMapping("/myIdeas")
	public String showMyIdeas(Model model, HttpSession session) {
	return findMyPaginated(1, "id", "asc", model,session);
	}
	
	/*
	 * @GetMapping("/myIdeas") public String showMyIdeas(Model model, HttpSession
	 * session) { Long userId = (Long)session.getAttribute("userId"); User user =
	 * repo.findById(userId).orElseThrow(() -> new
	 * IllegalArgumentException("Invalid Idea Id: " + userId)); List<Idea> userIdea
	 * = ideaRepo.findByUser(user); model.addAttribute("userIdeas",userIdea); return
	 * "myIdeas"; }
	 */
}
