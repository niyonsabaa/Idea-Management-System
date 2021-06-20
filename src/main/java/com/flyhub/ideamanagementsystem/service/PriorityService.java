package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.DaO.PriorityRepository;
import com.flyhub.ideamanagementsystem.Entity.Priority;
import com.flyhub.ideamanagementsystem.exception.PriorityNotFoundException;

@Service
public class PriorityService {
	@Autowired
	private PriorityRepository priorityRepo;

	public List<Priority> findAllPriorities() {
		return priorityRepo.findAll();
	}

	public Priority findPriority(Long id) {
		return priorityRepo.findById(id)
				.orElseThrow(() -> new PriorityNotFoundException("Priority with id " + id + " doesn't exist."));
	}

	public Priority createPriority(Priority priority) {
		return priorityRepo.save(priority);
	}

	public void deletePriority(Long id) {
		if (Objects.nonNull(findPriority(id))) {
			priorityRepo.deleteById(id);
		}
	}
}
