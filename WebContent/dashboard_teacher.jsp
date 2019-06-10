<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    <%@ page import="java.util.*" %>

<title>Teacher Dashboard</title>

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
      <h2 class="text-center text-uppercase text-secondary mb-0">Teacher Dashboard</h2>
      <hr class="star-dark mb-5">
    
    <c:set var="name" value="${C_TEACHER}"/>
      <h3 class="text-center text-uppercase text-secondary mb-0"><span style="color:#17a2b8">Welcome <span style="color:#2c3e50">"${name.firstName}"</span> to your
      teacher dashboard</span></h3>
    	
    	<!-- PORFOLIO Portfolio Grid Section -->
  <section class="portfolio" id="portfolio">
  
    <div class="container col-lg-10 mx-auto">
    


     <h3 class="text-center text-uppercase text-secondary mb-0">Pick one of the options below to navigate to</h3>
      <br>
      <div class="row">

        <div class="col-md-6 col-lg-6">
        
		    <c:url var="popLink" value="UniTeachersController">
				<c:param name="command" value="POPULATETEACHINFO" />
				<c:param name="teacherId" value="${name.id}"/>
			</c:url>
          
          <a class="newportfolio-item d-block mx-auto" href="${popLink}">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
           <img class="img-fluid" src="img/portfolio/tpersonal_info.png" alt="Student Personal Info">
          </a>
          
        </div>
        

        <div class="col-md-6 col-lg-6">
        	<c:url var="listClass" value="UniTeachersController">
				<c:param name="command" value="LISTEACHCLASSES" />
				<c:param name="teacherId" value="${name.id}"/>
			</c:url>
          <a class="newportfolio-item d-block mx-auto" href="${listClass}">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
            <img class="img-fluid" src="img/portfolio/tcurrent_class_schedule.png" alt="">
          </a>
        </div>
        
        <div class="col-md-6 col-lg-6">
                        
		    <c:url var="listClassLink" value="UniClassesController">
				<c:param name="command" value="LISTTEACHER" />
				<c:param name="teacherId" value="${name.id}"/>
			</c:url>
			
          <a class="newportfolio-item d-block mx-auto" href="${listClassLink}">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
            <img class="img-fluid" src="img/portfolio/tpick_classes.png" alt="">
          </a>
        </div>
       
        <div class="col-md-6 col-lg-6">
        	<c:url var="listClass" value="UniTeachersController">
				<c:param name="command" value="SUBMITGRADES" />
				<c:param name="teacherId" value="${name.id}"/>
			</c:url>
          <a class="newportfolio-item d-block mx-auto" href="${listClass}">
            <div class="newportfolio-item-caption d-flex position-absolute h-100 w-100">
              <div class="newportfolio-item-caption-content my-auto w-100 text-center text-white">
                <i class="fas fa-search-plus fa-3x"></i>
              </div>
            </div>
            <img class="img-fluid" src="img/portfolio/tsubmit_grade.png" alt="">
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