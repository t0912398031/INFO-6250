package com.me.labapr13;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Date;
import java.util.Random;

import org.apache.commons.mail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.ApplicantDAO;
import com.me.pojo.Applicant;

@Controller
public class JobApplicationController {
//	@Autowired
//	private ServletContext application;   //only static global instances to be AutoWired
//	
//	@PostConstruct
//	public void init() {
//		// initialize global instances
//	}

//	@PreDestroy
//	public void destroy() throws Exception {
//		// do cleanup
//	}

	@RequestMapping(value = "/job/apply.htm", method = RequestMethod.GET)
	public String showForm(HttpServletRequest request, ModelMap model, Applicant applicant) {
		//command object
		
		//model.addAttribute("applicant", applicant);
		request.setAttribute("action", "save");
		return "job-form";
	}
	
	@RequestMapping(value = "/job/apply.htm", method = RequestMethod.POST)
	public String handleUpload(@ModelAttribute("applicant") Applicant applicant, ApplicantDAO applicantDAO, HttpServletRequest request) throws IllegalStateException, IOException {
		//String locaLpath="C:\\Users\\balak\\OneDrive\\Desktop\\Webtools_Lab";
		
		
		String locaLpath = "/Users/rahulzore/Documents/";
		
		String photoNewName = generateFileName(applicant.getPhoto());
		
		applicant.getPhoto().transferTo(new File(locaLpath, photoNewName));
		
		String resumeNewName = generateFileName(applicant.getResume());
		
		try {
			File file = new File(locaLpath+resumeNewName);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(applicant.getResume().getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		int authkey1 = 0;
		int authkey2 = 0;
		        
		authkey1 = 10000000 + (new Random()).nextInt(90000000);
		authkey2 = 10000000 + (new Random()).nextInt(90000000);
		applicant.setAuthkey1(authkey1);
		applicant.setAuthkey2(authkey2);
		
		applicantDAO.create(applicant);
		try {
			//SendEmail(applicant);
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
		
		return "job-success";
	}
	
	public void SendEmail(Applicant applicant) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		//User your gmail username and password
		email.setAuthenticator(new DefaultAuthenticator("tawebtrolls@gmail.com", "yusufozbek"));
		email.setSSLOnConnect(true);
		email.setFrom("no-reply@msis.neu.edu");
		email.setSubject("TestMail");
		email.setMsg("Your application has been received");
		email.addTo(applicant.getEmail());
		email.send();
	}
	
	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}
	
	public String uploadFile(MultipartFile multipartFile) throws Exception {
		String fileName = generateFileName(multipartFile);
		String uploadDir = "uploads/";
		String filePath = uploadDir + fileName;
		try {
			File file = new File(filePath);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			fos.close();
		} catch (Exception e) {
			throw e;
		}

		return filePath;

	}

}
