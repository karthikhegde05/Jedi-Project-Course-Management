package com.jedi.db;

import com.jedi.classes.Course;
import com.jedi.classes.Professor;
import com.jedi.classes.Student;
import com.jedi.db.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorDAOImpl implements ProfessorDAO {

    ConnectionUtil connectionUtil;
    
    public ProfessorDAOImpl(ConnectionUtil connectionUtil) {
		// TODO Auto-generated constructor stub
    	this.connectionUtil = connectionUtil;
	}

	@Override
	// this will assign course to a professor
	public boolean registerCoursesWithDB(String professorId, String courseId) throws SQLException {
		// TODO Auto-generated method stub
		int res = 0;
		try(Connection connection = connectionUtil.getConnection();) {
			String sql = "update teacher set courseId = ? where userId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, courseId);
			preparedStatement.setString(2, professorId);
		    res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return res == 1;
	}


	@Override
	// how many students have enrolled in a course?
	public List<Student> viewEnrolledStudentsWithDB(String courseId) throws SQLException {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<Student>();
		try(Connection connection = connectionUtil.getConnection();) {
			
			String sql = "select * from student where userId in (select studentId from student_course"
					+ " where courseId = ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, courseId);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()) {
		    	Student student = new Student(rs.getString(1), rs.getString(2), rs.getString(3));
		    	students.add(student);
		    }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return students;
	}


	@Override
	// update the grade for a course for a particular student
	public boolean provideGrade(String courseId, String studentId, String grade) throws SQLException {
		
		int res = 0;
		try(Connection connection = connectionUtil.getConnection();) {
			String sql = "update student_course set grade = ? where studentId = ? and courseId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, grade);
			preparedStatement.setString(2, studentId);
			preparedStatement.setString(3, courseId);
		    res = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return res == 1;
	}


	@Override
	// for a particular professorId view courses
	public Optional<Course> viewCourses(String professorId) throws SQLException, ClassNotFoundException {
		
		Optional<Course> opt = Optional.empty();
		try(Connection connection = connectionUtil.getConnection();) {
			String sql = "select * from course where courseId in (select courseId from teacher where userId = "+professorId + ")";
			System.out.println(sql);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
		    ResultSet rs = preparedStatement.executeQuery();
		    if (rs.next()) {
		    	Course course = new Course(rs.getString(1), rs.getString(2),
		    			rs.getInt(3), rs.getInt(4));
		    	opt = Optional.of(course);
		    }
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return opt;
	}
}