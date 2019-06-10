package com.uniclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.StudentsAndClasses.StudentsAndClasses;
import com.students.Students;

public class UniClassesDbUtil {
	private DataSource dataSource;

	public UniClassesDbUtil(DataSource datasource) {
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
			String class_level = myRs.getString("class_level");
			int seats_available  = myRs.getInt("seats_available");
			String class_day = myRs.getString("class_day");
			int professor_id  = myRs.getInt("professor_id");
			
			//create new class object			
			UniClasses c = new UniClasses(id, class_name, class_time, class_level, seats_available, class_day, professor_id);
						
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
	
	
	
	public void addClasses(UniClasses c) throws Exception{
		Connection myCon = null;
		PreparedStatement myStm = null;
		//ResultSet myRs = null;
		
		try {
			
		// Get Db connection
		myCon = dataSource.getConnection();
		//create sql for insert
		//the many ??? are place holders that are filled out later
		String sql = "insert into classes(class_name, class_time, class_professor, class_level, seats_available) "
				+ "values (?, ?, ?, ?, ?)";
		
		myStm = myCon.prepareStatement(sql);
		
		//set the param values for the classes
		myStm.setString(1, c.getClassName());
		myStm.setString(2, c.getClassTime());
		myStm.setString(3, c.getProfessor());
		myStm.setString(4, c.getClassLevel());
		myStm.setInt(5, c.getSeatsAvailable());

		
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
	
	public UniClasses getClass(String classId) throws Exception {
		UniClasses theClass= null;
		
		Connection myCon = null;
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		
		int theclassId;
		
		try {
			
			//convert student if to int
			theclassId = Integer.parseInt(classId);
			
			//get connection to databse
			myCon = dataSource.getConnection();
			
			//create sql to get selected student
			
			String sql = "select * from classes where id  = ?";
			//create prepared statement 
			myStm = myCon.prepareStatement(sql);
			
			//set params
			myStm.setInt(1, theclassId);
			
			//execute statement
			myRs = myStm.executeQuery();
			
			//retrieve data from result set row
			if(myRs.next()) {
				int id = myRs.getInt("id");
				String className = myRs.getString("class_name");
				String classTime = myRs.getString("class_time");
				String classLevel = myRs.getString("class_level");
				int SeatsAvailable = myRs.getInt("seats_available");
				String classDay = myRs.getString("class_day");
				int professorId = myRs.getInt("professor_id");
				
				theClass = new UniClasses(id, className, classTime, classLevel, SeatsAvailable, classDay, professorId);
			}else {
				throw new Exception("Could not find class id: " + theclassId);
			}
		
			return theClass;
		}
		finally {
			close(myCon, myStm, myRs);
		}
		
	}
	
public List<UniClasses> getStudentClasses(String studentId) throws Exception {
		
		List<UniClasses> studentclasses = new ArrayList<>();
		
		Connection myCon = null;
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		
		int studentIdPass;
		
		try {
			
		studentIdPass = Integer.parseInt(studentId);
			
		// Get a connection
		myCon = dataSource.getConnection();
		
		//create string to hold SQL query
		String sql = "select c.id, class_name, class_time, "
				+ "class_level, seats_available, class_day, professor_id from studentclassmap sc, classes c, "
				+ "students s where sc.student_id = ? and s.id = sc.student_id  "
				+ "and sc.class_id = c.id order by c.id";
		
		//create prepared statement 
		myStm = myCon.prepareStatement(sql);
		
		//set params
		myStm.setInt(1, studentIdPass);
		
		//execute statement
		myRs = myStm.executeQuery();
		
		// process result set
		while(myRs.next()) {
			//retrieve data from result set row
			int id = myRs.getInt("id");
			String class_name = myRs.getString("class_name");
			String class_time = myRs.getString("class_time");
			//String class_professor = myRs.getString("class_professor");
			String class_level = myRs.getString("class_level");
			int seats_avaialable = myRs.getInt("seats_available");
			String class_day = myRs.getString("class_day");
			int professor_id = myRs.getInt("professor_id");

			//create new class object			
			UniClasses c = new UniClasses(id, class_name, class_time, class_level, seats_avaialable, class_day, professor_id);
						
			//add it to the list of students
			studentclasses.add(c);
		}
		
		}finally {
			//close JDBC objects
			close(myCon, myStm, myRs);
			
		}
		return studentclasses;
		
	}


public List<UniClasses> getTeachersClasses(String teacherId) throws Exception {
	
	List<UniClasses> teacherclasses = new ArrayList<>();
	
	Connection myCon = null;
	PreparedStatement myStm = null;
	ResultSet myRs = null;
	
	int teacherIdPass;
	
	try {
		
	teacherIdPass = Integer.parseInt(teacherId);
		
	// Get a connection
	myCon = dataSource.getConnection();
	
	//create string to hold SQL query
//	String sql = "select c.id, class_name, class_time, "
//			+ "class_level, seats_available, class_day, professor_id from studentclassmap sc, classes c, "
//			+ "students s where sc.student_id = ? and s.id = sc.student_id  "
//			+ "and sc.class_id = c.id order by c.id";
	
	String sql = "select * from classes where professor_id = ?";
	
	//create prepared statement 
	myStm = myCon.prepareStatement(sql);
	
	//set params
	myStm.setInt(1, teacherIdPass);
	
	//execute statement
	myRs = myStm.executeQuery();
	
	// process result set
	while(myRs.next()) {
		//retrieve data from result set row
		int id = myRs.getInt("id");
		String class_name = myRs.getString("class_name");
		String class_time = myRs.getString("class_time");
		//String class_professor = myRs.getString("class_professor");
		String class_level = myRs.getString("class_level");
		int seats_avaialable = myRs.getInt("seats_available");
		String class_day = myRs.getString("class_day");
		int professor_id = myRs.getInt("professor_id");

		//create new class object			
		UniClasses c = new UniClasses(id, class_name, class_time, class_level, seats_avaialable, class_day, professor_id);
					
		//add it to the list of students
		teacherclasses.add(c);
	}
	
	}finally {
		//close JDBC objects
		close(myCon, myStm, myRs);
		
	}
	return teacherclasses;
	
}


public void decreaseSeatsAvailable(String classId) throws Exception {
	int seatsAvailable;
	
	Connection myCon = null;
	PreparedStatement myStm = null;
	ResultSet myRs = null;
	
	int theclassId;
	
	try {
		
		//convert student if to int
		theclassId = Integer.parseInt(classId);
		
		//get connection to databse
		myCon = dataSource.getConnection();
		
		//create sql to get selected student
		
		String sql = "select * from classes where id  = ?";
		//create prepared statement 
		myStm = myCon.prepareStatement(sql);
		
		//set params
		myStm.setInt(1, theclassId);
		
		//execute statement
		myRs = myStm.executeQuery();
		
		//retrieve data from result set row
		if(myRs.next()) {

			seatsAvailable = myRs.getInt("seats_available");
			
		}else {
			throw new Exception("Could not find class id: " + theclassId);
		}
		
		seatsAvailable = seatsAvailable - 1;

		updateClassSeats(theclassId, seatsAvailable);
	
	}
	finally {
		close(myCon, myStm, myRs);
	}	
}

public void increaseSeatsAvailable(String classId) throws Exception {
	int seatsAvailable;
	
	Connection myCon = null;
	PreparedStatement myStm = null;
	ResultSet myRs = null;
	
	int theclassId;
	
	try {
		
		//convert student if to int
		theclassId = Integer.parseInt(classId);
		
		//get connection to databse
		myCon = dataSource.getConnection();
		
		//create sql to get selected student
		
		String sql = "select * from classes where id  = ?";
		//create prepared statement 
		myStm = myCon.prepareStatement(sql);
		
		//set params
		myStm.setInt(1, theclassId);
		
		//execute statement
		myRs = myStm.executeQuery();
		
		//retrieve data from result set row
		if(myRs.next()) {

			seatsAvailable = myRs.getInt("seats_available");
			
		}else {
			throw new Exception("Could not find class id: " + theclassId);
		}
		
		seatsAvailable = seatsAvailable + 1;

		updateClassSeats(theclassId, seatsAvailable);
	
	}
	finally {
		close(myCon, myStm, myRs);
	}	
}

public void updateClassSeats(int classId, int seats) throws SQLException {
	Connection myCon = null;
	PreparedStatement myStm = null;
	//ResultSet myRs = null;
	
	try {
		
	// Get Db connection
	myCon = dataSource.getConnection();
	//create sql for insert
	//the many ??? are place holders that are filled out later
	String sql = "update classes set seats_available = ? where id = ?";
	
	myStm = myCon.prepareStatement(sql);
	
	//set the param values for the classes
	myStm.setInt(1, seats);
	myStm.setInt(2, classId);
	
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

public void setProfessorId(int theTeacherId, int classId) throws SQLException {
	Connection myCon = null;
	PreparedStatement myStm = null;
	//ResultSet myRs = null;
	
	try {
		
	// Get Db connection
	myCon = dataSource.getConnection();
	//create sql for insert
	//the many ??? are place holders that are filled out later
	String sql = "update classes set professor_id = ? where id = ?";
	
	myStm = myCon.prepareStatement(sql);
	
	//set the param values for the classes
	myStm.setInt(1, theTeacherId);
	myStm.setInt(2, classId);
	
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
