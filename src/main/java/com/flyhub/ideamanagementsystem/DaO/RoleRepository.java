package com.flyhub.ideamanagementsystem.DaO;


import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.Entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);	
}
