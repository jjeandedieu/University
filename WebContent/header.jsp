<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    <%@ page import="java.util.*" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Rishi University</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

  <!-- Plugin CSS -->
  <link href="vendor/magnific-popup/magnific-popup.css" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template -->
  <link href="css/freelancer.min.css" rel="stylesheet">

</head>

<!--<body id="page-top">-->

  <!-- Navigation -->
      <c:set var="tempClassess" value="${STUDENTS_CLASSES}"/>
  
  <nav class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase" id="mainNav">
    <div class="container">
            <c:url var="homestudLink" value="UniStudentsControllerServlet">
				<c:param name="command" value="DASHBOARD" />
				<c:param name="studentId" value="${STUD_ID}"/>
			</c:url>
      <a class="navbar-brand-font" href="${homestudLink}">RU</a>
      
      <button class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
       <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
        
      <li class="nav-item mx-0 mx-lg-1">
        
      <c:set var="name" value="${C_STUDENT}"/>
      	<a href="" class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">
      		<span style="color:#ff8c00">${name.firstName}, </span>
      	</a>
      </li>

          <li class="nav-item mx-0 mx-lg-1">
        	<c:url var="studLink" value="UniClassesController">
				<c:param name="command" value="REGISTER" />
				<c:param name="studentId" value="${STUD_ID}"/>
			</c:url>
        	<a href="${studLink}" class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">Register for Classes</a>
	      </li>
	      
	      <li class="nav-item mx-0 mx-lg-1">
        	<c:url var="studLink" value="UniStudentsControllerServlet">
				<c:param name="command" value="DASHBOARD" />
				<c:param name="studentId" value="${STUD_ID}"/>
			</c:url>
        	<a href="./student_services.jsp" class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">Student Services</a>
	      </li>
	      
	      <li class="nav-item mx-0 mx-lg-1">
        	<c:url var="studLink" value="UniStudentsControllerServlet">
				<c:param name="command" value="DASHBOARD" />
				<c:param name="studentId" value="${STUD_ID}"/>
			</c:url>
        	<a href="${studLink}" class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">My Dashboard</a>
	      </li>
	      
          <li class="nav-item mx-0 mx-lg-1">
            <form action="UniStudentsControllerServlet" method="GET">
            <input type="hidden" name="command" value="LOGOUT">
            <button class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger btn btn-info" role="button" type="submit">Logout</button>
          	</form>
          </li>

        </ul>
      </div>
    </div>
  </nav>
  <!-- End of Header -->
