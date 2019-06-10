<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    <%@ page import="java.util.*" %>

<title>Teacher Personal Information</title>

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
   <section id="update_info">
    <div class="container">
    
    <c:set var="teach" value="${C_TEACHER}"/>
    
    <div>
    	<%
    		String update = (String) request.getAttribute("updation");
    		
    		if(update == "success"){
    			out.print("<div id='regis_success' class='card bg-success text-white' align='center'>"+
	    				      "<div class='card-body'>You Have Successfully Updated Your Personal Information</div>" +
    				      "</div>");
    			
    			out.print("<br>");
    			
    		}
    	%>
   
    
    <h2 class="text-center text-uppercase text-secondary mb-0">Your Personal Information</h2>
      <hr class="star-dark mb-5">
      
      <h3 class="text-center text-uppercase text-secondary mb-0">Update or Save Below</h3>
      
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
          <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
          <form id="registered" action="UniTeachersController" method="GET"  >
            
            <input type="hidden" name="command" value="UPDATE"/>
            <input type="hidden" name="teacherId" value="${teach.id}" />
            
            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>First Name</label>
                <input class="form-control" id="firstname" name="firstname" type="text" required="required" data-validation-required-message="Please enter your name." value="${teach.firstName}">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Last Name</label>
                <input class="form-control" id="lastname" name="lastname" type="text" required="required" data-validation-required-message="Please enter your name." value="${teach.lastName}">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                            
                <label>Email Address</label>
                <input class="form-control" id="email" name="email" type="email" required="required" data-validation-required-message="Please enter your email address." value="${teach.email}">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Phone Number</label>
                <input class="form-control" id="phone" name="phone" type="number" required="required" data-validation-required-message="Please enter your phone number." value="${teach.phoneNumber}">
                <p class="help-block text-danger"></p>
              </div>
            </div>
              <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Address</label>
                <input class="form-control" id="address" name="address" type="text" required="required" data-validation-required-message="Please enter your address." value="${teach.address}">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Seniority</label>
				  <input list="seniority" name="seniority" required="required" value="${teach.seniority}" readonly>
				  <datalist id="seniority">
				    <option value="Adjunct professor ">
				    <option value="Assistant professor ">
				    <option value="Associate professor ">
				    <option value="Professor">
				    <option value="Ch air/Distinguished professor ">
				  </datalist>
                <p class="help-block text-danger"></p>
              </div>
            </div>
            </br>
 		     <h3 class="text-center text-uppercase text-secondary mb-0">Login Credentials</h3>
              <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Username</label>
                <input class="form-control" id="username" name="username" type="text" required="required" data-validation-required-message="Please enter your username." value="${teach.username}" readonly>
                <p class="help-block text-danger"></p>
              </div>
            </div>            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Password</label>
                <input class="form-control" id="password" name="password" type="text" required="required" data-validation-required-message="Please enter your password."  value="${teach.password}">
                <p class="help-block text-danger"></p>
              </div>
            </div>
                        
        
            <br>
            <div class="form-group">
              <input style="width: 100%" type="submit" class="btn btn-primary btn-xl" id="update" value="Update" />
          </div>
           <!--
            </div>             
            <!-- <div id="success"></div>
            <div class="form-group">
              <input type="submit" class="btn btn-primary btn-xl" id="register" value="Register" disabled="disabled"/>
            </div>
            -->
            
          </form>
          <br/>
          <form action="./dashboard.jsp">
              <input style="width: 100%" type="submit" class="btn btn-primary btn-xl" id="Save" value="Cancel" />
          </form>
        </div>
      </div>
    </div>
    </div>
  </section>

  <!-- Footer -->


</body>
<jsp:include page="footer.jsp" />