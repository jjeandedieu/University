<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
     <%
 String sorry = (String)request.getAttribute("C_SORRY");
 String logout = (String)request.getAttribute("LOGOUT_Y");
 String user_error = (String)request.getAttribute("username_error");
 String pass_error = (String)request.getAttribute("password_error");
 
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>University Home</title>

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
  
  <!-- login imports -->
  <link href='http://fonts.googleapis.com/css?family=Varela+Round|Open+Sans:400,300,600' rel='stylesheet' type='text/css'>


<!-- JQUERY Google API -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- -------------- Own Customizations---------------- -->
<!-- login css file -->
<link href="css/login.css" rel="stylesheet">



</head>

<body id="page-top" onload="errorFunction('<%=sorry %>'); Logout('<%=logout%>'); Login('<%=user_error%>');">

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase" id="mainNav">
    <div class="container">
      <a class="navbar-brand-font" href="./index.jsp">RU</a>
      <button class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
       <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
        
          <li class="nav-item mx-0 mx-lg-1">
            <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="./register_student.jsp">Register Student</a>
          </li>
          
           <li class="nav-item mx-0 mx-lg-1">
            <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="./register_teacher.jsp">Register Teacher</a>
          </li>
          
          <li class="nav-item mx-0 mx-lg-1">
            <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="./search.jsp">Search</a>
          </li>
          
           <li class="nav-item mx-0 mx-lg-1">
            <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger btn btn-info" onclick="openForm()" role="button">Login As A Student</a>
            <br>
            <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger btn btn-info" onclick="openFormTeacher()" role="button">Login As A Teacher</a>
          </li>

        </ul>
      </div>
    </div>
  </nav>
  
  <!-- End of Login box -->
<!--  Login Code For Student-->

<div class="form-popup" id="myForm">
  <form action="UniStudentsControllerServlet" class="form-container" method="GET">
  	<input type="hidden" name="command" value="LOGIN">
    <h1>Login</h1>

    <label for="username"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label for="password"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>

    <button type="submit" class="btn">Login As A Student</button>
    <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
  </form>
</div>

<!--  Login Code For Teacher-->

<div class="form-popup" id="myFormTeacher">
  <form action="UniTeachersController" class="form-container" method="GET">
  	<input type="hidden" name="command" value="LOGIN">
    <h1>Login</h1>

    <label for="username"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label for="password"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>

    <button type="submit" class="btn">Login As A Teacher</button>
    <button type="button" class="btn cancel" onclick="closeFormTeacher()">Close</button>
  </form>
</div>