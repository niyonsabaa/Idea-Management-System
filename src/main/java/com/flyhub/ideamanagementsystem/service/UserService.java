package com.flyhub.ideamanagementsystem.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.DaO.RoleRepository;
import com.flyhub.ideamanagementsystem.DaO.UserRepository;
import com.flyhub.ideamanagementsystem.Entity.Country;
import com.flyhub.ideamanagementsystem.Entity.Gender;
import com.flyhub.ideamanagementsystem.Entity.Postfix;
import com.flyhub.ideamanagementsystem.Entity.Prefix;
import com.flyhub.ideamanagementsystem.Entity.Role;
import com.flyhub.ideamanagementsystem.Entity.User;
import com.flyhub.ideamanagementsystem.exception.UserAlreadyExistsException;
import com.flyhub.ideamanagementsystem.exception.UserNotFoundException;


@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private GenderService genderService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private PostfixService postfixService;
	@Autowired
	private PrefixService prefixService;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public List<User> findAllUsers() {
		List<User> users = repo.findAll();		
		return users;
	}

	public User findUser(Long id) throws UserNotFoundException{
		User user = repo.findById(id).orElseThrow(() -> new UserNotFoundException("User with id "+id+ " does not Exist"));			
		return user;		
	}

	public User createUser(User user, Long genderId, Long countryId, Long postfixId, Long prefixId) {
		String encodedPassword = encoder.encode(user.getPassword());
		Role normalUser = roleRepo.findByName("User");
		User registeredUser = checkEmail(user.getEmail());
		Gender gender = genderService.findGender(genderId);
		Country country = countryService.findCountry(countryId);
		Postfix postfix = postfixService.findPostfix(postfixId);
		Prefix prefix = prefixService.findPrefix(prefixId);
		if (Objects.nonNull(registeredUser)) {
			throw new UserAlreadyExistsException("User with Email "+user.getEmail()+" Already exists.");
		}
		user.setPassword(encodedPassword);
		user.setGender(gender);
		user.setCountry(country);
		user.setPostfix(postfix);
		user.setPrefix(prefix);
		User savedUser = repo.save(user);
		savedUser.getRoles().addAll(Arrays.asList(normalUser));
		return repo.save(savedUser);
	}

	public User updateUser(User user, Long genderId, Long countryId, Long postfixId, Long prefixId) {		
		Gender gender = genderService.findGender(genderId);
		Country country = countryService.findCountry(countryId);
		Postfix postfix = postfixService.findPostfix(postfixId);
		Prefix prefix = prefixService.findPrefix(prefixId);		
		user.setGender(gender);
		user.setCountry(country);
		user.setPostfix(postfix);
		user.setPrefix(prefix);
		return repo.save(user);
	}

	public void deleteUser(Long id) {
		if(Objects.nonNull(findUser(id))) {
			repo.deleteById(id);
		}		
	}

	public User login(String email, String password) {
		User user = repo.findByEmail(email);
		String savedPassword = user.getPassword();
		if (encoder.matches(password, savedPassword)) {
			return user;
		} else {
			return null;
		}
	}

	public User checkEmail(String email) {
		User user = repo.findByEmail(email);
		return user;
	}

	public User findUserByUsername(String username) {
		User user = repo.findByUsername(username);
		if(Objects.isNull(user)) {
			throw new UserNotFoundException("User with username "+username+ " doesn't exist.");
		}
		return user;
	}
	
	public User makeAdmin(Long id) {
		User user = findUser(id);
		Role adminUser = roleRepo.findByName("Admin");
		user.getRoles().addAll(Arrays.asList(adminUser));		
		return repo.save(user);
	}
	
	public User removeAdminRole(Long id) {
		User user = findUser(id);
		Role adminUser = roleRepo.findByName("Admin");
		user.getRoles().remove(adminUser);		
		return repo.save(user);		
	}

    //creating user Account
	public User registerUser(String email, String password, String firstName, String lastName, Long genderId,
			Long countryId, Long postfixId, Long prefixId, String username) {
		String encodedPassword = encoder.encode(password);
		Gender gender = genderService.findGender(genderId);
		Country country = countryService.findCountry(countryId);
		Postfix postfix = postfixService.findPostfix(postfixId);
		Prefix prefix = prefixService.findPrefix(prefixId);
		User user = new User(email, encodedPassword, firstName, lastName, username);
		user.setGender(gender);
		user.setCountry(country);
		user.setPostfix(postfix);
		user.setPrefix(prefix);
		User result = repo.save(user);
		return result;
	}
}
