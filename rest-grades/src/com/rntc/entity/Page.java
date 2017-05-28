package com.rntc.entity;

import java.util.List;

/**
 * This class it's used to return the JSON representation of list of grades.
 * 
 * @author reis
 * @version 1.0
 * @since 5/28/2017
 */
public class Page {

	private List<Grade> grades;

	public Page() {
	}

	public Page(List<Grade> grades) {
		this.setGrades(grades);
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

}
