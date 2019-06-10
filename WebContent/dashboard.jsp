<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    <%@ page import="java.util.*" %>

<title>Student Dashboard</title>

<jsp:include page="header.jsp" />

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
      <h2 class="text-center text-uppercase text-secondary mb-0">Student Dashboard</h2>
      <hr class="star-dark mb-5">
    
    <c:set var="name" value="${C_STUDENT}"/>
      <h3 class="text-center text-uppercase text-secondary mb-0"><span style="color:#17a2b8">Welcome <span style="color:#2c3e50">"${name.firstName}"</span> to your
      student dashboard</span></h3>
  
      <!-- Contact Section -->
  <!-- <section id="classes">
    <div class="container">
    <c:set var="name" value="${C_STUDENT}"/>
      <h3 class="text-center text-uppercase text-secondary mb-0">Classes Attending this Semester</h3>
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
          <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
    	<!--  <table align="center" cellpadding="12">
   		   			   <tr>
	    			<td><b>Class ID</b></td>
	    			<td><b>Class Name</b></td>
	    			<td><b>Class Time</b></td>
	    			<td><b>Class Professor</b></td>
	    			<td><b>Class Level</b></td>
	    			<td><b>Seats Available</b></td>
	    		</tr>
	    		<tr>
	    		<c:forEach var="tempClass" items="${CURRENT_STUD_CLASSES}">
					<!--Display the list of classes-->
					<!--<c:url var="tempLink" value="UniStudentsControllerServlet">
						<c:param name="command" value="DROPCLASS" />
						<c:param name="studentId" value="${tempStudent.id}"/>
						<c:param name="classId" value="${tempClass.id}"/>
					</c:url>
					
					<tr>
						<td>${tempClass.id}</td>
						<td>${tempClass.className}</td>
						<td>${tempClass.classTime}</</td>
						<td>${tempClass.professor}</</td>
						<td>${tempClass.classLevel}</</td>
						<td>${tempClass.seatsAvailable}</</td>
						<td>
							<a id="register" href="${tempLink}" class="btn btn-info" role="button">Drop Class</a>
								
						</td>

					</tr>
				</c:forEach>	
	    		
	    		</tr>
    	
    	</table>
    	
    	-->
    	
    	<!-- PORFOLIO Portfolio Grid Section -->
  <section class="portfolio" id="portfolio">
  
    <div class="container col-lg-10 mx-auto">
    


     <h3 class="text-center text-uppercase text-secondary mb-0">Pick one of the options below to navigate to</h3>
      <br>
      <div class="row">
      
      
      
        <div class="col-md-6 col-lg-6">
        
		    <c:url var="updateLink" value="UniStudentsControllerServlet">
				<c:param name="command" value="POPULATESTUDINFO" />
				<c:param name="studentId" value="${name.id}"/>
			</c:url>
          
          <a class="newportfolio-item d-block mx-auto" href="${updateLink}">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
           <img class="img-fluid" src="img/portfolio/student_personal_info.png" alt="Student Personal Info">
          </a>
          
        </div>
        
        
        
        
        
        <div class="col-md-6 col-lg-6">
          <a class="newportfolio-item d-block mx-auto" href="./student_info.jsp">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
            <img class="img-fluid" src="img/portfolio/student_info.png" alt="">
          </a>
        </div>
        
        <div class="col-md-6 col-lg-6">
          <a class="newportfolio-item d-block mx-auto" href="https://1stculture.com/">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
            <img class="img-fluid" src="img/portfolio/student_news3.png" alt="">
          </a>
        </div>
       
        <div class="col-md-6 col-lg-6">
          <a class="newportfolio-item d-block mx-auto" href="./student_services.jsp">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
            <img class="img-fluid" src="img/portfolio/student_services.png" alt="">
          </a>
        </div>
        
        </div>
        </div>
        

  </section>
    	
    	
    	
    	
    	
    	
    	
    	
        </div>
      </div>
    </div>
  </section>

  <!-- Footer -->


</body>
<jsp:include page="footer.jsp" />