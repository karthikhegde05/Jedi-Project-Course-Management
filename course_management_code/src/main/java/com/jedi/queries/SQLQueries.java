package com.jedi.queries;

public class SQLQueries {
	public static final String ADD_STUDENT_QUERY = "insert into student values (?, ?, ?)";
    public static final String ADD_COURSE_QUERY = "insert into course values (?,?,?,?)";
    public static final String ADD_TEACHER_QUERY = "insert into teacher values (?,?,?,?)";
    public static final String ADD_REGISTERCOURSE_QUERY="insert into student_course values (?, ?, ?)";
    public static final String GET_STUDENT_FEE = "select count(*) from student_course where studentId = ?";
}
