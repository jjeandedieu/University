<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
var request;
function sendInfo()
{
	//v holds the information to be sent
	//to the server, taken from the input value
	//with the name t1
	var v=document.vinform.t1.value;
	var url="ajaxdemo.jsp?val="+v;
	//
	//Create the XMLHttpRequest Object 
	//that allow sendnig of information to a server
	//if the browser support this object
	if(window.XMLHttpRequest){
		request=new XMLHttpRequest();
	}
	//if the browser does not support the XMLHttpRequest object
	//like older IE browser use the activeX object
	else if(window.ActiveXObject){
		request=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	try
	{
	//onreadystatechange define a function to be executed
	//when the readystate changes
	request.onreadystatechange=getInfo;
	request.open("GET",url,true);
	request.send();
	}
	catch(e)
	{
	alert("Unable to connect to server");
	}
	}
	
	function getInfo(){
		//readyState holds the status of the xmlhttprequest
		//response object
		if(request.readyState==4){
			var val=request.responseText;
			document.getElementById('amit').innerHTML=val;
		}
}

</script>
</head>
<body>
    <marquee><h1>This is an example of ajax with database</h1></marquee>
<form name="vinform">
<input type="text" name="t1" onkeyup="sendInfo()">

<input type="button" value="ShowTable" onClick="sendInfo()">  

</form>

<span id="amit"> </span>

</body>
</html>