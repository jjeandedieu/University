package com.teachers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.uniclasses.UniClasses;

public class TeachersDBUtil {
	//DataSource is an interfacce
	private DataSource dataSource;

	public TeachersDBUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public TeachersDBUtil() {
	}
	
	public List<Teachers> getTeachers() throws Exception {
		
		List<Teachers> teachers = new ArrayList<>();
		
		Connection myCon = null;
		Statement myStm = null;
		ResultSet myRs = null;
		
		try {
		// Get a connection
		myCon = dataSource.getConnection();
		
		//create string to hold SQL query
		String sql = "select * from teachers order by last_name";
		
		//create statement
		myStm =  myCon.createStatement();

		//execute query
		myRs = myStm.executeQuery(sql);
		
		// process result set
		while(myRs.next()) {
			//retrieve data from result set row
			int id = myRs.getInt("id");
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
			
			String phoneNumber = myRs.getString("phone_number");
			String address = myRs.getString("address");
			String seniority = myRs.getString("seniority");
			
			String username = myRs.getString("username");
			String password = myRs.getString("password");
						
			//create new student object
			Teachers stud = new Teachers(id, firstName, lastName, email, phoneNumber, address, seniority, username, password);
			
			//add it to the list of students
			teachers.add(stud);
		}
		
		}finally {
			//close JDBC objects
			close(myCon, myStm, myRs);
			
		}
		return teachers;
		
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

	public void addTeachers(Teachers teacher) throws Exception{
		Connection myCon = null;
		PreparedStatement myStm = null;
		//ResultSet myRs = null;
		
		try {
			
		// Get Db connection
		myCon = dataSource.getConnection();
		//create sql for insert
		//the many ??? are place holders that are filled out later
		String sql = "insert into teachers(first_name, last_name, email, phone_number, address, seniority, username, password) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		myStm = myCon.prepareStatement(sql);
		
		//set the param values for the student
		myStm.setString(1, teacher.getFirstName());
		myStm.setString(2, teacher.getLastName());
		myStm.setString(3, teacher.getEmail());
		myStm.setString(4, teacher.getPhoneNumber());
		myStm.setString(5, teacher.getAddress());
		myStm.setString(6, teacher.getSeniority());
		myStm.setString(7, teacher.getUsername());
		myStm.setString(8, teacher.getPassword());
		
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

	public Teachers getTeachers(String teacherId) throws Exception {
		Teachers theTeacher = null;
		
		Connection myCon = null;
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		
		int TeacherId;
		
		try {
			
			//convert student if to int
			TeacherId = Integer.parseInt(teacherId);
			
			//get connection to databse
			myCon = dataSource.getConnection();
			
			//create sql to get selected student
			
			String sql = "select * from teachers where id  = ?";
			//create prepared statement 
			myStm = myCon.prepareStatement(sql);
			
			//set params
			myStm.setInt(1, TeacherId);
			
			//execute statement
			myRs = myStm.executeQuery();
			
			//retrieve data from result set row
			if(myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String phoneNumber = myRs.getString("phone_number");
				String address = myRs.getString("address");
				String seniority = myRs.getString("seniority");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				
				theTeacher = new Teachers(TeacherId, firstName, lastName, email, phoneNumber, address, seniority, username, password);
			}else {
				throw new Exception("Could not find student id: " + TeacherId);
			}
		
			return theTeacher;
		}
		finally {
			close(myCon, myStm, myRs);
		}
		
	}

	public void updateTeacher(Teachers theTeacher) throws SQLException {
		Connection myCon = null;
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		
		try {
		//get a db connection
		myCon = dataSource.getConnection();
		
		//create sql update statement
		String sql = "Update teachers set first_name = ?, last_name = ?, email= ? , phone_number = ?, "
				+ "address = ?, password = ? where id = ?";
		
		//set params
		myStm = myCon.prepareStatement(sql);
		
		//if we arepassing student straight away
//		myStm.setString(1, firstName);
//		myStm.setString(2, lastName);
//		myStm.setString(3, email);
//		myStm.setInt(4, id);
		
		myStm.setString(1, theTeacher.getFirstName());
		myStm.setString(2, theTeacher.getLastName());
		myStm.setString(3, theTeacher.getEmail());
		myStm.setString(4, theTeacher.getPhoneNumber());
		myStm.setString(5, theTeacher.getAddress());
		myStm.setString(6, theTeacher.getPassword());
		myStm.setInt(7, theTeacher.getId());
		
		//execute SQL statement
		
		myStm.execute();	
		
		}
		finally {
			close(myCon, myStm, myRs);
		}
	}

	public void deleteTeacher(Teachers teacher) throws SQLException {
		Connection myCon = null;
		PreparedStatement myStm = null;
		
		try {
		//get a db connection
		myCon = dataSource.getConnection();
		
		//create sql update statement
		String sql = "delete from teachers where id = ? ";
		
		//set params
		myStm = myCon.prepareStatement(sql);

		myStm.setInt(1, teacher.getId());
		
		//execute SQL statement
		
		myStm.execute();	
		}
		finally {
			close(myCon, myStm, null);
		}		
	}


}
