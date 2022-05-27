package com.jedi.db;

import com.jedi.classes.Course;
import com.jedi.classes.Student;
import com.jedi.classes.Professor;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProfessorDAO {

    public boolean registerCoursesWithDB(String professorId,String courseId) throws SQLException;
    public List<Student> viewEnrolledStudentsWithDB(String courseId) throws SQLException;
    public boolean provideGrade(String courseId,String studentId,String grade) throws SQLException;
    Optional<Course> viewCourses(String professorId) throws SQLException, ClassNotFoundException;
}