package com.rntc.api.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rntc.api.exception.APIException;
import com.rntc.config.DBConfig;
import com.rntc.entity.Grade;

/**
 * The class GradeRepository represents specific operation in the database.
 * 
 * @version 1.0
 * @author reis
 * @since 5/21/2017
 */
public class GradeRepository {

	public static final int PAGE_LENGTH = 5;
	
	/**
	 * This method returns an offset of grades off the database. 
	 * 
	 * @param page This parameter represents the offset of the page 
	 * @return List<Grade> Returns the list of grades from the database.
	 * @throws APIException
	 */
	public List<Grade> getByRange(int page) throws APIException{
		List<Grade> grades = new ArrayList<>();
		
		try{
			Connection connection = DBConfig.getConnection();
			
			page = (page - 1) * PAGE_LENGTH;
			String sql = "SELECT * FROM GRADE OFFSET ? LIMIT ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, page);
			statement.setInt(2,  PAGE_LENGTH);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				Grade grade = new Grade(
						resultSet.getInt("ID_GRADE"), 
						resultSet.getString("TITLE"), 
						resultSet.getString("DESCRIPTION"));
				grades.add(grade);
			}
		}catch(Exception e){
			throw new APIException(500, "Error trying to page the data of the database." + e.getMessage());
		}
		
		return grades;
	}

	/**
	 * This method returns a paged list of grades.
	 * 
	 * @return List<Grade> This returns a list of grades from the DB.
	 * @throws Exception
	 * 
	 */
	public List<Grade> getAll() throws APIException {

		List<Grade> grades = new ArrayList<>();
		try {
			Connection connection = DBConfig.getConnection();
			String sql = "SELECT * FROM GRADE LIMIT " + PAGE_LENGTH;

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Grade grade = new Grade(
						resultSet.getInt("ID_GRADE"), 
						resultSet.getString("TITLE"),
						resultSet.getString("DESCRIPTION"));
				grades.add(grade);
			}
		} catch (Exception e) {
			throw new APIException(500, "Error trying to find the data in the database." + e.getMessage());
		}

		return grades;

	}
}
