package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.DaO.PostfixRepository;
import com.flyhub.ideamanagementsystem.Entity.Postfix;
import com.flyhub.ideamanagementsystem.exception.PostfixNotFoundException;

@Service
public class PostfixService {
	@Autowired
	PostfixRepository postfixRepo;

	public List<Postfix> findAllPostfix() {
		return postfixRepo.findAll();
	}

	public Postfix findPostfix(Long id) {
		return postfixRepo.findById(id)
				.orElseThrow(() -> new PostfixNotFoundException("postfix with id " + id + " doesn't exist"));
	}

	public Postfix createPostfix(Postfix postfix) {
		return postfixRepo.save(postfix);
	}

	public void deletePostfix(Long id) {
		if (Objects.nonNull(id)) {
			postfixRepo.deleteById(id);
		}
	}

}
