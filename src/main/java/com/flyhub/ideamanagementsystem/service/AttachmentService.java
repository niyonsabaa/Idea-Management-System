package com.flyhub.ideamanagementsystem.service;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.flyhub.ideamanagementsystem.DaO.AttachmentsRepository;
import com.flyhub.ideamanagementsystem.Entity.Attachment;
import com.flyhub.ideamanagementsystem.Entity.Idea;


@Service
public class AttachmentService {
	private final String UPLOAD_DIR = "./uploads/";
	String timestamp=  String.valueOf(Timestamp.from(Instant.now()).getTime()); 
	@Autowired
	AttachmentsRepository attachmentsRepo;
	@Autowired 
	private IdeaService ideaService;
	
	
	public List<Attachment> findAllAttachments(){
		return attachmentsRepo.findAll();
	}
	
	public Attachment findAttachment(Long id) {
		return attachmentsRepo.findById(id).get();
	}
	
	public Attachment createAttachment(MultipartFile file,Long ideaId) throws IOException {	
		Attachment attachment = new Attachment();
		String fileName = timestamp+"_"+StringUtils.cleanPath(file.getOriginalFilename());
		Path path = Paths.get(UPLOAD_DIR+fileName);
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		Idea idea = ideaService.findIdea(ideaId);
		attachment.setIdea(idea);
		attachment.setAttachmentName(fileName);		
		return attachmentsRepo.save(attachment);		
	}
	
	public void deleteAttachment(Long id) {
		attachmentsRepo.deleteById(id);
	}
	
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName)
			throws IOException {
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
	
	public Page<Attachment> findPaginatedAttachments(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.attachmentsRepo.findAll(pageable);
	}
}
