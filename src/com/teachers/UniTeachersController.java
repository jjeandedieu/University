package com.teachers;

import java.io.IOException;
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

import com.StudentsAndClasses.StudentsAndClassesDbUtil;
import com.grades.ClassGradesDBUtil;
import com.students.Students;
import com.students.StudentsDbUtil;
import com.students.UniStudentsControllerServlet;
import com.uniclasses.UniClasses;
import com.uniclasses.UniClassesDbUtil;
import com.grades.*;
/**
 * Servlet implementation class UniTeachersController
 */
@WebServlet("/UniTeachersController")
public class UniTeachersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TeachersDBUtil TeachersDbUtil;
	private StudentsAndClassesDbUtil studentsClasses;
	private UniClassesDbUtil  UniClass;
	private StudentsDbUtil Unistud;
	private ClassGradesDBUtil ClassGrades;
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
			TeachersDbUtil = new TeachersDBUtil(dataSource);
			studentsClasses = new StudentsAndClassesDbUtil(dataSource);
			UniClass = new UniClassesDbUtil(dataSource);
			Unistud = new StudentsDbUtil(dataSource);
			ClassGrades = new ClassGradesDBUtil(dataSource);
		}
		catch(Exception exc) {
			throw new ServletException(exc);
		}
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UniTeachersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read the "command" parameter
		String theCommand = request.getParameter("command");
		
		//if the command is missing, then default to listing Studentss
		if(theCommand == null) {
			theCommand = "LIST";
		}
		
		//route to the apropriate method
		try {
		switch (theCommand) {
		case "LIST":
			listStudent(request, response);
			break;
		case "ADD":
			addTeacher(request, response);
			break;
		case "UPDATE":
			updateTeacher(request, response);
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
			dropTeachersClass(request, response);
			break;
		case "POPULATETEACHINFO":
			populateTeachInfo(request, response);
			break;	
		case "LISTEACHCLASSES":
			listClassesMyClasses(request, response);
			break;	
		case "SUBMITGRADES":
			submitGrades(request, response);
			break;		
		case "SUBMITCLASSGRADES":
			submitClassGrades(request, response);
			break;	
		case "SUBMITFINALCLASSGRADES":
			submitFinalClassGrades(request, response);
			break;	
		default:
			listStudent(request, response);
		}
		
		}
		catch(Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void submitFinalClassGrades(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();		
		
		int teacherID = Integer.parseInt(request.getParameter("teachId"));
		
		int classID = Integer.parseInt(request.getParameter("classId"));
		int studentID = Integer.parseInt(request.getParameter("studentId"));
		String className = request.getParameter("className");
		
		String grade1 = request.getParameter("grade1");
		String grade2 = request.getParameter("grade2");
		String finalgrade = request.getParameter("finalgrade");
		String finallettergrade = request.getParameter("finallettergrade");
		
		int grade_1 = -1, grade_2 = -1, final_grade = -1;
		String final_letter_grade = "z";
		
		if(grade1 != null || grade2 != null || finalgrade != null || finallettergrade != null) {
			
			if(grade1 != null) {
				grade_1 = Integer.parseInt(grade1);
			}else if(grade2 != null){
				grade_2 = Integer.parseInt(grade2);
			}else if(finalgrade != null) {
				final_grade = Integer.parseInt(finalgrade);
			}
			else if(finallettergrade != null) {
				final_letter_grade = finallettergrade;
			}
			
			ClassGrades theClassGrade = new ClassGrades(studentID, teacherID, classID, grade_1, grade_2, final_grade, final_letter_grade);
			

			ClassGrades.addStudentsGrade(theClassGrade);

		}
		
		List<Students> listStudent = Unistud.getStudentsClass(Integer.toString(classID));
		
		//Get student classes
		List<ClassGrades> studentsGrades = ClassGrades.getStudentsClassGrades(classID);
		
		request.setAttribute("CLASS_STUDENTS", listStudent);
		request.setAttribute("CLASS_NAME", className);
		request.setAttribute("STUDENT_GRADES", studentsGrades);
      
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/submit_class_grades.jsp");
		
		dispatcher.forward(request, response);

		//send to JSP page (view)
		//submitClassGrades(request, response);

	}

	private void submitClassGrades(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String classID = request.getParameter("classId");
		String className = request.getParameter("className");
		
		List<Students> listStudent = Unistud.getStudentsClass(classID);
		//List<ClassGrades> studentsGrades = ClassGrades.getStudentsClassGrades();

		
		request.setAttribute("CLASS_STUDENTS", listStudent);
		request.setAttribute("CLASS_NAME", className);
		//request.setAttribute("STUDENT_GRADES", studentsGrades);

      
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/submit_class_grades.jsp");
		
		dispatcher.forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	private void listClassesMyClasses(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        
        String teacher_ID = String.valueOf(session.getAttribute("TEACHER_ID"));
        
        //Get the list of classes associated with students from the studentsandclassesdbutil
        List<UniClasses> theTeachersClasses = UniClass.getTeachersClasses(teacher_ID);
		List<Teachers> Teachers = TeachersDbUtil.getTeachers();

//        System.out.println( theTeachersClasses);
        		
        //Send the class correpsonding to the Student to the JSP
        request.setAttribute("CURRENT_TEACH_CLASSES", theTeachersClasses);
        request.setAttribute("TEACHERS", Teachers);

        session.setAttribute("SCURRENT_TEACH_CLASSES", theTeachersClasses); 
        
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/my_classes.jsp");
		
		dispatcher.forward(request, response);

		
}
	
	private void submitGrades(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        
        String teacher_ID = String.valueOf(session.getAttribute("TEACHER_ID"));
        
        //Get the list of classes associated with students from the studentsandclassesdbutil
        List<UniClasses> theTeachersClasses = UniClass.getTeachersClasses(teacher_ID);
		List<Teachers> Teachers = TeachersDbUtil.getTeachers();

//        System.out.println( theTeachersClasses);
        		
        //Send the class correpsonding to the Student to the JSP
        request.setAttribute("CURRENT_TEACH_CLASSES", theTeachersClasses);
        request.setAttribute("TEACHERS", Teachers);

        session.setAttribute("SCURRENT_TEACH_CLASSES", theTeachersClasses); 
        
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/submit_grades.jsp");
		
		dispatcher.forward(request, response);

		
}

	private void populateTeachInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        
        Object stud = session.getAttribute("C_TEACHER");
        session.setAttribute("C_TEACHER", stud);
        
//		send to jsp page: update-Students-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/personal_info_teacher.jsp");
		
		dispatcher.forward(request, response);

	}
	
	private void repopulateTeachInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String update = "success";
		
		String teacherId = request.getParameter("teacherId");
		
		Teachers theTeacher = TeachersDbUtil.getTeachers(teacherId);
		
		request.setAttribute("C_TEACHER", theTeacher);
		request.setAttribute("updation", update);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/personal_info_teacher.jsp");
		
		dispatcher.forward(request, response);
		
	
	}

	private void dropTeachersClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        //Get the student id from the session
        String teacher_ID = String.valueOf(session.getAttribute("TEACHER_ID"));
        
		if(request.getParameter("classId") != null && request.getParameter("classId") != "") {
			String classId = request.getParameter("classId");	
			
			//delete class from student class list
			studentsClasses.dropClassTeacher(teacher_ID, classId);
			
			//increase the number of seats in that class by 1
			//UniClass.increaseSeatsAvailable(classId);
			
			listClassesMyClasses(request, response);
			
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
		
		//create the session if not exist already
        HttpSession session = request.getSession();
        
        //create user variable to hold bollean exist
        boolean user_exist = false;
		String username = request.getParameter("username");
		String password =request.getParameter("password");
        String sorry = "";

		
		//If the site is forwarded to dashboard, use the username associated with the session
        if(request.getParameter("username")== null && request.getParameter("password") == null) {
    		username = (String) session.getAttribute("USERNAMEX");
    		password = (String) session.getAttribute("PASSWORDX");


        }
        

		//get teachers from db util
		List<Teachers> Teachers = TeachersDbUtil.getTeachers();

		//Check if teacher and username is in the database
		 for(Teachers d : Teachers){

		        if((d.getUsername() != null && d.getUsername().contains(username)) 
		        		&& (d.getPassword() != null && d.getPassword().contains(password))) {
		        	//Display Students information on the Dashboard
		            int teacherId = d.getId();
		            //Store the student object in the session
		            session.setAttribute("C_TEACHER", d);
		            session.setAttribute("TEACHER_ID", teacherId);
		            
		            session.setAttribute("USERNAMEX", username);
		            session.setAttribute("PASSWORDX", password);
		            
		            //String teacher_ID = String.valueOf(session.getAttribute("TEACHER_ID"));
		            
		            //Get the list of classes associated with students from the studentsandclassesdbutil
		           // List<UniClasses> theStudentsClasses = UniClass.getStudentClasses(teacher_ID);
		            
		           // System.out.println( theStudentsClasses);
		            		
		            //Send the class correpsonding to the Student to the JSP
		            //request.setAttribute("CURRENT_STUD_CLASSES", theStudentsClasses);
		            //.setAttribute("SCURRENT_STUD_CLASSES", theStudentsClasses); 
		            
		            user_exist = true;
		    		//send to JSP page (view)
		    		RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard_teacher.jsp");
		    		
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
		List<Teachers> Teachers = TeachersDbUtil.getTeachers();
		
		//ArrayList to hold students found
		List<Teachers> studentsFound = new ArrayList<>();
		
		 for(Teachers d : Teachers){
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
		Teachers Student = new Teachers(id, firstName, lastName, email, phoneNumber, address, grade);
		
		//perform delete on database
		//StudentsDbUtil.updateStudents(id, firstName, lastName, email);
		TeachersDbUtil.deleteTeacher(Student);
		
		//send them back to the "list Studentss" page
		listStudent(request, response);		
	}

	private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{		

		
		//Read Students info from the form data
		int id = Integer.parseInt(request.getParameter("teacherId"));
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phone");
		String address = request.getParameter("address");
		String password = request.getParameter("password");

		//create a new Students object
		Teachers theTeacher = new Teachers(id, firstName, lastName, email, phoneNumber, address, password);
		
		System.out.println(theTeacher);
		//perform update on database

		TeachersDbUtil.updateTeacher(theTeacher);
		
		repopulateTeachInfo(request, response);
		
	}

	private void addTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("username_error", "notexist");
		request.setAttribute("password_error", "notexist");
		boolean user_exist = false;
		
		//get Students from db util
		List<Teachers> Teachers = TeachersDbUtil.getTeachers();

		 for(Teachers d : Teachers){

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
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String seniority = request.getParameter("seniority");
			
			//create a new Students object
			Teachers Student = new Teachers(firstName, lastName, email, phoneNumber, address, seniority, username, password);
			
			//add the Students to the database
			TeachersDbUtil.addTeachers(Student);
			
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
		List<Teachers> Teachers = TeachersDbUtil.getTeachers();
		
		//System.out.println(Students);
		//add Studentss to the request
		request.setAttribute("Teachers_LIST", Teachers);
		
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/register_teacher.jsp");
		
		dispatcher.forward(request, response);
	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		//get Students from db util
		List<Teachers> Teachers = TeachersDbUtil.getTeachers();
		
		//System.out.println(Students);
		//add Students to the request
		request.setAttribute("Students_LIST", Teachers);
		
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
