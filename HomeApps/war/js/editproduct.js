var selID;

$(document).ready(function(){

	$(document).ready(function(){
		$(".list-group").on("click", ".item", function(){
			selID = $(this).attr("uid");
			assignValues();
		});
	});

	$(".delete").click(function(){
		deleteProduct();
	});

	$(".list-group").on("click", "a.new", function(){
		reset_form();
		selID = 0;
	});
});


function assignValues()
{
	var product;
	for(var i = 0; i < window.json_product.length; i++)
	{
		var tmp = JSON.stringify(window.json_product[i].id);
		if( selID == tmp )
		{
			product = window.json_product[i];
			break;
		}
	}
	$("#uid").val(product.id);
	$("#name").val(product.name);
	$("#categorie").val(product.categorie);
	$("#defaultprice").val(product.defaultPrice);
	$("#defaultamount").val(product.defaultAmount);
}

function reset_form()
{
	$(":input", ".myform").val("");
	$("input#uid").val("0");
}

function deleteProduct()
{
    $.ajax(
    {
        url : "/data?entity=1&opt=1",
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
