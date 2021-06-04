package com.flyhub.ideamanagementsystem.DaO;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flyhub.ideamanagementsystem.Entity.Member;

import java.util.HashMap;
import java.util.Collection;
@Repository
public interface MemberDaO extends JpaRepository<Member, Long> {
	

}
