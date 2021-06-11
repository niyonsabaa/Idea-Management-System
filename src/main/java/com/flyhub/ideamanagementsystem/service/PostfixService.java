package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.DaO.PostfixRepository;
import com.flyhub.ideamanagementsystem.Entity.Postfix;

@Service
public class PostfixService {
	@Autowired
	PostfixRepository postfixRepo;
	
	public List<Postfix> findAllPostfix(){
		return postfixRepo.findAll();
	}
	
	public Postfix findPostfix(Long id) {
		return postfixRepo.findById(id).get();
	}
	
	public Postfix createPostfix(Postfix postfix) {
		return postfixRepo.save(postfix);
	}
	
	public void deletePostfix(Long id) {
		postfixRepo.deleteById(id);
	}

}
