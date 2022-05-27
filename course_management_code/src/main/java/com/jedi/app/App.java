package com.jedi.app;

import com.jedi.db.AdminDaoImpl;
import com.jedi.db.StudentDaoImpl;
import com.jedi.db.ProfessorDAOImpl;
import com.jedi.db.ProfessorDAO;
import com.jedi.db.ConnectionUtil;
import com.jedi.resources.AdminRestApi;
import com.jedi.resources.StudentRestApi;
import com.jedi.resources.ProfessorRestApi;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration>{

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
	
	@Override
	public void run(AppConfiguration configuration, Environment environment) throws Exception {
		
		ConnectionUtil connectionUtil = new ConnectionUtil(configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
		
		AdminDaoImpl adm = new AdminDaoImpl(connectionUtil);
		AdminRestApi admapi = new AdminRestApi(adm);
		environment.jersey().register(admapi);
		
		StudentDaoImpl stu = new StudentDaoImpl(connectionUtil);
		StudentRestApi stumapi = new StudentRestApi(stu);
		environment.jersey().register(stumapi);
		
		ProfessorDAO teacherDAO = new ProfessorDAOImpl(connectionUtil);
		ProfessorRestApi teachApi = new ProfessorRestApi(teacherDAO);
		environment.jersey().register(teachApi);
		
		
	}

}
