<jsp:include page="header_front.jsp" />

<div class="nav">

</div>
 

  <!-- Register Section -->
  <section id="register">
    <div class="container">
    
    <div>
    	<%
    		String regis = (String) request.getAttribute("Registration");
    		
    		if(regis == "success"){
    			out.print("<div id='regis_success' class='card bg-success text-white' align='center'>"+
	    				      "<div class='card-body'>You Have Successfully Been Registered</div>" +
    				      "</div>");
    			
    			out.print("<br>");
    			
    		}
    	%>
   
    
      <h2 class="text-center text-uppercase text-secondary mb-0">Register As A Student</h2>
      <hr class="star-dark mb-5">
      <div class="row">
        <div class="col-lg-8 mx-auto">
          <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
          <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
          <form id="registered" action="UniStudentsControllerServlet" method="GET"  >
            
           <input type="hidden" name="command" value="ADD" />
            
            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>First Name</label>
                <input class="form-control" id="firstname" name="firstname" type="text" placeholder="First Name" required="required" data-validation-required-message="Please enter your name.">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Last Name</label>
                <input class="form-control" id="lastname" name="lastname" type="text" placeholder="Last Name" required="required" data-validation-required-message="Please enter your name.">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                            
                <label>Email Address</label>
                <input class="form-control" id="email" name="email" type="email" placeholder="Email Address" required="required" data-validation-required-message="Please enter your email address.">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Phone Number</label>
                <input class="form-control" id="phone" name="phone" type="number" placeholder="Phone Number" required="required" data-validation-required-message="Please enter your phone number.">
                <p class="help-block text-danger"></p>
              </div>
            </div>
              <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Address</label>
                <input class="form-control" id="address" name="address" type="text" placeholder="Address" required="required" data-validation-required-message="Please enter your address.">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Grade level</label>
                <input type="text" list="grade" id="grade" class="form-control" name="grade" type="text" placeholder="Grade Level" required="required" data-validation-required-message="Please enter your grade level.">
               	<datalist id="grade">
               		<option value = "Freshman"></option>
               		<option value="Sophmore"></option>
               		<option value="Junor"></option>
               		<option value="Senior"></option>
               	</datalist>
                <p class="help-block text-danger"></p>
              </div>
            </div>
            </br>
 		     <h3 class="text-center text-uppercase text-secondary mb-0">Login Credentials</h3>
              <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Username</label>
                <input class="form-control" id="username" name="username" type="text" placeholder="Username" required="required" data-validation-required-message="Please enter your username.">
                <p class="help-block text-danger"></p>
              </div>
            </div>            <div class="control-group">
              <div class="form-group floating-label-form-group controls mb-0 pb-2">
                <label>Password</label>
                <input class="form-control" id="password" name="password" type="text" placeholder="Password" required="required" data-validation-required-message="Please enter your password.">
                <p class="help-block text-danger"></p>
              </div>
            </div>
                        
        
            <br>
            <div id="success"></div>
            <div class="form-group">
              <input type="submit" class="btn btn-primary btn-xl" id="register" value="Register" />
            </div>     <!--        
            <div id="success"></div>
            <div class="form-group">
              <input type="submit" class="btn btn-primary btn-xl" id="register" value="Register" disabled="disabled"/>
            </div>
            -->
          </form>
        </div>
      </div>
    </div>
    </div>
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

  <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
  <div class="scroll-to-top d-lg-none position-fixed ">
    <a class="js-scroll-trigger d-block text-center text-white rounded" href="#page-top">
      <i class="fa fa-chevron-up"></i>
    </a>
  </div>



  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Plugin JavaScript -->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
  <script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="js/freelancer.min.js"></script>
	
	<!-- Customer Sciprt for the login box -->
	<script src="js/login.js"></script>
	
	<script src="js/general.js"></script>

</body>
</html>