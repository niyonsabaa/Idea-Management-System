package com.flyhub.ideamanagementsystem.DaO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.Entity.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Long> {

}
