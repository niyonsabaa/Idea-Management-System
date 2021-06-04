package com.flyhub.ideamanagementsystem.DaO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.Entity.Attachment;

public interface AttachmentsRepository extends JpaRepository<Attachment, Long> {

}
