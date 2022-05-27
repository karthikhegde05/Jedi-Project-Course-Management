package com.jedi.resources;

import com.jedi.classes.Course;
import com.jedi.classes.Professor;
import com.jedi.classes.Student;
import com.jedi.db.ProfessorDAO;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/professor/{professorId}")
public class ProfessorRestApi {

    ProfessorDAO profDAO;

    public ProfessorRestApi(ProfessorDAO profDAO) {
        super();
        this.profDAO = profDAO;
    }

    @GET
    @Path("/course")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response viewCourses(@PathParam("professorId") String professorId) throws SQLException, ClassNotFoundException {
        return Response.ok(profDAO.viewCourses(professorId)).build();
    }


    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response viewEnrolledStudents(@HeaderParam("courseId") String courseId) throws SQLException {
        return Response.ok(profDAO.viewEnrolledStudentsWithDB(courseId)).build();
    }

    @PUT
    @Path("/course")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerCourse(@PathParam("professorId") String professorId, @HeaderParam("courseId") String courseId) {
        boolean updated = false;
        try {
            updated = profDAO.registerCoursesWithDB(professorId, courseId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (updated) {
            return Response.noContent().build();
        } else {
            throw new WebApplicationException(Status.NOT_FOUND);
        }
    }
    
    //Assign grade to students
    @PUT
    @Path("/course/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response provideGrade (@PathParam("professorId") String professorId,
                                  @HeaderParam("grade") String grade,
                                  @PathParam("studentId") String studentId) {
        try {
            String courseId = profDAO.viewCourses(professorId).get().getCourseId();
            boolean updated = profDAO.provideGrade(courseId, studentId, grade);
            if (updated) {
                return Response.noContent().build();
            } else {
                throw new WebApplicationException(Status.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}