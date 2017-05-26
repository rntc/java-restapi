package com.rntc.api.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.rntc.config.DBConfig;
import com.rntc.entity.Grade;

/**
 * The class GradeRepository represents the database operations, such as: add,
 * edit, delete, findById.
 * 
 * @version 1.0
 * @author reis
 * @since 5/21/2017
 */
public class GradeRepository {

	/**
	 * This method returns a list of grades.
	 * 
	 * @return List<Grade> This returns a list of grades from the DB.
	 * @throws Exception
	 */
	public List<Grade> listGrades() throws Exception {

		List<Grade> grades = new ArrayList<>();

		Connection connection = DBConfig.getConnection();
		String sql = "SELECT * FROM GRADE";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Grade grade = new Grade();
			grade.setId(resultSet.getInt("ID_GRADE"));
			grade.setTitle(resultSet.getString("TITLE"));
			grade.setDescription(resultSet.getString("DESCRIPTION"));
			grades.add(grade);
		}

		return grades;

	}

	/**
	 * This method is used to find a grade by id, and if find one returns the
	 * Grade.
	 * 
	 * @param idGrade
	 *            int That represents the id of the grade.
	 * @return Grade The object grade.
	 * @throws Exception
	 */
	public Grade findById(int idGrade) throws Exception {
		Grade grade = null;

		Connection connection = DBConfig.getConnection();

		String sql = "SELECT * FROM GRADE WHERE ID_GRADE= ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, idGrade);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			grade = new Grade();
			grade.setId(resultSet.getInt("ID_GRADE"));
			grade.setTitle(resultSet.getString("TITLE"));
			grade.setDescription(resultSet.getString("DESCRIPTION"));
		}

		return grade;
	}

	/**
	 * This method edit a grade by the given id.
	 * 
	 * @param grade
	 *            The object grade.
	 * @param id
	 *            The id of the grade.
	 * @throws Exception
	 */
	public void edit(Grade grade, int id) throws Exception {
		Connection connection = DBConfig.getConnection();

		String sql = "UPDATE GRADE SET TITLE=?, DESCRIPTION=? WHERE ID_GRADE = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, grade.getTitle());
		statement.setString(2, grade.getDescription());
		statement.setInt(3, id);
		statement.execute();
	}

	/**
	 * This method delete a grade by the given id.
	 * 
	 * @param id
	 *            The grade id
	 * @throws Exception
	 */
	public void delete(int id) throws Exception {
		Connection connection = DBConfig.getConnection();

		String sql = "DELETE FROM GRADE WHERE ID_GRADE=?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		statement.execute();
	}

	/**
	 * This method add a grade
	 * 
	 * @param grade
	 *            The grade object.
	 * @throws Exception
	 */
	public void addGrade(Grade grade) throws Exception {
		Connection connection = DBConfig.getConnection();

		String sql = "INSERT INTO GRADE(TITLE, DESCRIPTION) VALUES(?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, grade.getTitle());
		statement.setString(2, grade.getDescription());
		statement.execute();
	}
}
