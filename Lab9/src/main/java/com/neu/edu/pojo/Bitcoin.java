package com.neu.edu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Table(name = "bitcoin_table")
public class Bitcoin {

	// Implicit column name
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;

	// Explicit column name
	@Column(name = "bicoinID")
	private String bicoinID;

	public Bitcoin() {
		this.bicoinID = RandomStringUtils.random(15, true, true);
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getBicoinID() {
		return bicoinID;
	}

	public void setBicoinID(String bicoinID) {
		this.bicoinID = bicoinID;
	}

}
