package com.flyhub.ideamanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.flyhub.ideamanagementsystem.DaO.UserRepository;
import com.flyhub.ideamanagementsystem.Entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	UserRepository repo;
	@Autowired
	private TestEntityManager testManager;
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("jean@gmail.com");
		user.setFirstName("Jean ");
		user.setLastName("K");
		user.setPassword("1245");		
		User savedUser = repo.save(user);
		User existUser = testManager.find(User.class, savedUser.getId());
		assertThat(existUser.getEmail().equals(user.getEmail()));
	}
	
}
