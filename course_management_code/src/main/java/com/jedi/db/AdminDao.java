package com.jedi.db;

import java.util.List;
import java.util.Optional;

import com.jedi.classes.Admin;
import com.jedi.classes.Course;
import com.jedi.classes.Professor;
import com.jedi.classes.Student;

public interface AdminDao {
    
	default String createProfessor(Professor prof) {
        return "";
    }
    
    default String createStudent(Student student) {
    	return "";
    }
    
    boolean createCourse(Course course);

}