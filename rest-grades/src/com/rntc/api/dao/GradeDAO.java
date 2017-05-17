package com.rntc.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.rntc.config.DBConfig;
import com.rntc.entity.Grade;

public class GradeDAO {

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
	
	public void edit(Grade grade, int id) throws Exception{
		Connection connection = DBConfig.getConnection();
		
		String sql = "UPDATE GRADE SET TITLE=?, DESCRIPTION=? WHERE ID_GRADE = ?";
		System.out.println(grade.getId() + grade.getTitle() + grade.getDescription());
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, grade.getTitle());
		statement.setString(2, grade.getDescription());
		statement.setInt(3, id);
		statement.execute();
	}
	
	public void addGrade(Grade grade) throws Exception{
		Connection connection = DBConfig.getConnection();
		
		String sql = "INSERT INTO GRADE(TITLE, DESCRIPTION) VALUES(?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, grade.getTitle());
		statement.setString(2, grade.getDescription());
		statement.execute();
	}

	public static void main(String[] args) throws Exception {
		GradeDAO dao = new GradeDAO();
		Grade grade = new Grade();
		grade.setDescription("bla bluh");
		grade.setTitle("Computer stuff");
		dao.addGrade(grade);
	}
}
