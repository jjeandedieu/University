package com.uniclasses;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.students.Students;
import com.students.StudentsDbUtil;
import com.teachers.Teachers;
import com.teachers.TeachersDBUtil;
import com.StudentsAndClasses.*;

/**
 * Servlet implementation class UniClassesController
 */
@WebServlet("/UniClassesController")
public class UniClassesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private StudentsDbUtil StudentsBdUtil;
	private TeachersDBUtil TeachersBdUtil;
	private UniClasses Uniclass;
	private UniClassesDbUtil UniClassesDBUtil;
	private StudentsAndClassesDbUtil studentNClasses;
	private String logout = "";
	
	@Resource(name="jdbc/university_students")
	private DataSource dataSource;
    
@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		//Things we would normally do with a constructor
		//can be done in the init method
		
		//create our Students db util...and pass in the conn pool / datasource
		try {
			UniClassesDBUtil = new UniClassesDbUtil(dataSource);
			StudentsBdUtil = new StudentsDbUtil(dataSource);
			studentNClasses = new StudentsAndClassesDbUtil(dataSource);
			TeachersBdUtil = new TeachersDBUtil(dataSource);
			Uniclass = new UniClasses();
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
		
		//if the command is missing, then default to listing Studentss
		if(theCommand == null) {
			theCommand = "LIST";
		}
		
		//route to the appropriate method
		try {
			switch (theCommand) {
			case "LIST":
				listClasses(request, response);
				break;
			case "LISTEACHCLASSES":
				listClassesTeacher(request, response);
				break;
			case "LISTTEACHER":
				listClassesTeacher(request, response);
				break;
			case "REGISTER":
				registerClasses(request, response);
				break;
			case "REGISTERTEACHER":
				registerClassesTeacher(request, response);
				break;
			case "LOGOUT":
				logout(request, response);
				break;
			default:
				listClasses(request, response);
			}
		
		}
		catch(Exception exc) {
			throw new ServletException(exc);
		}
	}

	//Insert the teacherid into the professor_id column of the classes table
	//Make sure to check first if the value in that column is null
	@SuppressWarnings("unused")
	private void registerClassesTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();

        String alreadyPicked = "";
        String alreadyAssigned = "";
        String succesful = "";
        
        //Get all the classes so that we can check if the professor field is null
        List<UniClasses> classes = UniClassesDBUtil.getClasses();
        
        //check if the classId is not null
        if(request.getParameter("classId") != null) {
        	
        	//check if the teacherId is not null
        	if(request.getParameter("teacherId") != null) {
        		
        		//convert the classid to integer
            	int classId = Integer.parseInt(request.getParameter("classId"));
            	
            	//go through the classes
            	for(UniClasses c: classes) {
            		
            		//GO inside if the class with the id is found
            		if(classId == c.getId()) {
            			
            			//convert int to integer so that we can compare to null
            			int prof_id = c.getProfessorId();
            			
        				//Getting the teacher id from the sessoin, could have gotten it from the request object
        				String teacher_ID = String.valueOf(session.getAttribute("TEACHER_ID"));
       				 
        				//convert the teacherid to int
        				int theTeacherId = Integer.parseInt(teacher_ID);
            			
            			//go through if the id is not null
            			if(prof_id == 0 || prof_id == theTeacherId) {
            				
            				//check if the current teacher id is not the same id already in the professor_id field
            				if(prof_id != theTeacherId) {
                				UniClassesDBUtil.setProfessorId(theTeacherId, classId);
                				succesful = "true";
            				}else {
            					alreadyPicked = "true";
            				}
            				 
            			}else {
            				alreadyAssigned = "true";
            			}
            		}
            	}
            		
        		
        	}
        }
		
		//request.setAttribute("C_TEACHER", theTeacher);
		request.setAttribute("PICKED", alreadyPicked);
		request.setAttribute("ASSIGNED", alreadyAssigned);
		request.setAttribute("SUCCESS", succesful);
		
		//GO to the list method
		listClassesTeacher(request, response);		
	}

	private void registerClasses(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        boolean class_exist = false;
        
        String classId;
        String studentId;
        String classExist = "";
        int cl_ID;

        //Get the student id from the session
        String student_ID = String.valueOf(session.getAttribute("STUD_ID"));
        
        //convert student id to int
//        int stud_ID = Integer.parseInt(student_ID);
        
      //Get Classes Associated with a student
        //Get the list of classes associated with students from the studentsandclassesdbutil
        List<UniClasses> theStudentsClasses = UniClassesDBUtil.getStudentClasses(student_ID);
        
        //Check if the classID even exist
        if(request.getParameter("classId") != null) {
        	
        	classId = request.getParameter("classId");
            //convert class id to int
            cl_ID = Integer.parseInt(classId);
            
    		
            for (UniClasses c: theStudentsClasses) {
            	
            	Integer cId = c.getId();
            	
            	if(cId != null && cId == cl_ID) {
            		class_exist = true;
            		classExist = "exist";
            	}
            }
        }

        //check if the studend ID is not null
        if(request.getParameter("studentId")!= null) {
        	studentId = request.getParameter("studentId");
        }else {
        	studentId = student_ID;
        }
		
		//Convert to string
//		int theStudentId = Integer.parseInt(request.getParameter("studentId"));	
//		int theClassId = Integer.parseInt(request.getParameter("classId"));	
		
		//Students theStudent = StudentsBdUtil.getStudents(studentId);
		Students theStudent = StudentsBdUtil.getStudents(studentId);
		String classfull = "false";
		
		if(request.getParameter("classId") != null && !class_exist) {
			classId = request.getParameter("classId");	
			
			UniClasses theClass = UniClassesDBUtil.getClass(classId);
			
			if(theClass.getSeatsAvailable() == 0) {
				classfull = "true";
			}else {
			
			studentNClasses.registerStudentClasses(theStudent, theClass);
			
			//call decrease number of seats function frmo UniClassDBUtil
			UniClassesDBUtil.decreaseSeatsAvailable(classId);

    		classExist = "notexist";
			
			}
			

		}
		
		request.setAttribute("C_STUDENT", theStudent);
		request.setAttribute("CLASS_EXIST", classExist);
		request.setAttribute("CLASS_FULL", classfull);
		
		//GO to the list method
		listClasses(request, response);

	}

	private void listClasses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//get Students from db util
		List<UniClasses> classes = UniClassesDBUtil.getClasses();

		System.out.println(classes);
		//add Studentss to the request
		request.setAttribute("STUDENTS_CLASSES", classes);
		
		List<Teachers> teachers = TeachersBdUtil.getTeachers();

		request.setAttribute("TEACHERS", teachers);
		
		//request.setAttribute("C_STUDENT", s);
		//request.setAttribute("STUDENT_CLASS", cId);
		
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/register_classes.jsp");
		
		dispatcher.forward(request, response);
		
	}
	
	private void listClassesTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//get Students from db util
		List<UniClasses> classes = UniClassesDBUtil.getClasses();
		List<Teachers> teachers = TeachersBdUtil.getTeachers();

		System.out.println(classes);
		//add Studentss to the request
		request.setAttribute("CLASSES", classes);
		request.setAttribute("TEACHERS", teachers);
		
		//request.setAttribute("C_STUDENT", s);
		//request.setAttribute("STUDENT_CLASS", cId);
		
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pick_classes.jsp");
		
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logout = "yes";
		
        HttpSession session = request.getSession(); 
        
        session.invalidate();  	
        
        request.setAttribute("LOGOUT_Y", logout);
        
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		
		dispatcher.forward(request, response);
	}
	
	

}
