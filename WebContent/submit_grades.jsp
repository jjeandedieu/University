<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    <%@ page import="java.util.*" %>

<title>SUbmit Classes</title>

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
    <c:set var="name" value="${C_TEACHER}"/>
      <h3 class="text-center text-uppercase text-secondary mb-0">Pick a Class to Submit Grades</h3>
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
          <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
    	  <table align="center" cellpadding="12">
   		   			   <tr>
	    			<td><b>Class ID</b></td>
	    			<td><b>Class Name</b></td>
	    			<td><b>Class Day</b></td>
	    			<td><b>Class Time</b></td>
	    			<td><b>Class Level</b></td>
	    			<td><b>Seats Available</b></td>
	    			<td><b>Class Professor</b></td>
	    			<td><b>Submit Grades</b></td>
	    		</tr>
	    		<tr>
	    		<c:forEach var="tempClass" items="${CURRENT_TEACH_CLASSES}">
					<!--Display the list of classes-->
					<c:url var="tempLink" value="UniTeachersController">
						<c:param name="command" value="SUBMITCLASSGRADES" />
						<c:param name="classId" value="${tempClass.id}"/>
						<c:param name="className" value="${tempClass.className}"/>
						
					</c:url>
					
					<tr>
						<td>${tempClass.id}</td>
						<td>${tempClass.className}</td>
						<td>${tempClass.classDay}</td>
						<td>${tempClass.classTime}</</td>
						<td>${tempClass.classLevel}</</td>
						<td>${tempClass.seatsAvailable}</</td>
						<td>
														
	    					<c:forEach var="tempTeachers" items="${TEACHERS}">
	    						<c:set var="profID" value="${tempClass.professorId}"/>
	    						<c:set var="teachID" value="${tempTeachers.id }" />
	    						<c:if test="${profID == teachID }">
	    							${tempTeachers.firstName }
	    							&nbsp;
	    							${tempTeachers.lastName }	    							
	    						</c:if>
	    					</c:forEach>

						</td>
						<td>
							<a id="drop" href="${tempLink}" class="btn btn-info" role="button">Submit Grades</a>
								
						</td>

					</tr>
				</c:forEach>	
	    		
	    		</tr>
    	
    	</table>
    	</div>
    	</div>
    	</div>
    </section>
    </div>
  </section>
  

  <!-- Footer -->


</body>
<jsp:include page="footer.jsp" />