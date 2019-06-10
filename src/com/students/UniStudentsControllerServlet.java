package com.students;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.StudentsAndClasses.StudentsAndClasses;
import com.StudentsAndClasses.StudentsAndClassesDbUtil;
import com.teachers.Teachers;
import com.teachers.TeachersDBUtil;
import com.uniclasses.UniClasses;
import com.uniclasses.UniClassesDbUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Servlet implementation class UniStudentssControllerServlet
 */
@WebServlet("/UniStudentsControllerServlet")
public class UniStudentsControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StudentsDbUtil StudentsDbUtil;
	private StudentsAndClassesDbUtil studentsClasses;
	private UniClassesDbUtil  UniClass;
	private TeachersDBUtil TeachersBdUtil;
	private String logout = "";
	
	@Resource(name="jdbc/university_students")
	private DataSource dataSource;
	
	// Called by the Java EE server or TOMCAT
	// When the server is first initialized
	@Override
	public void init() throws ServletException {
		super.init();
		//Things we would normally do with a constructor
		//can be done in the init method
		
		//create our Students db utill...and pass in the conn pool / datasource
		try {
			StudentsDbUtil = new StudentsDbUtil(dataSource);
			studentsClasses = new StudentsAndClassesDbUtil(dataSource);
			UniClass = new UniClassesDbUtil(dataSource);
			TeachersBdUtil = new TeachersDBUtil(dataSource);
		}
		catch(Exception exc) {
			throw new ServletException(exc);
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read the "command" parameter
		String theCommand = request.getParameter("command");
		
		//if the command is missing, then default to listing Students
		if(theCommand == null) {
			theCommand = "LIST";
		}
		
		//route to the appropriate method
		try {
		switch (theCommand) {
		case "LIST":
			listStudent(request, response);
			break;
		case "ADD":
			addStudent(request, response);
			break;
		case "UPDATE":
			updateStudent(request, response);
			break;
		case "DELETE":
			deleteStudent(request, response);
			break;
		case "SEARCH":
			searchStudent(request, response);
			break;
		case "LOGIN":
			dashboard(request, response);
			break;
		case "LOGOUT":
			logout(request, response);
			break;
		case "DASHBOARD":
			dashboard(request, response);
			break;
		case "DROPCLASS":
			dropStudentClass(request, response);
			break;
		case "POPULATESTUDINFO":
			populateStudInfo(request, response);
			break;	
		case "LISTSTUDCLASSES":
			listClasses(request, response);
			break;
		case "SEARCHING":
			searchingStudent(request, response);
			break;
		default:
			listStudent(request, response);
		}
		
		}
		catch(Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void listClasses(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        
        String student_ID = String.valueOf(session.getAttribute("STUD_ID"));
        
        //Get the list of classes associated with students from the studentsandclassesdbutil
        List<UniClasses> theStudentsClasses = UniClass.getStudentClasses(student_ID);
        
        System.out.println( theStudentsClasses);
        		
        //Send the class correpsonding to the Student to the JSP
        request.setAttribute("CURRENT_STUD_CLASSES", theStudentsClasses);
        session.setAttribute("SCURRENT_STUD_CLASSES", theStudentsClasses); 
        
		List<Teachers> teachers = TeachersBdUtil.getTeachers();

		session.setAttribute("TEACHERS", teachers);
        
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/class_schedules.jsp");
		
		dispatcher.forward(request, response);

		        
		        
		    
		
}

	private void populateStudInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        
        Object stud = session.getAttribute("C_STUDENT");
        session.setAttribute("C_STUDENT", stud);
        
//		send to jsp page: update-Students-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/personal_info.jsp");
		
		dispatcher.forward(request, response);

	}
	
	private void repopulateStudInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String update = "success";
		
		String studeId = request.getParameter("studentId");
		
		Students theStudent = StudentsDbUtil.getStudents(studeId);
		
		request.setAttribute("C_STUDENT", theStudent);
		request.setAttribute("updation", update);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/personal_info.jsp");
		
		dispatcher.forward(request, response);
		
	
	}

	private void dropStudentClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        //Get the student id from the session
        String student_ID = String.valueOf(session.getAttribute("STUD_ID"));
        
		if(request.getParameter("classId") != null && request.getParameter("classId") != "") {
			String classId = request.getParameter("classId");	
			
			//delete class from student class list
			studentsClasses.dropClass(student_ID, classId);
			
			//increase the number of seats in that class by 1
			UniClass.increaseSeatsAvailable(classId);
			
			listClasses(request, response);
			
		}		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logout = "yes";
		
        HttpSession session = request.getSession(false); 
        
        session.invalidate();  	
        
        request.setAttribute("LOGOUT_Y", logout);
        
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		
		dispatcher.forward(request, response);
	}

	private void dashboard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
        HttpSession session = request.getSession();

        boolean user_exist = false;
		String username = request.getParameter("username");
		String password =request.getParameter("password");
        String sorry = "";

		
		//If the site is forwarded to dashboard, use the username associated with the session
        if(request.getParameter("username")== null && request.getParameter("password") == null) {
    		username = (String) session.getAttribute("USERNAMEX");
    		password = (String) session.getAttribute("PASSWORDX");


        }
        

		//get Students from db util
		List<Students> Students = StudentsDbUtil.getStudents();

		//Check if students and username is in the database
		 for(Students d : Students){

		        if((d.getUsername() != null && d.getUsername().contains(username)) 
		        		&& (d.getPassword() != null && d.getPassword().contains(password))) {
		        	//Display Students information on the Dashboard
		            int studId = d.getId();
		            //Store the student object in the session
		            session.setAttribute("C_STUDENT", d);
		            session.setAttribute("STUD_ID", studId);
		            
		            session.setAttribute("USERNAMEX", username);
		            session.setAttribute("PASSWORDX", password);
		            
		            String student_ID = String.valueOf(session.getAttribute("STUD_ID"));
		            
		            //Get the list of classes associated with students from the studentsandclassesdbutil
		            List<UniClasses> theStudentsClasses = UniClass.getStudentClasses(student_ID);
		            
		            System.out.println( theStudentsClasses);
		            		
		            //Send the class correpsonding to the Student to the JSP
		            request.setAttribute("CURRENT_STUD_CLASSES", theStudentsClasses);
		            session.setAttribute("SCURRENT_STUD_CLASSES", theStudentsClasses); 
		            
		            user_exist = true;
		    		//send to JSP page (view)
		    		RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
		    		
		    		dispatcher.forward(request, response);

		        }
		        
		    }
		 
		 //If the combination of username and password not in the databse, then
		 //user does not exist, wrong username or password
		 if(!user_exist) {
        	sorry = "wrong";
	   		 
            //Store the student object in the session
            request.setAttribute("C_SORRY", sorry);
            
    		//send to JSP page (view)
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
    		
    		dispatcher.forward(request, response);
		 }

	}

	private void searchStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Read student name passed from the parameter
		String studentName = request.getParameter("firstname_search");
		
		//get Students from db util
		List<Students> Students = StudentsDbUtil.getStudents();
		
		//ArrayList to hold students found
		List<Students> studentsFound = new ArrayList<>();
		
		 for(Students d : Students){
		        if(d.getFirstName() != null && d.getFirstName().toLowerCase().contains(studentName.toLowerCase())) {
		        	//Add Students found to the studentsFound ArrayList
		        	studentsFound.add(d);	        	
		        }
		    }
		
		// String found = " Not String found ";
		 int size = studentsFound.size();
		 
         //send  firstname, lastname... to the jsp table for display
		 request.setAttribute("STUDENTS_FOUND", studentsFound);
		// request.setAttribute("FOUND", found);
		 request.setAttribute("SIZE", size);
		 
		 //System.out.println(size);
		 
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void searchingStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Read student name passed from the parameter
		String name = request.getParameter("val");
		
		//get Students from db util
		List<Students> Students = StudentsDbUtil.getStudentsAjax(name);
		
		//String json = new Gson().toJson(Students);
		
		// Use this builder to construct a Gson instance when you need to set configuration options other than the default.
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		Gson gson = gsonBuilder.create();
		
		String JSONObject = gson.toJson(Students);
		
		log("\nConverted JSONObject ==> " + JSONObject);
		
		response.setContentType("application/json");
		response.getWriter().write(JSONObject);
		
//		//ArrayList to hold students found
//		List<Students> studentsFound = new ArrayList<>();
//		
//		 for(Students d : Students){
//		        if(d.getFirstName() != null && d.getFirstName().toLowerCase().contains(studentName.toLowerCase())) {
//		        	//Add Students found to the studentsFound ArrayList
//		        	studentsFound.add(d);	        	
//		        }
//		    } 
		
//		// String found = " Not String found ";
//		 int size = Students.size();
//		 
//         //send  firstname, lastname... to the jsp table for display
//		 request.setAttribute("STUDENTS_FOUND", Students);
//		// request.setAttribute("FOUND", found);
//		 request.setAttribute("SIZE", size);
//		 
//		 //System.out.println(size);
//		 
//		//send to JSP page (view)
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
//		
//		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Read Students info from the form data
		int id = Integer.parseInt(request.getParameter("StudentsId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phone");
		String address = request.getParameter("address");
		String grade = request.getParameter("grade");
		
		//create a new Students object
		Students Student = new Students(id, firstName, lastName, email, phoneNumber, address, grade);
		
		//perform delete on database
		//StudentsDbUtil.updateStudents(id, firstName, lastName, email);
		StudentsDbUtil.deleteStudent(Student);
		
		//send them back to the "list Studentss" page
		listStudent(request, response);		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{		

		
		//Read Students info from the form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phone");
		String address = request.getParameter("address");
		String grade = request.getParameter("grade");
		String password = request.getParameter("password");

		//create a new Students object
		Students theStudent = new Students(id, firstName, lastName, email, phoneNumber, address, grade, password);
		
		System.out.println(theStudent);
		//perform update on database

		StudentsDbUtil.updateStudent(theStudent);
		
		repopulateStudInfo(request, response);
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("username_error", "notexist");
		request.setAttribute("password_error", "notexist");
		boolean user_exist = false;
		
		//get Students from db util
		List<Students> Students = StudentsDbUtil.getStudents();

		 for(Students d : Students){

		        	if(d.getUsername() != null && d.getUsername().contains(request.getParameter("username"))) {
			        	//setAttributesToNull(request, response);
						user_exist = true;
			    		break;
		        	}

		        }
		        	
		 //Display Students information on the Dashboard
		if(user_exist) {
			request.setAttribute("username_error", "exist");
    		backToRegister(request, response);
			
		}else {
			
		if(request.getParameter("firstname") == "" || request.getParameter("lastname") == "" 
				||request.getParameter("email") == "" || request.getParameter("phone") == "" 
				|| request.getParameter("address") == "" || request.getParameter("grade") == ""
				|| request.getParameter("username") == "" || request.getParameter("password") == "") {
			
			//Reset all attributes to null
        	setAttributesToNull(request, response);

			//send back to the main page(the Students list)
    		backToRegister(request, response);
		
		}else {
			//read the Students info from data
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phone");
			String address = request.getParameter("address");
			String grade = request.getParameter("grade");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			//create a new Students object
			Students Student = new Students(firstName, lastName, email, phoneNumber, address, grade, username, password);
			
			//add the Students to the database
			StudentsDbUtil.addStudents(Student);
			
			//Reset all attributes to null
	    	setAttributesToNull(request, response);
	    	
	    	//Registration successful
			request.setAttribute("Registration", "success");
			
			//send back to the main page(the Students list)
    		backToRegister(request, response);
		}
		
		}
		

		
		
	}

	private void backToRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//get Students from db util
		List<Students> Students = StudentsDbUtil.getStudents();
		
		//System.out.println(Students);
		//add Studentss to the request
		request.setAttribute("Students_LIST", Students);
		
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/register_student.jsp");
		
		dispatcher.forward(request, response);
	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		//get Students from db util
		List<Students> Students = StudentsDbUtil.getStudents();
		
		//System.out.println(Students);
		//add Students to the request
		request.setAttribute("Students_LIST", Students);
		
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void setAttributesToNull(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("firstname", null);
		request.setAttribute("lastname", null);
		request.setAttribute("email", null);
		request.setAttribute("phone", null);
		request.setAttribute("address", null);
		request.setAttribute("grade", null);
		request.setAttribute("username", null);
		request.setAttribute("password", null);
	}

}


