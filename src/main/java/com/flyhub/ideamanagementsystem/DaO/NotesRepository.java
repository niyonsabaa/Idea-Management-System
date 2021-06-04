package com.flyhub.ideamanagementsystem.DaO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.Entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Long> {

}
