package com.jedi.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jedi.classes.Course;
import com.jedi.classes.Student;
import com.jedi.db.AdminDao;
import com.jedi.db.StudentDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

@Path("/student")
public class StudentRestApi {
	
	StudentDao studentDao;
	
	public StudentRestApi(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudent(@QueryParam("userId") String userId) throws SQLException {
		Optional<Student> opt = studentDao.getStudent(userId);
		if(opt.isPresent()) {
            return Response.ok(opt.get()).build();
        }
        else {
        	throw new WebApplicationException(404);
        }
	}
	
	@GET
	@Path("/fee")
	@Produces(MediaType.APPLICATION_JSON)
	public int getTotalFees(@QueryParam("studentId") String studentId) throws SQLException {
		int fees = studentDao.getTotalFees(studentId);
		return fees;
	}
	
	@GET
	@Path("/grades")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGrades(@QueryParam("studentId") String studentId, @QueryParam("courseId") String courseId) throws SQLException {
		String opt = studentDao.viewGrades(studentId, courseId);
		if(opt != null) {
			return Response.ok(opt).build();
        }
        else {
        	throw new WebApplicationException(404);
        }
	}
	
	@POST
	@Path("/courses")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerCourses(@QueryParam("studentId") String studentId, @QueryParam("courseId") String courseId) throws SQLException {
		studentDao.registerCourses(studentId, courseId);
		return Response.ok("Course Registered!").build();
	}
	
	@GET
	@Path("/courses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> viewCourses(@QueryParam("studentId") String studentId) throws SQLException{
		ArrayList<Course> courses = studentDao.viewCourses(studentId);
		return courses;
	}
	
}
