package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.Entity.Category;
import com.flyhub.ideamanagementsystem.Entity.Idea;
import com.flyhub.ideamanagementsystem.Entity.Priority;
import com.flyhub.ideamanagementsystem.DaO.IdeaRepository;
import com.flyhub.ideamanagementsystem.Entity.User;
import com.flyhub.ideamanagementsystem.exception.IdeaNotFoundException;

@Service
public class IdeaService {
	@Autowired
	private IdeaRepository ideaRepo;
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	PriorityService priorityService;

	public List<Idea> findAllIdeas() {
		return ideaRepo.findAll();
	}

	public Idea findIdea(Long id) {
		return ideaRepo.findById(id)
				.orElseThrow(() -> new IdeaNotFoundException(" Idea with id " + id + " doesn't exist"));
	}

	public Idea createIdea(Idea idea, Long userId, Long categoryId, Long priorityId) {
		User user = userService.findUser(userId);
		Category category = categoryService.findCategory(categoryId);
		Priority priority = priorityService.findPriority(priorityId);
		idea.setUser(user);
		idea.setCategory(category);
		idea.setPriority(priority);
		return ideaRepo.save(idea);
	}

	public void deleteIdea(Long id) {
		if (Objects.nonNull(findIdea(id))) {
			ideaRepo.deleteById(id);
		}
	}

	public List<Idea> ideaSearch(String keyword) {
		return ideaRepo.search(keyword);
	}

	public Page<Idea> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.ideaRepo.findAll(pageable);
	}

	public Page<Idea> findMyPaginated(User user, int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.ideaRepo.findByUser(user, pageable);
	}

}
