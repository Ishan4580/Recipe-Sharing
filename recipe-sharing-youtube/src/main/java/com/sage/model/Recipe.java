package com.sage.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity

public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	@ManyToOne
	private User user;
	private String image;
	private String description;
	private boolean vagetarian;
	private LocalDateTime cratedAt;
	private List<Long> likes =new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isVagetarian() {
		return vagetarian;
	}
	public void setVagetarian(boolean vagetarian) {
		this.vagetarian = vagetarian;
	}
	public LocalDateTime getCratedAt() {
		return cratedAt;
	}
	public void setCratedAt(LocalDateTime cratedAt) {
		this.cratedAt = cratedAt;
	}
	public List<Long> getLikes() {
		return likes;
	}
	public void setLikes(List<Long> likes) {
		this.likes = likes;
	}
	
}
