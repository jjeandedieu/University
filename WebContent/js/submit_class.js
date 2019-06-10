	$(document).ready(function() {
         $("#submit_grades").click(function() {
             servletCall();
         });

     });
     function servletCall() {
			var thecommand = $("#thecommand").val();
			var theclassId = $("#classId").val();
			var thestudentId = $("#studentId").val();
	         $.post(
             "UniTeachersController", 
             {
            	 command : thecommand,
            	 classId: theclassId,
            	 studentId : thestudentId
            	 
             
             
             }, //meaasge you want to send
             function(result) {
             $('#somediv').html('Here is your result : <strong>' + result + '</strong>'); //message you want to show
         });
     };