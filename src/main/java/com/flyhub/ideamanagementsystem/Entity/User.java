package com.flyhub.ideamanagementsystem.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, length = 45)
	private String email;
	@Column(nullable = false, length = 64)
	private String password;
	@Column(nullable = false, length = 20)
	private String firstName;
	@Column(nullable = false, length = 20)
	private String lastName;

	@Column(name = "username", unique = true)
	private String username;
	
	@ManyToOne(targetEntity = Gender.class, fetch = FetchType.LAZY)

	@JoinColumn(name = "gender_id", nullable = false)

	@OnDelete(action = OnDeleteAction.CASCADE)

	private Gender gender;

	@ManyToOne(targetEntity = Country.class, fetch = FetchType.LAZY)

	@JoinColumn(name = "country_id", nullable = false)

	@OnDelete(action = OnDeleteAction.CASCADE)

	private Country country;

	@ManyToOne(targetEntity = Postfix.class, fetch = FetchType.LAZY)

	@JoinColumn(name = "postfix_id", nullable = false)

	@OnDelete(action = OnDeleteAction.CASCADE)

	private Postfix postfix;
	
	
	@ManyToOne(targetEntity = Prefix.class, fetch = FetchType.LAZY)

	@JoinColumn(name = "prefix_id", nullable = false)

	@OnDelete(action = OnDeleteAction.CASCADE)

	private Prefix prefix;	

	// many to many relationships between the User and Role
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Role> roles = new HashSet<>();

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Postfix getPostfix() {
		return postfix;
	}

	public void setPostfix(Postfix postfix) {
		this.postfix = postfix;
	}

	public Prefix getPrefix() {
		return prefix;
	}

	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country= country;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	  public String getUsername() { return username; }
	  
	  public void setUsername(String username) { this.username = username; }
	 

	public User() {
	}

	public User(String email, String password, String firstName, String lastName,String username) {
        this.email= email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;		
		this.username = username;
	}	

}
