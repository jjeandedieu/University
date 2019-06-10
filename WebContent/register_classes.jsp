<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title>Register Classes</title>

<jsp:include page="header.jsp" />
<% 
	String classFull = (String)request.getAttribute("CLASS_FULL");

%>

<body>
<div class="nav">

</div>
  <!-- Welcome Section -->
  <section id="dashboard">
    <div class="container">
    
<%

	String theclassFull = (String)request.getAttribute("CLASS_FULL");

	String classExist = (String) request.getAttribute("CLASS_EXIST");    
	
  		if(classExist == "exist"){
  			out.print("<div id='regis_success' class='card bg-danger text-white' align='center'>"+
   				      "<div class='card-body'>You have already registered for this class</div>" +
  				      "</div>");
  			
  			out.print("<br>");
  			
  		}
  		if(classExist == "notexist"){
  			out.print("<div id='regis_success' class='card bg-success text-white' align='center'>"+
				      "<div class='card-body'>You have successfully registered for the class</div>" +
		      "</div>");
	
	out.print("<br>");
  			
  		} 	
  		
  		if(theclassFull == "true"){
  			out.print("<div id='regis_success' class='card bg-warning text-white' align='center'>"+
				      "<div class='card-body'>The Class You Are trying to register for is Currently full</div>" +
		      "</div>");
	
	out.print("<br>");
  			
  		} 
%>

      <h3 class="text-center text-uppercase text-secondary mb-0">Register for Classes Below</h3>
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
          <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
    	
    	<c:set var="tempStudent" value="${C_STUDENT}"/>
      	<c:set var="tempClassess" value="${STUDENTS_CLASSES}"/>
      	
    	<input type="hidden" name="studentId" value="${tempStudent.id}" />
    	<input type="hidden" name="classId" value="${tempClasses.id}" />
    	
	    	<table align="center" cellpadding="12">
	   			   <tr>
		    			<td><b>Class Id</b></td>
		    			<td><b>Class Name</b></td>
		    			<td><b>Time</b></td>
		    			<td><b>Professor</b></td>
		    			<td><b>Class Level</b></td>
		    			<td><b>Seats Available</b></td>
		    			<td><b></b></td>
		    		</tr>
	    		<c:forEach var="tempClasses" items="${STUDENTS_CLASSES}">
	    			<c:set var="tempStudent" value="${C_STUDENT}"/>
	    			
	    			<!--Set up a link for each student-->
					<c:url var="tempLink" value="UniClassesController">
						<c:param name="command" value="REGISTER" />
						<c:param name="studentId" value="${tempStudent.id}"/>
						<c:param name="classId" value="${tempClasses.id}"/>
					</c:url>
	    			
	    		    <c:if test="${not empty tempClasses}">
		    			<tr>
		    				<td>${tempClasses.id }</td>
		    				<td>${tempClasses.className }</td>
		    				<td>${tempClasses.classTime }</td>
		    				<td>
								
	    					<c:forEach var="tempTeachers" items="${TEACHERS}">
	    						<c:set var="profID" value="${tempClasses.professorId }"/>
	    						<c:set var="teachID" value="${tempTeachers.id }" />
	    						<c:if test="${profID == teachID }">
	    							${tempTeachers.firstName }
	    							&nbsp;
	    							${tempTeachers.lastName }	    							
	    						</c:if>
	    					</c:forEach>

							</td>
		    				<td>${tempClasses.classLevel }</td>
		    				<td>${tempClasses.seatsAvailable }</td>
		    				<td>
								<a id="register" href="${tempLink}" class="btn btn-info" role="button">Register</a>
								
							</td>
		    			</tr>
		    			
		    		</c:if>
		    	</c:forEach>
	    	</table>
    	
        </div>
      </div>
    </div>
  </section>
  

</body>
<jsp:include page="footer.jsp" />