package com.jedi.resources;

import com.jedi.classes.Course;
import com.jedi.classes.Professor;
import com.jedi.classes.Student;
import com.jedi.db.AdminDao;

import java.net.URI;
import java.sql.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/admin")
public class AdminRestApi {	
		AdminDao adminDao;
		
		
		
		public AdminRestApi(AdminDao adminDao) {
			super();
			this.adminDao = adminDao;
		}


		
		
		
		@POST
		@Path("/student-creation")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response addStudent(Student s) {
			String ok = adminDao.createStudent(s);
			if(ok != null) {
				return Response.status(201).entity(s).build();
			}
			else {
				throw new WebApplicationException();
			}
		}

		@POST
		@Path("/professor-creation")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response addTeacher(Professor t) {
			String ok = adminDao.createProfessor(t);
			
			if(ok != null) {
				return Response.status(201).entity(t).build();
			}
			else {
				throw new WebApplicationException(400);
			}
		}
		

		
		@POST
		@Path("/course-creation")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response addCourse(Course c) {
			
			boolean ok = adminDao.createCourse(c);
			
			if(ok) {
				return Response.created(URI.create("/course/")).entity(c).build();
			}
			else {
				throw new WebApplicationException(400);
			}
			
		}

}
