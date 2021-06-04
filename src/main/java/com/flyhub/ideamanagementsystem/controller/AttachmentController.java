package com.flyhub.ideamanagementsystem.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.flyhub.ideamanagementsystem.DaO.AttachmentsRepository;
import com.flyhub.ideamanagementsystem.DaO.IdeaRepository;
import com.flyhub.ideamanagementsystem.Entity.Attachment;
import com.flyhub.ideamanagementsystem.Entity.Idea;
import com.flyhub.ideamanagementsystem.service.AttachmentService;

@Controller
public class AttachmentController {
	private final String UPLOAD_DIR = "./uploads/";
	String timestamp=  String.valueOf(Timestamp.from(Instant.now()).getTime()); 
	@Autowired
	IdeaRepository ideaRepo;
	@Autowired
	AttachmentsRepository attachmentRepo;
	@Autowired
	AttachmentService attachmentService;

	@GetMapping("/add_attachment/{id}")
	public String showAddNotesForm(@PathVariable("id") long id, Model model) {
		Idea idea = ideaRepo.findById(id).get();
		model.addAttribute("idea", idea);
		model.addAttribute("attachment", new Attachment());
		return "add_attachment";
	}

	@PostMapping("/save_attachment/{id}")
	public String saveNote(@PathVariable("id") long id, RedirectAttributes redirectAttributes,
			@RequestParam("file") MultipartFile file) throws IOException {
		String fileName = timestamp+"_"+StringUtils.cleanPath(file.getOriginalFilename());
		Path path = Paths.get(UPLOAD_DIR+fileName);
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		Idea idea = ideaRepo.findById(id).get();
		attachmentRepo.save(new Attachment(idea, fileName));

		/*
		 * if (result.hasErrors()) { redirectAttributes.addFlashAttribute("message",
		 * "Uploading Attachment Failed"); return "redirect:/ideaList"; }
		 */
		redirectAttributes.addFlashAttribute("message", "Your Attachment has been Uploaded Successfully");
		return "redirect:/ideaList";
	}	
	@GetMapping("/attachmentsPage/{pageNo}")
	public String findPaginatedAttachments(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;
		Page<Attachment> page = attachmentService.findPaginatedAttachments(pageNo, pageSize, sortField, sortDir);
		List<Attachment> listAttachments = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listAttachments", listAttachments);
		return "attachmentList";
	}
	@GetMapping("/viewAttachments")
	public String showNotes(Model model) {
		return findPaginatedAttachments(1, "id", "asc", model);
	}
	
	@RequestMapping("/file/{fileName}")	
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {	
		File file = new File(UPLOAD_DIR + fileName);
		if (file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {				
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());			
		}
	}
}
