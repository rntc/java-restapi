package com.rntc.api.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rntc.api.repository.GradeRepository;
import com.rntc.entity.Grade;

/**
 * 
 * @author reis
 * @since 05/21/2017
 *
 */

@Path("/grades")
public class GradeService {

	private GradeRepository gradeRepository;

	@PostConstruct
	public void init() {
		gradeRepository = new GradeRepository();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Grade> listGrades() {
		List<Grade> grades = null;
		try {
			grades = gradeRepository.listGrades();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grades;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String saveGrade(Grade grade) {
		String msg = "";

		try {
			gradeRepository.addGrade(grade);
			msg = "Grade saved with success!";
		} catch (Exception e) {
			msg = "Error trying to saving the grade";
			e.printStackTrace();
		}

		return msg;
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Grade findById(@PathParam("id") int gradeId){
		Grade grade = null;
		
		try{
			grade = gradeRepository.findById(gradeId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return grade;
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(Grade grade, @PathParam("id") int gradeId){
		String msg = "";

		try{
			gradeRepository.edit(grade, gradeId);
			msg = "Grade edited with success!";
		}catch(Exception e){
			msg = "Error trying to editing the grade!";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(@PathParam("id") int gradeId){
		String msg = "";

		try{
			gradeRepository.delete(gradeId);
			msg = "Grade deleted with success!";
		}catch(Exception e){
			msg = "Error trying to delete the grade!";
			e.printStackTrace();
		}
		
		return msg;
	}
	
}
