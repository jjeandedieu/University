package com.students;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentsDbUtil {
	//DataSource is an interfacce
	private DataSource dataSource;

	public StudentsDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Students> getStudents() throws Exception {
		
		List<Students> students = new ArrayList<>();
		
		Connection myCon = null;
		Statement myStm = null;
		ResultSet myRs = null;
		
		try {
		// Get a connection
		myCon = dataSource.getConnection();
		
		//create string to hold SQL query
		String sql = "select * from students order by last_name";
		
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
			String grade = myRs.getString("grade");
			
			String username = myRs.getString("username");
			String password = myRs.getString("password");
						
			//create new student object
			Students stud = new Students(id, firstName, lastName, email, phoneNumber, address, grade, username, password);
			
			//add it to the list of students
			students.add(stud);
		}
		
		}finally {
			//close JDBC objects
			close(myCon, myStm, myRs);
			
		}
		return students;
		
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

public List<Students> getStudentsClass(String classId) throws Exception {
		
		List<Students> students = new ArrayList<>();
		
		Connection myCon = null;
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		
		int class_ID = Integer.parseInt(classId);
		
		try {
		// Get a connection
		myCon = dataSource.getConnection();
		
		//create string to hold SQL query
		String sql = "select sc.class_id, s.id, s.first_name, "
				+ "s.last_name, s.email from students s inner join "
				+ "studentclassmap sc on s.id = sc.student_id where sc.class_id = ? ";
		
		//create statement
		myStm =  myCon.prepareStatement(sql);
		
		//
		myStm.setInt(1, class_ID);

		//execute query
		myRs = myStm.executeQuery();
		
		// process result set
		while(myRs.next()) {
			//retrieve data from result set row
			int class_id = myRs.getInt("class_id");
			int student_id = myRs.getInt("id");
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");

						
			//create new student object
			Students stud = new Students(class_id, student_id, firstName, lastName, email);
			
			//add it to the list of students
			students.add(stud);
		}
		
		}finally {
			//close JDBC objects
			close(myCon, myStm, myRs);
			
		}
		return students;
		
	}
	
	public void addStudents(Students student) throws Exception{
		Connection myCon = null;
		PreparedStatement myStm = null;
		//ResultSet myRs = null;
		
		try {
			
		// Get Db connection
		myCon = dataSource.getConnection();
		//create sql for insert
		//the many ??? are place holders that are filled out later
		String sql = "insert into students(first_name, last_name, email, phone_number, address, grade, username, password) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		myStm = myCon.prepareStatement(sql);
		
		//set the param values for the student
		myStm.setString(1, student.getFirstName());
		myStm.setString(2, student.getLastName());
		myStm.setString(3, student.getEmail());
		myStm.setString(4, student.getPhoneNumber());
		myStm.setString(5, student.getAddress());
		myStm.setString(6, student.getGrade());
		myStm.setString(7, student.getUsername());
		myStm.setString(8, student.getPassword());
		
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

	public Students getStudents(String studentId) throws Exception {
		Students theStudent = null;
		
		Connection myCon = null;
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		
		int StudentId;
		
		try {
			
			//convert student if to int
			StudentId = Integer.parseInt(studentId);
			
			//get connection to databse
			myCon = dataSource.getConnection();
			
			//create sql to get selected student
			
			String sql = "select * from students where id  = ?";
			//create prepared statement 
			myStm = myCon.prepareStatement(sql);
			
			//set params
			myStm.setInt(1, StudentId);
			
			//execute statement
			myRs = myStm.executeQuery();
			
			//retrieve data from result set row
			if(myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String phoneNumber = myRs.getString("phone_number");
				String address = myRs.getString("address");
				String grade = myRs.getString("grade");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				
				theStudent = new Students(StudentId, firstName, lastName, email, phoneNumber, address, grade, username, password);
			}else {
				throw new Exception("Could not find student id: " + StudentId);
			}
		
			return theStudent;
		}
		finally {
			close(myCon, myStm, myRs);
		}
		
	}

	public void updateStudent(Students theStudent) throws SQLException {
		Connection myCon = null;
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		
		try {
		//get a db connection
		myCon = dataSource.getConnection();
		
		//create sql update statement
		String sql = "Update students set first_name = ?, last_name = ?, email= ? , phone_number = ?, "
				+ "address = ?, grade = ?, password = ? where id = ?";
		
		//set params
		myStm = myCon.prepareStatement(sql);
		
		//if we arepassing student straight away
//		myStm.setString(1, firstName);
//		myStm.setString(2, lastName);
//		myStm.setString(3, email);
//		myStm.setInt(4, id);
		
		myStm.setString(1, theStudent.getFirstName());
		myStm.setString(2, theStudent.getLastName());
		myStm.setString(3, theStudent.getEmail());
		myStm.setString(4, theStudent.getPhoneNumber());
		myStm.setString(5, theStudent.getAddress());
		myStm.setString(6, theStudent.getGrade());
		myStm.setString(7, theStudent.getPassword());
		myStm.setInt(8, theStudent.getId());
		
		//execute SQL statement
		
		myStm.execute();	
		
		}
		finally {
			close(myCon, myStm, myRs);
		}
	}

	public void deleteStudent(Students student) throws SQLException {
		Connection myCon = null;
		PreparedStatement myStm = null;
		
		try {
		//get a db connection
		myCon = dataSource.getConnection();
		
		//create sql update statement
		String sql = "delete from students where id = ? ";
		
		//set params
		myStm = myCon.prepareStatement(sql);

		myStm.setInt(1, student.getId());
		
		//execute SQL statement
		
		myStm.execute();	
		}
		finally {
			close(myCon, myStm, null);
		}		
	}

	public List<Students> getStudentsAjax(String name) throws SQLException {
		
		List<Students> students = new ArrayList<>();
		
		Connection myCon = null;
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		
		try {
			
			
			myCon = dataSource.getConnection();
			
			//create sql update statement
			String sql = "select * from students where first_name like ? "
					+ "or last_name like ? order by first_name;";
			
			//set params
			myStm = myCon.prepareStatement(sql);
		
			myStm.setString(1, name + "%");
			myStm.setString(2, name + "%");

			//execute query
			myRs = myStm.executeQuery();
			
		// process result set
		while(myRs.next()) {
			//retrieve data from result set row
			int id = myRs.getInt("id");
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
			
			String phoneNumber = myRs.getString("phone_number");
			String address = myRs.getString("address");
			String grade = myRs.getString("grade");
			
			//create new student object
			Students stud = new Students(id, firstName, lastName, email, phoneNumber, address, grade);
			
			//add it to the list of students
			students.add(stud);
			
		}
		
		}finally {
			//close JDBC objects
			close(myCon, myStm, myRs);
			
		}
		return students;
	}
	

}
