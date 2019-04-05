package com.neu.edu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone_table")
public class Phone {

	// Implicit column name
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long phoneId;

	// Explicit column name
	@Column(name = "phoneNumber")
	private String phoneNumber;

	public Phone() {
	}

	public Phone(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(long id) {
		this.phoneId = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
