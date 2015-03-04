var selID;

$(document).ready(function(){

//	$(".save").click(function(){
//		$(".myform").submit();
//	});

	$(document).ready(function(){
		$(".list-group").on("click", ".item", function(){
			selID = $(this).attr("uid");
			assignValues();
		});
	});

//	$(".myform").submit(function(e){
//		var postData = $(this).serializeArray();
//	    $.ajax(
//	    {
//	        url : "/data?entity=0&opt=0",
//	        type: "POST",
//	        encoding: "utf-8",
//	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
//	        data : postData,
//	        success:function(data, textStatus, jqXHR)
//	        {
//	        	location.reload();
//	        },
//	        error: function(jqXHR, textStatus, errorThrown)
//	        {
//	            alert(textStatus);
//	        }
//	    });
//	    e.preventDefault();
//	    e.unbind();
//	});

	$(".delete").click(function(){
		deleteCustomer();
	});

	$(".list-group").on("click", "a.new", function(){
		reset_form();
		selID = 0;
	});
});


function assignValues()
{
	var customer;
	for(var i = 0; i < window.json_customer.length; i++)
	{
		var tmp = JSON.stringify(window.json_customer[i].id);
		if( selID == tmp )
		{
			customer = window.json_customer[i];
			break;
		}
	}
	$("#uid").val(customer.id);
	$("#alias").val(customer.alias);
	$("#telephon").val(customer.telefon);
	$("#email").val(customer.email);
	$("#country").val(customer.country);
	$("#province").val(customer.province);
	$("#city").val(customer.city);
	$("#zipcode").val(customer.zipcode);
	$("#address").val(customer.address);
	$("#firstname").val(customer.firstName);
	$("#lastname").val(customer.lastName);
	$("#pwd").val(customer.pwd);
}

function reset_form()
{
	$(":input", ".myform").val("");
	$("input#uid").val("0");
}

function deleteCustomer()
{
    $.ajax(
    {
        url : "/data?entity=0&opt=1",
        type: "POST",
        data : {id: selID},
        success:function(data, textStatus, jqXHR)
        {
        	location.reload();
        },
        error: function(jqXHR, textStatus, errorThrown)
        {
        	alert("error");
        }
    });
}
