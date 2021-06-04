package com.flyhub.ideamanagementsystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flyhub.ideamanagementsystem.Entity.Attachment;
import com.flyhub.ideamanagementsystem.service.AttachmentService;

@RestController
@RequestMapping("api/v1/attachments")
public class AttachmentRestController {
	@Autowired
	private AttachmentService attachmentService;
	@GetMapping
	public List<Attachment> findAllAttachments(){
		return  attachmentService.findAllAttachments();
	}
	
	@GetMapping("/{id}")
	public Attachment findAttachment(@PathVariable("id") Long id) {
		return attachmentService.findAttachment(id);
	}
	
	@PostMapping	
	public Attachment createAttachment(@RequestParam("file") MultipartFile file,@RequestParam("ideaId") Long ideaId) throws IOException {			
		return attachmentService.createAttachment(file, ideaId);
	}	
	
	@DeleteMapping("/{id}")
	public void deleteAttachment(@PathVariable("id") Long id) {
		attachmentService.deleteAttachment(id);
	}
	
	@GetMapping("/download")
	public void fileUpload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("fileName") String fileName) throws IOException {
		this.attachmentService.downloadFile(request, response, fileName);
		
				
	}

}
