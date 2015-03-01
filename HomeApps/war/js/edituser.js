$(document).ready(function(){
	$(".save").click(function(){
		$(".myform").submit();
	});
	
	$(".myform").submit(function(e){
		var postData = $(this).serializeArray();
	    var formURL = $(this).attr("action");
	    $.ajax(
	    {
	        url : formURL,
	        type: "POST",
	        encoding: "utf-8",
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        data : postData,
	        success:function(data, textStatus, jqXHR) 
	        {
	        	refreshCustomersList();
	        },
	        error: function(jqXHR, textStatus, errorThrown) 
	        {
	            alert(textStatus);    
	        }
	    });
	    e.preventDefault();
	    e.unbind();
	});
});