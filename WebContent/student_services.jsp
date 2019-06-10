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
      <h2 class="text-center text-uppercase text-secondary mb-0">Student Services</h2>
      <hr class="star-dark mb-5">
    
    <c:set var="name" value="${C_STUDENT}"/>
      <h3 class="text-center text-uppercase text-secondary mb-0"><span style="color:#17a2b8">Welcome <span style="color:#2c3e50">"${name.firstName}"</span> to your
      student services page</span></h3>
    	
    	<!-- PORFOLIO Portfolio Grid Section -->
  <section class="portfolio" id="portfolio">
  
    <div class="container col-lg-10 mx-auto">
    


     <h3 class="text-center text-uppercase text-secondary mb-0">Pick one of the options below to navigate to</h3>
      <br>
      <div class="row">
      
      
      
        <div class="col-md-6 col-lg-6">
        
		    <c:url var="registerClassLink" value="UniClassesController">
				<c:param name="command" value="REGISTER" />
				<c:param name="studentId" value="${name.id}"/>
			</c:url>
          
          <a class="newportfolio-item d-block mx-auto" href="${registerClassLink}">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
           <img class="img-fluid" src="img/portfolio/register_classes.png" alt="Register Classes">
          </a>
          
        </div>
        

        <div class="col-md-6 col-lg-6">
        	<c:url var="listClass" value="UniStudentsControllerServlet">
				<c:param name="command" value="LISTSTUDCLASSES" />
				<c:param name="studentId" value="${name.id}"/>
			</c:url>
          <a class="newportfolio-item d-block mx-auto" href="${listClass}">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
            <img class="img-fluid" src="img/portfolio/current_classes.png" alt="">
          </a>
        </div>
        
        <div class="col-md-6 col-lg-6">
          <a class="newportfolio-item d-block mx-auto" href="https://1stculture.com/">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
            <img class="img-fluid" src="img/portfolio/make_payment2.png" alt="">
          </a>
        </div>
       
        <div class="col-md-6 col-lg-6">
          <a class="newportfolio-item d-block mx-auto" href="https://1stculture.com/">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
            <img class="img-fluid" src="img/portfolio/student_records.png" alt="">
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