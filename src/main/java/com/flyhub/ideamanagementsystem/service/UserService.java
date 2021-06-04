package com.flyhub.ideamanagementsystem.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.DaO.RoleRepository;
import com.flyhub.ideamanagementsystem.DaO.UserRepository;
import com.flyhub.ideamanagementsystem.Entity.Role;
import com.flyhub.ideamanagementsystem.Entity.User;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private RoleRepository roleRepo;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public List<User> findAllUsers(){
		return repo.findAll();
	}
	
	public User findUser(Long id){
		return repo.findById(id).get();
	}
	
	public User createUser(User user) {
		String encodedPassword = encoder.encode(user.getPassword());
		Role normalUser = roleRepo.findByName("User");
		User registeredUser = checkEmail(user.getEmail());
		if(Objects.nonNull(registeredUser)) {
			return null;
		}
		user.setPassword(encodedPassword);
		User savedUser= repo.save(user);
		savedUser.getRoles().addAll(Arrays.asList(normalUser));
		return repo.save(savedUser);		
	}
	
	public void deleteUser(Long id) {
		repo.deleteById(id);
	}

	public User login(String email, String password) {		
		User user = repo.findByEmail(email);
		String savedPassword = user.getPassword();
		if(encoder.matches(password,savedPassword)) {
		return user;
		}else {
			return null;
		}
	}

	public User checkEmail(String email) {
		User user = repo.findByEmail(email);
		return user;
	}

//creating user Account
	public User registerUser(String email, String password, String firstName, String lastName,
			int gender, int countryId, int postfix, int prefix,String username) {
		String encodedPassword = encoder.encode(password);
		User user = new User(email,encodedPassword,firstName,lastName,gender,countryId,postfix,prefix,username);
		User result = repo.save(user);
		return result;
	}
}
