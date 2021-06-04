package com.flyhub.ideamanagementsystem.DaO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.flyhub.ideamanagementsystem.Entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByEmailAndPassword(String email, String password);	
	User findByEmail(String email);
	
	//User findByUsername(String username);
	
	@Modifying
	@Transactional
    @Query("UPDATE User u SET u.email = :email,u.firstName = :firstName,u.lastName = :lastName WHERE u.id = :userId")
    int updateUser(@Param("userId") Long userId, @Param("email") String email,@Param("firstName") String firstName,@Param("lastName") String lastName);
}
