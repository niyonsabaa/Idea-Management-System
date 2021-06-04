package com.flyhub.ideamanagementsystem.DaO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
