package com.rntc.api.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.rntc.api.exception.APIException;
import com.rntc.config.DBConfig;
import com.rntc.entity.Grade;

/**
 * This class represents database operations such as: select, update, insert, delete.
 * 
 * @author reis
 * @version 1.0
 * @since 5/28/2017
 */
public class GradeMapper {

	
	
	/**
	 * This method retrieves a Grade by the id.
	 * 
	 * @param grade The grade that wants to be search for it.
	 * @return Grade The grade that was found.
	 * @throws APIException
	 */
	public Grade select(Grade grade) throws APIException {
		try{
			Connection connection = DBConfig.getConnection();

			String sql = "SELECT * FROM GRADE WHERE ID_GRADE= ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, grade.getId());
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()){
				grade = new Grade(
						resultSet.getInt("ID_GRADE"), 
						resultSet.getString("TITLE"),
						resultSet.getString("DESCRIPTION"));
			}else{
				return null;
			}
		}catch(Exception e){
			throw new APIException(500, e.getMessage());
		}
		
		return grade;
	}
	
	/**
 	 * This method add a grade and returns the grade that was insert.
	 * 
	 * @param grade The grade that it will be inserted.
	 * @return Grade The grade that was just inserted.
	 * @throws APIException
	 */
	public Grade insert(Grade grade) throws APIException {
		try{
			Connection connection = DBConfig.getConnection();

			String sql = "INSERT INTO GRADE(TITLE, DESCRIPTION) VALUES(?,?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, grade.getTitle());
			statement.setString(2, grade.getDescription());
			statement.execute();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				grade.setId(resultSet.getInt(1));
			}
		}catch(Exception e){
			throw new APIException(500, e.getMessage());
		}
		
		return grade;
	}

	/**
	 * This method update a grade that is passing in as parameter 
	 * 
	 * @param grade The grade that will be updated.
	 * @return Grade Returns the grade that was just updated.
	 * @throws APIException
	 */
	public Grade update(Grade grade) throws APIException {
		try{
			Connection connection = DBConfig.getConnection();

			String sql = "UPDATE GRADE SET TITLE=?, DESCRIPTION=? WHERE ID_GRADE = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, grade.getTitle());
			statement.setString(2, grade.getDescription());
			statement.setInt(3, grade.getId());
			statement.execute();
			
			if(statement.getUpdateCount() == 0){
				throw new APIException(500, "The grade specified doesn't exist.");
			}
		}catch(APIException ae){
			throw ae;
		}catch(Exception e){
			throw new APIException(500, "Error not specified: " + e.getMessage());
		}
		
		return grade;
	}

	/**
	 * This method deletes a grade
	 * 
	 * @param grade This is the grade that will be deleted from the database.
	 * @return grade
	 * @throws APIException
	 * 
	 */
	public Grade delete(Grade grade) throws APIException {
		try{
			Connection connection = DBConfig.getConnection();

			String sql = "DELETE FROM GRADE WHERE ID_GRADE=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, grade.getId());
			statement.execute();
			if(statement.getUpdateCount() == 0){
				throw new APIException(400, "The grade specified doesn't exist.");
			}
		}catch(APIException ae){
			throw ae;
		}catch(Exception e){
			throw new APIException(500, e.getMessage());
		}	
		return null;
	}

}
