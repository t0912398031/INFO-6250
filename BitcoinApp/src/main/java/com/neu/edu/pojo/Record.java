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
	private double price;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "orderId")
	private long orderId;
	
	@Column(name = "targetId")
	private long targetId;

	public Record() {
	}
	
	public Record(int dealAmount, double sellPrice, long orderId, String type, long targetId) {
		this.amount = dealAmount;
		this.price = sellPrice;
		this.orderId = orderId;
		this.type = type;
		this.targetId = targetId;
		this.date = new Date();
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getTargetId() {
		return targetId;
	}

	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}

}
