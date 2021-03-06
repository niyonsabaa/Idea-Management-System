package com.flyhub.ideamanagementsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.flyhub.ideamanagementsystem.Entity.User;
import com.flyhub.ideamanagementsystem.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/users")
public class UserRestController {
	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/{id}")
	public User findUser(@PathVariable("id") Long id) {
		User user = userService.findUser(id);
		return user;
	}

	@GetMapping("/user")
	public User findUserByUsername(@RequestParam("username") String username) {
		return userService.findUserByUsername(username);
	}

	@PostMapping
	public User createUser(@RequestBody User user, @RequestParam("genderId") Long genderId,
			@RequestParam("countryId") Long countryId, @RequestParam("postfixId") Long postfixId,
			@RequestParam("prefixId") Long prefixId) {
		return userService.createUser(user, genderId, countryId, postfixId, prefixId);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id,

			@RequestParam("genderId") Long genderId,

			@RequestParam("countryId") Long countryId, @RequestParam("postfixId") Long postfixId,

			@RequestParam("prefixId") Long prefixId) {
		User oldUser = userService.findUser(id);
		user.setId(id);
		user.setPassword(oldUser.getPassword());
		user.setUsername(oldUser.getUsername());
		user.setRoles(oldUser.getRoles());
		return ResponseEntity.ok(this.userService.updateUser(user, genderId, countryId, postfixId, prefixId));
	}

	@PostMapping("/admin/{id}")
	public User makeAdmin(@PathVariable("id") Long id) {
		return userService.makeAdmin(id);
	}
	@PostMapping("/user/{id}")
	public User removeAdminRole(@PathVariable("id") Long id) {
		return userService.removeAdminRole(id);
	}
}
