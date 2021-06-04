package com.flyhub.ideamanagementsystem.DaO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.Entity.Permissions;

public interface PermissionRepository extends JpaRepository<Permissions, Long> {

}
