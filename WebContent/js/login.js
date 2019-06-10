function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function openFormTeacher() {
	  document.getElementById("myFormTeacher").style.display = "block";
	}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}

function closeFormTeacher() {
	  document.getElementById("myFormTeacher").style.display = "none";
	  }

function errorFunction(error){
	//alert(error)
	if(error === null){
		alert("You have entered the Wrong Username or Password, Please try again")

	}else{

	}
	
	if(error==="wrong"){
		alert("You have entered the Wrong Username or Password, Please try again")

	}
}

function Logout(logout){	
	if(logout==="yes"){
		alert("You have succesfully been logged out")

	}
}

function Login(loginuse){	
	var newURL = "University/register_student.jsp"
		
		if(loginuse==="exist"){
			alert("The Username Entered Already Exist")
		}
		
		window.history.pushState({}, document.title, "/" + newURL );
		
}

setTimeout(function() {
	  $("#regis_success").fadeOut().empty();
	}, 5000);



function disableButton(classFull){
	 var lnk = document.getElementById('register');
	
	if(classFull === "true"){

	    if (window.addEventListener) {
	        document.addEventListener('click', function (e) {
	            if (e.target.id === lnk.id) {
	                e.preventDefault();         // Comment this line to enable the link tag again.
	            }
	        });
	    }	
	}
	else {
	    if (window.addEventListener) {
	        document.addEventListener('click', function (e) {
	            if (e.target.id === lnk.id) {
	            	
	            }
	        });
	    }	
	    
	}
}