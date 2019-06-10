<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>


<script> 
		$(document).ready(function() {
			$("#btn").click(function() {
					$("#test").load("test3.jsp", {
						Name: "Daniel",
						Lastname: " Nielsen"
						}, function() {
							alert("Hi");

							});
				});
		
			});


		/*		$(document).ready(function() {
				$("button").click(function(){
					$.get("test3.jsp", function(data, status) {
						$("#test").html(data);
							alert(status)

						})
					})
			})
			
			
		$(document).ready(function() {

			$("input").keyup(function() {
				var name = $("input").val();
				$.post("testServlet", {
					testServlet: name

					}, function(data, status) {
							$("#test2").html(data);
						})

				})

			})*/
			
			
	$(document).ready(function() {
         $("#somebutton").click(function() {
             servletCall();
         });

     });
     function servletCall() {
			var namex = $("#text_name").val();
	         $.post(
             "testServlet", 
             {name : namex}, //meaasge you want to send
             function(result) {
             $('#somediv').html('Here is your result : <strong>' + result + '</strong>'); //message you want to show
         });
     };
			

	</script>
</head>
<body>
	<div id="test">
		<p>This is the first content!</p>
	</div>
	<button id="btn">Click to change</button>
	
	
		<div id="test1">
		<p>Click me to get data</p>
	</div>
	<button id="btn1">Click to get data</button>

	<div>
		<input type="text" name="name">
		<p id="test2"></p>
	</div>
	
	<br>
	<div>
	<input type="text" name="name1" id="text_name">
     <button id="somebutton">press here</button>
     <div id="somediv"></div>
	</div>
</body>

<jsp:include page="footer.jsp" />
</html>