package com.StudentsAndClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.uniclasses.UniClasses;
import com.students.Students;


public class StudentsAndClassesDbUtil {
	private DataSource dataSource;

	public StudentsAndClassesDbUtil(DataSource datasource) {
		this.dataSource = datasource;
		
	}
	
	public List<UniClasses> getClasses() throws Exception {
		
		List<UniClasses> classes = new ArrayList<>();
		
		Connection myCon = null;
		Statement myStm = null;
		ResultSet myRs = null;
		
		try {
		// Get a connection
		myCon = dataSource.getConnection();
		
		//create string to hold SQL query
		String sql = "select * from classes order by class_name";
		
		//create statement
		myStm =  myCon.createStatement();

		//execute query
		myRs = myStm.executeQuery(sql);
		
		// process result set
		while(myRs.next()) {
			//retrieve data from result set row
			int id = myRs.getInt("id");
			String class_name = myRs.getString("class_name");
			String class_time = myRs.getString("class_time");
			String class_professor = myRs.getString("class_professor");
			String class_level = myRs.getString("class_level");
			int seats_available  = myRs.getInt("seats_available");
			
			//create new class object			
			UniClasses c = new UniClasses(id, class_name, class_time, class_professor, class_level, seats_available);
						
			//add it to the list of students
			classes.add(c);
		}
		
		}finally {
			//close JDBC objects
			close(myCon, myStm, myRs);
			
		}
		return classes;
		
	}
	
	private void close(Connection myCon, Statement myStm, ResultSet myRs) {
		try {
			if(myRs != null) {
				myRs.close();
			}
			if(myStm != null) {
				myStm.close();
			}
			if(myCon != null) { //Makes the connection object availabel for use by someone else
				myCon.close(); 
			}
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	
	
	public void registerStudentClasses(Students theStudent, UniClasses theClass) throws Exception{
		Connection myCon = null;
		PreparedStatement myStm = null;
		//ResultSet myRs = null;
		
		
		try {
			
		// Get Db connection
		myCon = dataSource.getConnection();
		//create sql for insert
		//the many ??? are place holders that are filled out later
		String sql = "insert into studentclassmap(student_id, class_id) "
				+ "values (?, ?)";
		
		myStm = myCon.prepareStatement(sql);
		
		//set the param values for the classes
		myStm.setInt(1, theStudent.getId());
		myStm.setInt(2, theClass.getId());

		
		//execute sql insert
		//can not issues data manipulation with executeQuery()
		myStm.execute();
		
		
		}finally {
			//clean up JDBC objects
			if(myStm != null) {
				myStm.close();
			}
			if(myCon != null) { //Makes the connection object availabel for use by someone else
				myCon.close(); 
			}
			
		}
	}
	
	public void dropClass(String student_ID, String classId) throws SQLException {
		Connection myCon = null;
		PreparedStatement myStm = null;
		//ResultSet myRs = null;
		
		int stud_ID = Integer.parseInt(student_ID);
		int class_ID = Integer.parseInt(classId);
		
		try {
			
		// Get Db connection
		myCon = dataSource.getConnection();
		//create sql for insert
		//the many ??? are place holders that are filled out later
		String sql = "delete from studentclassmap where student_id = ? and class_id = ?";
		
		myStm = myCon.prepareStatement(sql);
		
		//set the param values for the classes
		myStm.setInt(1, stud_ID);
		myStm.setInt(2, class_ID);
		
		//execute sql insert
		//can not issues data manipulation with executeQuery()
		myStm.execute();
		
		
		}finally {
			//clean up JDBC objects
			if(myStm != null) {
				myStm.close();
			}
			if(myCon != null) { //Makes the connection object availabel for use by someone else
				myCon.close(); 
			}
			
		}	
	}

	public void dropClassTeacher(String teacher_ID, String classId) throws SQLException {
		Connection myCon = null;
		PreparedStatement myStm = null;
		//ResultSet myRs = null;
		
		int teach_ID = Integer.parseInt(teacher_ID);
		int class_ID = Integer.parseInt(classId);
		
		try {
			
		// Get Db connection
		myCon = dataSource.getConnection();
		//create sql for insert
		//the many ??? are place holders that are filled out later
		String sql = "update classes set professor_id = null where professor_id = ? and id = ?";
		
		myStm = myCon.prepareStatement(sql);
		
		//set the param values for the classes
		myStm.setInt(1, teach_ID);
		myStm.setInt(2, class_ID);
		
		//execute sql insert
		//can not issues data manipulation with executeQuery()
		myStm.execute();
		
		
		}finally {
			//clean up JDBC objects
			if(myStm != null) {
				myStm.close();
			}
			if(myCon != null) { //Makes the connection object availabel for use by someone else
				myCon.close(); 
			}
			
		}	
	}


}
