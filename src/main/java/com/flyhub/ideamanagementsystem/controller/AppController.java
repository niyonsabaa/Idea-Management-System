package com.flyhub.ideamanagementsystem.controller;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.flyhub.ideamanagementsystem.DaO.CountryRepository;
import com.flyhub.ideamanagementsystem.DaO.GenderRepository;
import com.flyhub.ideamanagementsystem.DaO.PermissionRepository;
import com.flyhub.ideamanagementsystem.DaO.PostfixRepository;
import com.flyhub.ideamanagementsystem.DaO.PrefixRepository;
import com.flyhub.ideamanagementsystem.DaO.RoleRepository;
import com.flyhub.ideamanagementsystem.DaO.UserRepository;
import com.flyhub.ideamanagementsystem.Entity.Role;
import com.flyhub.ideamanagementsystem.Entity.User;
import com.flyhub.ideamanagementsystem.exception.ApiException;
import com.flyhub.ideamanagementsystem.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {
	@Autowired
	private UserRepository repo;
	@Autowired
	private UserService userService;
	@Autowired
	GenderRepository genderRepository;
	@Autowired
	PrefixRepository prefixRepository;
	@Autowired
	PostfixRepository postfixRepository;
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	PermissionRepository permissionRepo;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}

	@GetMapping("/dashboard")
	public String viewDashboard() {
		return "dashboard";
	}

	@GetMapping("/profile")
	public String viewProfileScreen(Model model, User user) {
		model.addAttribute("user", user);
		return "userProfile";
	}

	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("genders", genderRepository.findAll());
		model.addAttribute("prefixs", prefixRepository.findAll());
		model.addAttribute("postfixs", postfixRepository.findAll());
		model.addAttribute("countries", countryRepository.findAll());
		return "signup_form";
	}

	/*
	 * @PostMapping("/process_register") public String processRegistration(User
	 * user, RedirectAttributes redirectAttributes) { User regUser =
	 * userService.checkEmail(user.getEmail()); // Role admin = new Role("Admin");
	 * // Role normalUser = new Role("User"); Role normalUser =
	 * roleRepo.findByName("User"); if (Objects.nonNull(regUser)) {
	 * redirectAttributes.addFlashAttribute("message",
	 * "Registration Failed. An account with Email address " + user.getEmail() +
	 * " Already Exists"); return "redirect:/register"; }
	 * 
	 * User savedUser = userService.registerUser(user.getEmail(),
	 * user.getPassword(), user.getFirstName(), user.getLastName(),
	 * user.getGender(), user.getCountry(), user.getPostfix(),
	 * user.getPrefix(),user.getUsername()); // saving a user if
	 * (Objects.nonNull(savedUser)) { // saving Roles //
	 * roleRepo.saveAll(Arrays.asList(admin, normalUser)); // add Roles to user //
	 * savedUser.getRoles().addAll(Arrays.asList(admin,normalUser));
	 * savedUser.getRoles().addAll(Arrays.asList(normalUser)); // update the user
	 * repo.save(savedUser); // saving Access Permissions // permissionRepo.save(new
	 * // Permissions(normalUser,"idea",true,false,false,true));
	 * redirectAttributes.addFlashAttribute("message",
	 * "You have Successfully Registered. You can now Login"); return "redirect:/";
	 * } else { redirectAttributes.addFlashAttribute("message",
	 * "Registration Failed Please try again"); return "redirect:/register"; } }
	 */
	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		User oauthUser = userService.login(user.getEmail(), user.getPassword());		
		if (Objects.nonNull(oauthUser)) {
			// getting Roles of a logged in user
			Set<Role> userRoles = oauthUser.getRoles();
			Iterator it = userRoles.iterator();
			String roles = userRoles.stream().findFirst().get().getName();
			while (it.hasNext()) {
				Role fectchedRole = (Role) it.next();
				if (fectchedRole.getName().equals("Admin")) {
					roles = "Admin";
				} 
			}
			request.getSession().setAttribute("fName", oauthUser.getFirstName());
			request.getSession().setAttribute("lName", oauthUser.getLastName());
			request.getSession().setAttribute("email", oauthUser.getEmail());
			request.getSession().setAttribute("userId", oauthUser.getId());
			request.getSession().setAttribute("role", roles);
			return "redirect:/idea";
		} else {
			redirectAttributes.addFlashAttribute("message", "Login Failed! Either the Email or Password is incorrect");
			return "redirect:/";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@PostMapping("update_profile")
	public String updateProfile(@Param("email") String email, @Param("id") Long id,
			@Param("firstName") String firstName, @Param("lastName") String lastName, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		int result = repo.updateUser(id, email, firstName, lastName);
		if (result == 1) {
			redirectAttributes.addFlashAttribute("message", "profile updated Successfully");
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("fName", firstName);
			request.getSession().setAttribute("lName", lastName);

			return "redirect:/profile";
		} else {
			redirectAttributes.addFlashAttribute("message", "failed to update profile");
			return "redirect:/profile";
		}
	}
	
	
	
	
	
}
