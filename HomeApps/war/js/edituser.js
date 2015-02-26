var customers;
var selID = 0;

$(document).ready(
		
		function()
		{
//			$(".list-group").on("click", "a.new",
//				function()
//				{
//					reset_form();
//					selID = 0;
//				}
//			);
//			
//			$(".list-group").on("click", ".item", function(){
//				selID = $(this).attr("uid");
//				assignValues();
//			});
			
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
			
			$(".save").click(function(){
				$(".myform").submit();
			});
			
//			$(".delete").click(function(){
//				deleteCustomer();
//			});
//		}
);

function deleteCustomer()
{
	if( 0 == selID )
	{
		reset_form();
	}
	else
	{
		deleteCustomerFromDB();
	}
}

//function deleteCustomerFromDB()
//{
//    $.ajax(
//    {
//        url : "/customer?opt=2",
//        type: "POST",
//        data : {uid: selID},
//        success:function(data, textStatus, jqXHR) 
//        {
//        	refreshCustomersList();
//        	reset_form();
//        },
//        error: function(jqXHR, textStatus, errorThrown) 
//        {
//            alert(textStatus);    
//        }
//    });
//}

function refreshCustomersList()
{
	removeCustomers();
	loadCustomers();
	addCustomers();
}

function removeCustomers()
{
	$(".list-group").empty();
}

function loadCustomers()
{
	$.ajax(
	    	{
	    		type: "GET",
	            url: "/customer?opt=1",
	            dataType: "json",
	            async: false,
	            success: function(data)
	            {
	            	customers = data;
	            },
	            error: function(e)
	            {
	            	customers = null;
	            }
	    	}
	    );
}

function reset_form()
{
	$(":input", ".myform").val("");
	$("input#uid").val("0");
}

function addCustomers()
{
	var result = "<a href='#' class='list-group-item active new' style='text-align: center'>+ 新建客户</a>";
	var length = customers.length;
	for(var i = 0; i < length; i++)
	{
		result += "<a href='#' class='list-group-item item' style='text-align: center' uid="+ customers[i].id +">" + customers[i].lastName + " " + customers[i].firstName + "</a>";
	}
	
	$(".list-group").append(result);
}

function assignValues()
{
	var customer;
	for(var i = 0; i < customers.length; i++)
	{
		var tmp = JSON.stringify(customers[i].id);
		if( selID == tmp )
		{
			customer = customers[i];
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
}

