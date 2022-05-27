package com.jedi.db;

import com.jedi.classes.Course;
import com.jedi.classes.StudentCourse;
import com.jedi.classes.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface StudentDao
{

    Optional<Student> getStudent(String studentId) throws SQLException;

   int getTotalFees(String studentId) throws SQLException;
    
    void registerCourses(String studentId,String courseId) throws SQLException;

    ArrayList<Course> viewCourses(String studentId) throws SQLException;

    String viewGrades(String studentId,String courseId) throws SQLException;

}