<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header_front.jsp" />


<div class="nav">

</div>
 

  <!-- Search Section -->
  <section id="search">
    <div class="container">
      <h2 class="text-center text-uppercase text-secondary mb-0">Search Student By First Name</h2>
      <hr class="star-dark mb-5">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
          <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
          <form action="UniStudentsControllerServlet" method="GET" name="search">
            
           <input type="hidden" name="command" value="SEARCH" />
            
            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Search Student By First Name</label>
                <input class="form-control" name="firstname_search" type="text" placeholder="Search Student By First Name" required="required" data-validation-required-message="Please enter the name you want to search.">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            
           	<div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>AJAX</label>
                <input class="form-control" name="t1" onkeyup="sendInfo()" type="text" >
                <p class="help-block text-danger"></p>
              </div>
            </div>

            <br>
            <div id="success"></div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary btn-xl" >Search</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
  
    <!-- Contact Section -->
  <section id="search">
    <div class="container">
      <h2 class="text-center text-uppercase text-secondary mb-0">Display Results</h2>
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
          <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
    	<table align="center" cellpadding="12">
   			   <tr>
	    			<td><b>First Name</b></td>
	    			<td><b>Last Name</b></td>
	    			<td><b>Email</b></td>
	    			<td><b>Phone Number</b></td>
	    			<td><b>Address</b></td>
	    			<td><b>Grade Level</b></td>
	    		</tr>
    			
    			<tr>
    			<%
    			if ((request.getAttribute("SIZE")) != null){
	    			String message = "No Student Found With the Given First Name";
					//int size = 0;
	    			int size = (int)request.getAttribute("SIZE");
	    			if(size == 0 ){
	    				out.println("<td><h3>" + message + "<h3></td>");
	
	    			}
    			
    			}
    			
    			%>
    			</tr>
    		
    		
    		<!--
    		<c:forEach var="tempStudent" items="${STUDENTS_FOUND}">
    		<c:set var="studentSize" scope="session" value="${SIZE}"/>
    			
    		    <c:if test="${not empty tempStudent}">
		    		    
	    			<tr>
	    				<td>${tempStudent.firstName }</td>
	    				<td>${tempStudent.lastName }</td>
	    				<td>${tempStudent.email }</td>
	    				<td>${tempStudent.phoneNumber }</td>
	    				<td>${tempStudent.address }</td>
	    				<td>${tempStudent.grade }</td>
	    			</tr>
    			</c:if>
    
    		</c:forEach>-->
    	
    	</table>
    	
    	<span id="amit"> </span>
    	
        </div>
      </div>
    </div>
    
    
    <span id="amit"> </span>
    
  </section>

  <!-- Footer -->
  <footer class="footer text-center">
    <div class="container">
      <div class="row">
        <div class="col-md-4 mb-5 mb-lg-0">
          <h4 class="text-uppercase mb-4">Location</h4>
          <p class="lead mb-0">901 International Pkwy Ste 100
            <br>Lake Mary, FL 32746</p>
        </div>
        
      </div>
    </div>
  </footer>

  <div class="copyright py-4 text-center text-white">
    <div class="container">
      <small>Copyright &copy; RishiUniversity.com 2019</small>
    </div>
  </div>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Plugin JavaScript -->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
  <script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>


  <!-- Custom scripts for this template -->
  <script src="js/freelancer.min.js"></script>
  
  
  <!-- Custom scripts for this template -->
  <script src="js/freelancer.min.js"></script>
	
	<!-- Customer Sciprt for the login box -->
	<script src="js/login.js"></script>
	
	<script src="js/general.js"></script>
	
	<script src="js/search.js"></script>
	

</body>
</html>