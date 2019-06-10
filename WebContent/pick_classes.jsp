<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    <%@ page import="java.util.*, com.teachers.*" %>

<title>Pick Classes</title>

<jsp:include page="header_teacher.jsp" />

<% 
	String classFull = (String)request.getAttribute("CLASS_FULL");

%>

<body>
<div class="nav">

</div>
  <!-- Welcome Section -->
  <section id="pick_classes">
    <div class="container">
    
<%

	String theclassPicked = (String)request.getAttribute("PICKED");

	String classAssigned = (String) request.getAttribute("ASSIGNED");    
	
	String success = (String) request.getAttribute("SUCCESS");    
	
  		if(theclassPicked == "true"){
  			out.print("<div id='regis_success' class='card bg-danger text-white' align='center'>"+
   				      "<div class='card-body'>You have already picked this class</div>" +
  				      "</div>");
  			
  			out.print("<br>");
  			
  		}
  		if(classAssigned == "true"){
  			out.print("<div id='regis_success' class='card bg-warning text-white' align='center'>"+
				      "<div class='card-body'>This class has already been assigned to another professor</div>" +
		      "</div>");
	
	out.print("<br>");
  			
  		} 	
  		
  		if(success == "true"){
  			out.print("<div id='regis_success' class='card bg-success text-white' align='center'>"+
				      "<div class='card-body'>You have successfully registered as the professor for the class</div>" +
		      "</div>");
	
	out.print("<br>");
  			
  		} 
%>

      <h3 class="text-center text-uppercase text-secondary mb-0">Pick Classess Below</h3>
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
          <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
    	
    	<c:set var="tempTeacher" value="${C_TEACHER}"/>
      	<c:set var="tempClassess" value="${CLASSES}"/>
      	

    	
	    	<table align="center" cellpadding="12">
	   			   <tr>
		    			<td><b>Class Id</b></td>
		    			<td><b>Class Name</b></td>
		    			<td><b>Time</b></td>
		    			<!--<td><b>Professor</b></td>-->
		    			<td><b>Class Level</b></td>
		    			<td><b>Seats Available</b></td>
		    			<td><b>Class Professor</b></td>
		    			<td><b></b></td>
		    		</tr>
	    		<c:forEach var="tempClasses" items="${CLASSES}">
	    			<c:set var="tempTeacher" value="${C_TEACHER}"/>
	    			<c:set var="teachers" value="${TEACHERS}"/>
	    			
	    			<!--Set up a link for each teacher-->
					<c:url var="tempLink" value="UniClassesController">
						<c:param name="command" value="REGISTERTEACHER" />
						<c:param name="teacherId" value="${tempTeacher.id}"/>
						<c:param name="classId" value="${tempClasses.id}"/>
					</c:url>
	    				    		    
	    		    <c:if test="${not empty tempClasses}">
		    			<tr>
		    				<td>${tempClasses.id }</td>
		    				<td>${tempClasses.className }</td>
		    				<td>${tempClasses.classTime }</td>
		    				<!--<td>${tempClasses.professor }</td>-->
		    				<td>${tempClasses.classLevel }</td>
		    				<td>${tempClasses.seatsAvailable }</td>
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
		    				<td>
								<a id="register" href="${tempLink}" class="btn btn-info" role="button">Pick</a>
								
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