package com.jedi.db;

import com.jedi.classes.Admin;
import com.jedi.classes.Course;
import com.jedi.classes.Professor;
import com.jedi.queries.SQLQueries;
import com.jedi.classes.Student;
import com.jedi.db.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdminDaoImpl implements AdminDao{
	
	
	ConnectionUtil connectionUtil;
    
    public AdminDaoImpl(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}
    
    
    @Override
    public boolean createCourse(Course course) {
    	try(Connection connection = connectionUtil.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into course values (?,?,?,?)");
            preparedStatement.setString(1, course.getCourseId());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setInt(3, course.getSeats());
            preparedStatement.setInt(4, course.getFee());

            int rows = preparedStatement.executeUpdate();
            
            if(rows == 1) {
            	return true;
            }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
            
        return false;
            
    }
    
    @Override
    public String createStudent(Student student) {
    	String str = null;
        try(Connection connection = connectionUtil.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into student values (?, ?, ?)");
            preparedStatement.setString(1, student.getUserId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getDepartment());

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Student is Created Successfully!");
            }
            str = student.getUserId();
        }
        catch(SQLException e)
        {
            System.out.println("Student already exists");
            e.printStackTrace();
        }
        return str;
    }
    
    @Override
    public String createProfessor(Professor prof) {
    	String str = null;
    	try(Connection connection = connectionUtil.getConnection();) {
            System.out.println(3);

    		PreparedStatement preparedStatement = connection.prepareStatement("insert into teacher values (?,?,?,?)");
            preparedStatement.setString(1, prof.getUserId());
            preparedStatement.setString(2, prof.getName());
            preparedStatement.setString(3, prof.getDepartment());
            preparedStatement.setString(4, prof.getCourseId());
            System.out.println(2);
            int rows = preparedStatement.executeUpdate();
            System.out.println(1);
            if (rows == 1) {
                System.out.println("Professor is Created Successfully");
            }
            str = prof.getUserId();
    	}
            catch(SQLException e)
            {
                System.out.println("Professor already exists");
                e.printStackTrace();
            }
            return str;
    }
    

}