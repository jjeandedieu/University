<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    <%@ page import="java.util.*" %>

<title>Submit Grades</title>

<jsp:include page="header_teacher.jsp" />

<body id="page-top">

  <!-- Header -->
 <!--
<header class="masthead bg-primary text-white text-center">

</header>
-->
<div class="nav">

</div>
    <!-- Welcome Section -->
  <section id="dashboard">
    <div class="container">
      <h2 class="text-center text-uppercase text-secondary mb-0">Submit Grades</h2>
      <hr class="star-dark mb-5">
    
    <c:set var="name" value="${C_TEACHER}"/>
      <h3 class="text-center text-uppercase text-secondary mb-0"><span style="color:#17a2b8">Hi <span style="color:#2c3e50">"${name.firstName}"</span>, below are your
      classes</span></h3>
  
      <!-- Contact Section -->
   <section id="classes">
    <div class="container">
    <c:set var="TeacherName" value="${C_TEACHER}"/>
      <h3 class="text-center text-uppercase text-secondary mb-0">Add Grades and Click Submit</h3>
      <div class="row">
        <div class="col-lg-10 mx-auto">
          <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
          <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
    	            
           
    	  <table align="center" cellpadding="12">
    	  		<c:set var="className" value="${CLASS_NAME}"></c:set>
   		   		<tr>
   		   			<td colspan="8" style="text-align:center"><h3>${className}</h3></td>
   		   		</tr>
   		   		<tr>
	    			<td><b>Student ID</b></td>
	    			<td><b>First Name</b></td>
	    			<td><b>Last Name</b></td>
	    			
	    			<td><b>Email</b></td>
	    			<td><b>Grade 1</b></td>
	    			<td><b>Grade 2</b></td>
	    			<td><b>Final</b></td>
	    			<td><b>Letter Grade</b></td>
	    		</tr>
	    		<c:forEach var="tempStudent" items="${CLASS_STUDENTS}">
					<!--Display the list of classes-->
				
						<tr>	
							<td>${tempStudent.id}</td>
							<td>${tempStudent.firstName}</td>
							<td>${tempStudent.lastName}</td>
							<td>${tempStudent.email}</td>
							<td>
								
								<form action="UniTeachersController" method="GET">
								
								<%
									int grade1Count = 1;
								%>
								<input type="hidden" name="command" id="thecommand" value="SUBMITFINALCLASSGRADES">
								<input type="hidden" name="className" value="${className}">
								<input type="hidden" name="classId" value="${tempStudent.classId}">
								<input type="hidden" name="studentId" value="${tempStudent.id}">
								<input type="hidden" name="teachId" value="${TeacherName.id}">
								
								<c:forEach var="studGrades" items="${STUDENT_GRADES}">
									<c:choose>
										<c:when test="${tempStudent.id == studGrades.studentId}">
											<%
												grade1Count++;
											%>	
											<input class="form-control" id="grade1" name="grade1" type="number" value="${studGrades.grade1}">
										</c:when>
										
										<c:otherwise>
										</c:otherwise>
									</c:choose>		
								</c:forEach>	
								<%
									if(grade1Count == 1){
										out.println("<input class='form-control' id='grade1' name='grade1' type='number'>");
									}
								
								%>			

								<!--<input class="form-control" id="grade1" name="grade1" type="number">  -->
								<br>	
								
								<input type="submit" class="btn btn-primary btn-md" id="submit_class" value="Submit" />
								
								
								</form>							
							</td>
							
							<td>
								<form action="UniTeachersController" method="GET">
								<%
									int grade2Count = 1;
								%>
								
								<input type="hidden" name="command" id="thecommand" value="SUBMITFINALCLASSGRADES">
								<input type="hidden" name="className" value="${className}">
								<input type="hidden" name="classId" value="${tempStudent.classId}">
								<input type="hidden" name="studentId" value="${tempStudent.id}">
								<input type="hidden" name="teachId" value="${TeacherName.id}">
								
																
								<c:forEach var="studGrades" items="${STUDENT_GRADES}">
									<c:choose>
										<c:when test="${tempStudent.id == studGrades.studentId}">
											<%
											grade2Count++;
											%>	
											<input class="form-control" id="grade2" name="grade2" type="number" value="${studGrades.grade2}">
										</c:when>
										
										<c:otherwise>
										</c:otherwise>
									</c:choose>		
								</c:forEach>
								<%
									if(grade2Count == 1){
										out.println("<input class='form-control' id='grade2' name='grade2' type='number'>");
									}
								
								%>	
								
								<!-- <input class="form-control" id="grade2" name="grade2" type="number"> -->
								<br>
								<input type="submit" class="btn btn-primary btn-md" id="submit_class" value="Submit" />
								
								</form>							
							</td>
							<td>
								<form action="UniTeachersController" method="GET">
								
								<%
									int finalGradeCount = 1;
								%>
								
								<input type="hidden" name="command" id="thecommand" value="SUBMITFINALCLASSGRADES">
								<input type="hidden" name="className" value="${className}">
								<input type="hidden" name="classId" value="${tempStudent.classId}">
								<input type="hidden" name="studentId" value="${tempStudent.id}">
								<input type="hidden" name="teachId" value="${TeacherName.id}">
								
								<c:forEach var="studGrades" items="${STUDENT_GRADES}">
									<c:choose>
										<c:when test="${tempStudent.id == studGrades.studentId}">
											<%
												finalGradeCount++;
											%>											
										<input class="form-control" id="finalgrade" name="finalgrade" type="number" value="${studGrades.finalGrade}">
										</c:when>
										
										<c:otherwise>
										</c:otherwise>
									</c:choose>		
								</c:forEach>
								<%
									if(finalGradeCount == 1){
										out.println("<input class='form-control' id='finalgrade' name='finalgrade' type='number'>");
									}
								
								%>
								
								<!-- <input class="form-control" id="final_grade" name="finalgrade" type="number"> -->
								<br>
								<input type="submit" class="btn btn-primary btn-md" id="submit_class" value="Submit" />
								
								</form>
							</td>
							
							<td>
								<form action="UniTeachersController" method="GET">
								<%
									int finalLetterCount = 1;
								%>
								
								<input type="hidden" name="command" id="thecommand" value="SUBMITFINALCLASSGRADES">
								<input type="hidden" name="className" value="${className}">
								<input type="hidden" name="classId" value="${tempStudent.classId}">
								<input type="hidden" name="studentId" value="${tempStudent.id}">
								<input type="hidden" name="teachId" value="${TeacherName.id}">
								
								<c:forEach var="studGrades" items="${STUDENT_GRADES}" varStatus="status">
									<c:choose>
										<c:when test="${tempStudent.id == studGrades.studentId}">
											<%
												finalLetterCount++;
											%>
											<input class="form-control" id="finallettergrade" name="finallettergrade" type="text" value="${studGrades.finalLetterGrade}">
										</c:when>
										
										<c:otherwise>
										
										</c:otherwise>
									</c:choose>	
								</c:forEach>
								<%
									if(finalLetterCount == 1){
										out.println("<input class='form-control' id='finallettergrade' name='finallettergrade' type='text'>");
									}
								
								%>
								 
								<!-- <input class="form-control" id="final_grade" name="finallettergrade" type="text"> -->
								<br>
								<input type="submit" class="btn btn-primary btn-md" id="submit_class" value="Submit" />
								
								</form>
							</td>
							<!-- <td>
								<a id="drop" href="${tempLink}" class="btn btn-info" role="button">Submit Final Grade</a>
									
								<!-- <input type="submit" class="btn btn-primary btn-xl" id="register" value="Submit Grades" />
								
							</td>-->

					</tr>
				</c:forEach>	
	    		
    	
    	</table>
    	
    		<div>
    	
    	</div>
    	</div>
    	</div>
    </section>
    </div>
  </section>
  

  <!-- Footer -->


</body>
	<script src="js/submit_class.js"></script>

<jsp:include page="footer.jsp" />