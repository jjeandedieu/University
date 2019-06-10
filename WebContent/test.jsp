    		<c:set var="studentSize" value="${STUDENTS_FOUND_SIZE}"/>
    			<c:if test="${studentSize == 0}">
	    			<tr>
	    				<td>
	    					No Student found with that first name
	    				</td>
	    			</tr>
    			</c:if>
    		
    		    <c:if test="${not empty tempStudent}">
		    		    
		    		<tr>
		    			<td>First Name</td>
		    			<td>Last Name</td>
		    			<td>Email</td>
		    			<td>Phone Number</td>
		    			<td>Address</td>
		    			<td>Grade Level</td>
		    		</tr>
	    			<tr>
	    				<td>${tempStudent.firstName }</td>
	    				<td>${tempStudent.lastName }</td>
	    				<td>${tempStudent.email }</td>
	    				<td>${tempStudent.phoneNumber }</td>
	    				<td>${tempStudent.address }</td>
	    				<td>${tempStudent.grade }</td>
	    			</tr>
    			</c:if>
    			
    			
    			
    			
    			    		<c:set var="string" value="${FOUND }"/>
	    		<c:choose>
	    			<c:when test="${empty tempStudent}">
	    						    		    
			    		<tr>
			    			<td>First Name</td>
			    			<td>Last Name</td>
			    			<td>Email</td>
			    			<td>Phone Number</td>
			    			<td>Address</td>
			    			<td>Grade Level</td>
			    		</tr>
		    			<tr>
		    				<td>${tempStudent.firstName }</td>
		    				<td>${tempStudent.lastName }</td>
		    				<td>${tempStudent.email }</td>
		    				<td>${tempStudent.phoneNumber }</td>
		    				<td>${tempStudent.address }</td>
		    				<td>${tempStudent.grade }</td>
		    			</tr>
		    			
		    		</c:when>
		    			
		    		<c:otherwise>

		    			
		    		</c:otherwise>
	    			
	    		
	    		</c:choose>
    			
    			<tr>
    			<%
    			String message = "NO Student Found";
				out.println("<td>" + message + "</td>");

    			int size = (int)request.getAttribute("SIZE");
    			if(size == 0 ){
    				out.println("<td>" + message + "</td>");

    			}
    			
    			%>
    			</tr>
    			
    			    			<c:if test="${studentSize > 0}">
	    			<tr>
	    				<td>
	    					No Student found with that first name
	    				</td>
	    			</tr>
    			</c:if>
    			
<c:set var="wrongCreds" value="${C_SORRY}"/>
<c:if test="${empty wrongCreds}">
	<span onload="ErrorFunction()"></span>
</c:if>


<%
if(request.getAttribute("C_SORRY") != null){
	out.println("<h2 onload='errorFunction()'></h2>");
}
%>

<c:set var="wrongCreds" value="${C_SORRY}"/>
<c:if test="${not empty wrongCreds}">
	<h2>Wrong Creds</h2>
	<span onload="ErrorFunction()"></span>
</c:if>

<%
if(request.getAttribute("C_SORRY") != null){
	String n = "wrong Creds";
	out.println("<h2>"+ n + "</h2>");
}

if ((request.getAttribute("C_SORRY")) != null){
	String xmessage = "No Student Found With the Given First Name";
	//int size = 0;
	
		String sorry = "No Student Found With the Given First Name";

	%>
<script>errorFunction();</script>	<%
	

}

%>>

<%
String sorry = (String)request.getAttribute("C_SORRY");

%>

<span onload="errorFunction('<%=sorry %>')"></span>

          	<form action="UniClassesController" method="GET">
	            <input type="hidden" name="command" value="REGISTER">
	            <button class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" role="button" type="submit">Register for Classes</button>
	        </form>  
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="header.jsp" />
<body>
<div class="nav">

</div>
  <!-- Welcome Section -->
  <section id="dashboard">
    <div class="container">

      <h3 class="text-center text-uppercase text-secondary mb-0">Register for Classes Below</h3>
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
          <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
    	<form action="/UniClassesController" method="Get">
    	<input type="hidden" name="command" value="REGISTER" />
    	
    	<c:set var="tempStudent" value="${C_STUDENT}"/>
      	<c:set var="tempClassess" value="${STUDENTS_CLASSES}"/>
      	
    	<input type="hidden" name="studentId" value="${tempStudent.id}" />
    	<input type="hidden" name="classId" value="${tempClasses.id}" />
    	
	    	<table align="center" cellpadding="12">
	   			   <tr>
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
		    				<td>${tempClasses.className }</td>
		    				<td>${tempClasses.classTime }</td>
		    				<td>${tempClasses.professor }</td>
		    				<td>${tempClasses.classLevel }</td>
		    				<td>${tempClasses.seatsAvailable }</td>
		    				<td>
								<button class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger btn btn-info" role="button" type="submit">Register</button>
							</td>
		    			</tr>
		    			
		    		</c:if>
		    	</c:forEach>
	    	</table>
    	</form>
    	
        </div>
      </div>
    </div>
  </section>
  

</body>
<jsp:include page="footer.jsp" />

	<c:set var="name" value="${C_STUDENT}"/>


          <li class="nav-item mx-0 mx-lg-1">
        	<c:url var="studLink" value="UniClassesController">
				<c:param name="command" value="REGISTER" />
			</c:url>
        	<a href="${studLink}" class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">Register for Classes</a>
	      </li>
	      
	      <li class="nav-item mx-0 mx-lg-1">
        	<c:url var="studLink" value="UniStudentsControllerServlet">
				<c:param name="command" value="DASHBOARD" />
				<c:param name="studentId" value="${name.id}"/>
				<c:param name="classId" value="${STUDENT_CLASSES.id}"/>
			</c:url>
        	<a href=" " class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">Dashboard</a>
	      </li>
	      
          <li class="nav-item mx-0 mx-lg-1">
            <form action="UniStudentsControllerServlet" method="GET">
            <input type="hidden" name="command" value="LOGOUT">
            <button class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger btn btn-info" role="button" type="submit">Logout</button>
          	</form>
          </li>
          
          	      <li class="nav-item mx-0 mx-lg-1">
	      
        	<c:url var="studLink" value="UniStudentsControllerServlet">
				<c:param name="command" value="DASHBOARD" />
				<c:param name="studentId" value="${name.id}"/>
				<c:param name="classId" value="${STUDENT_CLASSES.id}"/>
			</c:url>
        	<a href=" " class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">Dashboard</a>
	      </li>
	      
	              	 <form action="UniClassesController" method="GET">
            	<input type="hidden" name="command" value="REGISTER">
            	  <%
            	  if ((request.getAttribute("C_STUDENT")) != null){
					//int c_stud = (int) request.getAttribute("C_STUDENT");
					out.println("<input type='hidden' name='studentId' value='<%=C_STUDENT>' />");
            	  }
            		%>
            	<button class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger btn btn-info" role="button" type="submit">Register</button>
          	</form>
          	
          	
          	
          			//Initialize studentId and classId
//		int studentId = 0, studentClassId = 0;
//		
//		if((request.getParameterMap().containsKey("classId")) && (request.getParameterMap().containsKey("classId"))){
//			studentClassId = Integer.parseInt(request.getParameter("classId"));
//			studentId = Integer.parseInt(request.getParameter("studentId"));
//
//		}
//		
//		int[] st = {studentId, studentClassId};
//
//		Uniclass.setStudentPlusClasses(st);
//		
//		listClasses(request, response);


		//UniClasses [] studentclasses = Uniclass.getStudentPlusClasses();
		//int studentId = Integer.parseInt(request.getParameter("studentId"));
//		
//		int[] st = Uniclass.getStudentPlusClasses();
//		int studentId = st[0];
//		int classId = st[1];
//		
//		String s = ""+studentId;
//		String c = ""+classId;
    			