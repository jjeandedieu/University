package com.grades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.sql.DataSource;

import com.students.Students;

public class ClassGradesDBUtil {
	private DataSource dataSource;
	HashMap<Integer, List<Integer>> map = new HashMap<Integer , List<Integer>>();


	public ClassGradesDBUtil(DataSource datasource) {
		this.dataSource = datasource;
		
	}
	
	public void addStudentsGrade(ClassGrades theClassGrade) throws Exception{
		Connection myCon = null;
		PreparedStatement myStm = null;
		//ResultSet myRs = null;
		
		try {
			
		// Get Db connection
		myCon = dataSource.getConnection();
		
		//Insert Everything from database from studentId and classId column
		getValuesAndInsert(theClassGrade);
		
		
		
		//Check if I should do an insert or an update depending on the value
		//of the boolean variable.
		boolean insertUpdateCheck = iterateThroughAndCheck(map, theClassGrade.getStudentId(), theClassGrade.getClassId());
		
		//If the student id, classid combination or just the studentid exist
		if(insertUpdateCheck) {
			//update of the corresponding row that is not null
			
			//if getgrade1 is not null or empty, update that row
			if(theClassGrade.getGrade1() != -1) {
				
				String sql = "update classgradess1 set grade_1 = ? where student_id = ?";

				myStm = myCon.prepareStatement(sql);
				
				myStm.setInt(1, theClassGrade.getGrade1());
				myStm.setInt(2, theClassGrade.getStudentId());

			}
			//if getgrade2 is not null or empty, update that row
			else if(theClassGrade.getGrade2() != -1) {
				
				String sql = "update classgradess1 set grade_2 = ? where student_id = ?";
				
				myStm = myCon.prepareStatement(sql);
				
				myStm.setInt(1, theClassGrade.getGrade2());
				myStm.setInt(2, theClassGrade.getStudentId());



			}
			//if getgrade3 is not null or empty, update that row
			else if(theClassGrade.getFinalGrade() != -1) {
				
				String sql = "update classgradess1 set final_grade = ? where student_id = ?";
				
				myStm = myCon.prepareStatement(sql);
				
				System.out.println(theClassGrade.getFinalGrade());
				System.out.println(theClassGrade.getStudentId());
				
				myStm.setInt(1, theClassGrade.getFinalGrade());
				myStm.setInt(2, theClassGrade.getStudentId());

			}
			else if(theClassGrade.getFinalLetterGrade() != "z") {
				
				String sql = "update classgradess1 set final_letter_grade = ? where student_id = ?";
				
				myStm = myCon.prepareStatement(sql);
				
				myStm.setString(1, theClassGrade.getFinalLetterGrade());
				myStm.setInt(2, theClassGrade.getStudentId());

			}

		}else {
			
			//if getgrade1 is not null or empty, update that row
			if(theClassGrade.getGrade1() != -1) {
				
				String sql = "insert into classgradess1(student_id, teacher_id, class_id, grade_1) "
						+ "values (?, ?, ?, ?)";

				myStm = myCon.prepareStatement(sql);
				
				//set the param values for the student
				myStm.setInt(1, theClassGrade.getStudentId());
				myStm.setInt(2, theClassGrade.getTechaerId());
				myStm.setInt(3, theClassGrade.getClassId());
				myStm.setInt(4, theClassGrade.getGrade1());

			}
			//if getgrade2 is not null or empty, update that row
			else if(theClassGrade.getGrade2() != -1) {
				
				String sql = "insert into classgradess1(student_id, teacher_id, class_id, grade_2) "
						+ "values (?, ?, ?, ?)";

				myStm = myCon.prepareStatement(sql);
				
				//set the param values for the student
				myStm.setInt(1, theClassGrade.getStudentId());
				myStm.setInt(2, theClassGrade.getTechaerId());
				myStm.setInt(3, theClassGrade.getClassId());
				myStm.setInt(4, theClassGrade.getGrade2());

			}
			//if getgrade3 is not null or empty, update that row
			else if(theClassGrade.getFinalGrade() != -1) {
				
				String sql = "insert into classgradess1(student_id, teacher_id, class_id, final_grade) "
						+ "values (?, ?, ?, ?)";

				myStm = myCon.prepareStatement(sql);
				
				//set the param values for the student
				myStm.setInt(1, theClassGrade.getStudentId());
				myStm.setInt(2, theClassGrade.getTechaerId());
				myStm.setInt(3, theClassGrade.getClassId());
				myStm.setInt(4, theClassGrade.getFinalGrade());

			}
			//if getgrade3 is not null or empty, update that row
			else if(theClassGrade.getFinalLetterGrade() != "z") {
				
				String sql = "insert into classgradess1(student_id, teacher_id, class_id, final_letter_grade) "
						+ "values (?, ?, ?, ?)";

				myStm = myCon.prepareStatement(sql);
				
				//set the param values for the student
				myStm.setInt(1, theClassGrade.getStudentId());
				myStm.setInt(2, theClassGrade.getTechaerId());
				myStm.setInt(3, theClassGrade.getClassId());
				myStm.setString(4, theClassGrade.getFinalLetterGrade());

			}
			
		}
		
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
	
	//check if given studentid and classid have already been inserted
	//so we know if we should do an insert or update.
	public void getValuesAndInsert(ClassGrades theClassGrade) throws SQLException {

		HashMap<Integer, Integer> map = new HashMap<Integer , Integer>();
		
		Connection myCon = null;
		PreparedStatement myStm = null;
		ResultSet myRs = null;
		
		try {
			//Get a connection
			myCon = dataSource.getConnection();
			
			//Create String to hold SQL query
			String sql = "select student_id, class_id from classgradess1 where student_id = ?";
			
			System.out.println(theClassGrade.getStudentId());
			//create statement
			myStm = myCon.prepareStatement(sql);
			
			//Setup the parameter
			myStm.setInt(1, theClassGrade.getStudentId());
			
			//execute query
			myRs = myStm.executeQuery();
			
			//process result set
			while(myRs.next()) {
				int student_Id = myRs.getInt("student_id");
				int class_Id = myRs.getInt("class_id");
				
				addValuesAndCheck(new Integer(student_Id), new Integer(class_Id));
			}

		}finally {
			//close JDBC objects
			close(myCon, myStm, myRs);
		}
		
				
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
	
	
	private void addValuesAndCheck(Integer key, Integer value) {
		List<Integer> tempList;
		
		System.out.println(key + " " + value);

		   //Check if the student id is already there
		   if (map.containsKey(key)) {
			  //Make templist == to the arrayList from that key
		      tempList = map.get(key);
		     
		      if(tempList == null) {
		         tempList = new ArrayList<>();
		      }
		      
		      tempList.add(value);  
		      
		   } else {
		      tempList = new ArrayList<>();
		      tempList.add(value);               
		   }
		   
			map.put(key,tempList);
			
		}
	
	
	public boolean iterateThroughAndCheck(HashMap p, Integer a, Integer b) {
		Set s1 = p.entrySet();
		System.out.println(s1);

		
		Iterator itr = s1.iterator();
		
		while(itr.hasNext()) {
			
			//m1 points to each entry inside of the map interface
			//And each entry is composed of keys and values
			Map.Entry m1 = (Map.Entry) itr.next();
			System.out.println(m1.getKey() +  "....." + m1.getValue());
			
			System.out.println(".............");
			
			if(m1.getKey().equals(a)) {
				List<Integer> l = (List<Integer>) m1.getValue();
				
				Iterator i = l.iterator();
				
				while(i.hasNext()) {
					
					if(i.next().equals(b)) {
						System.out.println(b);
//						System.out.println(i.next());
						return true;
					}
				}
				
				//return true;

			}
		}		
		return false;
	}
	
	
public List<ClassGrades> getStudentsClassGrades(int theClassID) throws Exception {
		
		List<ClassGrades> studentsGrades = new ArrayList<>();
		
		Connection myCon = null;
		PreparedStatement myStm = null;
		ResultSet myRs = null;
//		
//		int class_ID = Integer.parseInt(classId);
		
		try {
		// Get a connection
		myCon = dataSource.getConnection();
		
		//create string to hold SQL query
		String sql = "select student_id, grade_1, grade_2, final_grade, final_letter_grade from classgradess1 where class_id = ?";
		
		//create statement
		myStm =  myCon.prepareStatement(sql);
		
		myStm.setInt(1, theClassID);

		//execute query
		myRs = myStm.executeQuery();
		
		// process result set
		while(myRs.next()) {
			//retrieve data from result set row
			int student_id = myRs.getInt("student_id");
			int grade1 = myRs.getInt("grade_1");
			int grade2 = myRs.getInt("grade_2");
			int finalgrade = myRs.getInt("final_grade");
			String finallettergrade = myRs.getString("final_letter_grade");

						
			//create new student object
			ClassGrades stud = new ClassGrades(student_id, grade1, grade2, finalgrade, finallettergrade);
			
			//add it to the list of students
			studentsGrades.add(stud);
		}
		
		}finally {
			//close JDBC objects
			close(myCon, myStm, myRs);
			
		}
		return studentsGrades;
		
	}

}
