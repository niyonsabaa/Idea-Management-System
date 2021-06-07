package com.flyhub.ideamanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPassword {
@Test
	public void testPassword() {
		// TODO Auto-generated method stub
		String rawPassword = "root";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encryptedPassword = encoder.encode(rawPassword);
		System.out.println(encryptedPassword);		
		/*
		 * if(encoder.matches("charisma",encryptedPassword)) {
		 * System.out.println("First Passwords match"); } else {
		 * System.out.println("First Passwords don't match"); }
		 */
		
	}

}
