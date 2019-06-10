(function() {
    $('form > input').keyup(function() {

        var empty = false;
        $('form > input').each(function() {
            if ($(this).val() == '') {
                empty = true;
            }
        });

        if (empty) {
            $('#register').attr('disabled', 'disabled');
        } else {
            $('#register').removeAttr('disabled');
        }
    });
})()



$('#firstname, #lastname, #email, #phone, #address, #grade, #username, #password').bind('keyup', function() {
    if(allFilled()) $('#register').removeAttr('disabled');
});

function allFilled() {
    var filled = true;
    $('form input').each(function() {
        if($(this).val() == '') filled = false;
    });
    return filled;
}

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