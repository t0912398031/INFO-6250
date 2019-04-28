package com.me.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Applicant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applicationid;
	
	@Column
	private String first;
	@Column
	private String last;
	@Column
	private String email;
	@Column
	private String nuid;
	@Column
	private String gpa;
	@Column
	private String major;
	@Column
	private String entrance;
	@Column
	private String graduation;
	@Column
	private String aboutme;
	@Column
	private String skills;
	@Column
	private String coop;
	@Column
	private String prevta;
	@Column
	private String courses;
	@Column
	private String whichclass;
	
	@Transient
	private MultipartFile photo;
	@Transient
	private MultipartFile resume;
	
	
	//private String saveorupdate;
	@Column
	private int authkey1;
	@Column
	private int authkey2;
	@Column
	private String photoFile;
	@Column
	private String resumeFile;
	
	public Applicant() {
		
	}
	
	
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNuid() {
		return nuid;
	}
	public void setNuid(String nuid) {
		this.nuid = nuid;
	}
	public String getGpa() {
		return gpa;
	}
	public void setGpa(String gpa) {
		this.gpa = gpa;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	public String getGraduation() {
		return graduation;
	}
	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}
	public String getAboutme() {
		return aboutme;
	}
	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getCoop() {
		return coop;
	}
	public void setCoop(String coop) {
		this.coop = coop;
	}
	public String getPrevta() {
		return prevta;
	}
	public void setPrevta(String prevta) {
		this.prevta = prevta;
	}
	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
	public String getWhichclass() {
		return whichclass;
	}
	public void setWhichclass(String whichclass) {
		this.whichclass = whichclass;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public MultipartFile getResume() {
		return resume;
	}
	public void setResume(MultipartFile resume) {
		this.resume = resume;
	}

	public int getAuthkey1() {
		return authkey1;
	}
	public void setAuthkey1(int authkey1) {
		this.authkey1 = authkey1;
	}
	public int getAuthkey2() {
		return authkey2;
	}
	public void setAuthkey2(int authkey2) {
		this.authkey2 = authkey2;
	}
	
	
	public long getApplicationid() {
		return applicationid;
	}


	public void setApplicationid(long applicationid) {
		this.applicationid = applicationid;
	}


	public String getPhotoFile() {
		return photoFile;
	}


	public void setPhotoFile(String photoFile) {
		this.photoFile = photoFile;
	}


	public String getResumeFile() {
		return resumeFile;
	}


	public void setResumeFile(String resumeFile) {
		this.resumeFile = resumeFile;
	}


	@Override
	public String toString() {
		return this.getFirst();
	}
}
