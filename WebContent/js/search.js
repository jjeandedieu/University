
var request;
function sendInfo()
{
	//Holds the information to be sent
	//to the server, taken from the input value
	//with the name t1
	var v=document.search.t1.value;
	var command = "SEARCHING";
	
	//+ "&command=" + command
	var url="/University/UniStudentsControllerServlet?val="+ v + "&command=" + command;
	
	//Create the XMLHttpRequest Object 
	//that allow sending of information to a server
	//if the browser support this object
	if(window.XMLHttpRequest){
		request=new XMLHttpRequest();
	}
	//if the browser does not support the XMLHttpRequest object
	//like older IE browser use the activeX object
	else if(window.ActiveXObject){
		request = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	try
	{
	//onreadystatechange define a function to be executed
	//when the readystate changes
	request.onreadystatechange=getInfo;
	request.open("GET", url, true);
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
//			var val=request.responseText;
//			console.log(val);
//			document.getElementById('amit').innerHTML=val;
			
			var myObj = JSON.parse(this.responseTest);
			console.log(myObj[0].firstName);
			document.getElementById('amit').innerHTML = myObj[0].firstName;
		}
}
