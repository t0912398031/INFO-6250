package com.neu.edu.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order {

	// Implicit column name
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;

	// Explicit column name
	@Column(name = "type")
	private String type;
	
	@Column(name = "amount")
	private int amount;

	@Column(name = "price")
	private double price;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "dealdate")
	private Date dealdate;
	
	@Column(name = "USER_ID")
	private long USER_ID;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "client")
//    private Client client;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID")
	private Set<Record> records;

	public Order() {
		this.date = new Date();
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDealdate() {
		return dealdate;
	}

	public void setDealdate(Date dealdate) {
		this.dealdate = dealdate;
	}

	public Set<Record> getRecords() {
		return records;
	}

	public void setRecords(Set<Record> records) {
		this.records = records;
	}

	public long getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(long uSER_ID) {
		USER_ID = uSER_ID;
	}

	
}
