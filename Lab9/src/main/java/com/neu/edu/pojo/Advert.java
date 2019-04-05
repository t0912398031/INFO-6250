package com.neu.edu.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "Advert")
public class Advert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "advertId")
	private long advertId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String message;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private User user;

	@Column(nullable = false)
	@ManyToMany(mappedBy = "adverts")
	private Set<Category> categories = new HashSet<Category>();

	public Advert(String title, String message, User user, Category catergory) {
		this.title = title;
		this.message = message;
		this.user = user;
	}

	public Advert() {
	}

	public long getAdvertId() {
		return advertId;
	}

	public void setAdvertId(long advertId) {
		this.advertId = advertId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Advert [advertId=" + advertId + ", title=" + title + ", message=" + message + ", user=" + user
				+ ", categories=" + categories + "]";
	}

}
