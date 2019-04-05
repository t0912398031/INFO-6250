package com.neu.edu.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "record_table")
public class Record {

	// Implicit column name
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;

	// Explicit column name
	@Column(name = "price")
	private int price;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "date")
	private Date date;

	public Record() {
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	
}
