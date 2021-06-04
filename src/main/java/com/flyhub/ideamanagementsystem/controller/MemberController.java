package com.flyhub.ideamanagementsystem.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flyhub.ideamanagementsystem.Entity.Member;

import com.flyhub.ideamanagementsystem.DaO.MemberDaO;
import com.flyhub.ideamanagementsystem.service.MemberService;
@Controller
public class MemberController {
	@Autowired
	private MemberDaO memberDaO;	
	/*
	 * @Autowired private MemberService memberService;
	 * 
	 * @RequestMapping(method = RequestMethod.GET) public Collection<Member>
	 * getAllMembers(){ return memberService.getallMembers(); }
	 * 
	 * @RequestMapping(value="/{id}", method=RequestMethod.GET) public Member
	 * getMemberById(@PathVariable("id") int id) { return
	 * memberService.getMemberById(id); }
	 */
	
	//get members
	@GetMapping("members")
	public List<Member> getAllMembers(){
		return this.memberDaO.findAll();
	}
	//get member by id
	//save member
	@PostMapping("createMember")
	public Member createMember(@RequestBody Member member) {
		return this.memberDaO.save(member);
	}	
}
