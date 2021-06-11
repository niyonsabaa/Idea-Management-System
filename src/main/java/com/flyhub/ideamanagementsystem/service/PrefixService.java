package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.DaO.PrefixRepository;
import com.flyhub.ideamanagementsystem.Entity.Prefix;

@Service
public class PrefixService {
	@Autowired
	PrefixRepository prefixRepo;
	
	public List<Prefix> findAllPrefix(){
		return prefixRepo.findAll();
	}
	
	public Prefix findPrefix(Long id) {
		return prefixRepo.findById(id).get();
	}
	
	public Prefix createPrefix(Prefix prefix) {
		return prefixRepo.save(prefix);
	}
	
	public void deletePrefix(Long id) {
		prefixRepo.deleteById(id);
	}
}
