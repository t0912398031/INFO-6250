package com.neu.edu.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order {

	// Implicit column name
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;

	// Explicit column name
	@Column(name = "type")
	private String type;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "price")
	private String price;
	
	@Column(name = "date")
	private Date date;

	public Order() {
		this.date = new Date();
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
}
