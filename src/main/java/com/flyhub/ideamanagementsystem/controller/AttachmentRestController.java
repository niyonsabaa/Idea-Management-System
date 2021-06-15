package com.flyhub.ideamanagementsystem.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping("api/v1/attachments")
public class AttachmentRestController {
	private final String UPLOAD_DIR = "./uploads/";
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
	
	/*
	 * @GetMapping("/download") public void fileUpload(HttpServletRequest request,
	 * HttpServletResponse response,
	 * 
	 * @RequestParam("fileName") String fileName) throws IOException {
	 * this.attachmentService.downloadFile(request, response, fileName);
	 * 
	 * 
	 * }
	 */
	
	 @GetMapping("/download")
	    public void download(@RequestParam("fileName") String fileName,
	                         HttpServletResponse response) throws Exception{
		 File file = new File(UPLOAD_DIR + fileName);
	        
	        byte[] buffer = new byte[1024];
	        BufferedInputStream bis = null;
	        OutputStream os = null;	       
	            //Determine whether the parent directory of the file exists
	            if (file.exists()) {
	                //Set to return file information
	                response.setContentType("application/octet-stream;charset=UTF-8");
	                response.setCharacterEncoding("UTF-8");
	                os = response.getOutputStream();
	                bis = new BufferedInputStream(new FileInputStream(file));
	                while(bis.read(buffer) != -1){
	                    os.write(buffer);
	                }
	            }	         
	    }

}
