package com.rntc.entity;

/**
 * This is class it's a POJO for Grade.
 * 
 * @author reis
 * @version 1.0
 * @since 5/21/2017
 */
public class Grade {

	private int id;
	private String title;
	private String description;

	public Grade() {
	}

	public Grade(int id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
