var order_counter = 0;
var id_customer = "cust";
var id_product = "prod";
var customers;
var auth;

$(document).ready(

    function () {

        $("button#add_order").click(

            function ()
            {
                addOrder();
            }
        );

        $("#order_list").on("click", "button#btn_product",
            function()
            {
                var row = addRowPair(id_product);
                $(this).closest("tbody").append(row);
                $(this).closest("tr").remove();
            }
        );

        $("#order_list").on("click", ".dropdown-menu li a",
                function()
                {
        			var selText = $(this).text();
        			var prod_id = $(this).attr('uid');
        			$(this).parents('.dropdown').find('.dropdown-toggle').html(selText+'<span class="caret"></span>').end().children( '.dropdown-toggle' ).dropdown( 'toggle' );

        			var product = find_product(prod_id);
        			if( product )
        			{
        				var row = $(this).closest("tr");
        				var sp = product.defaultPrice;
        				var amt = product.defaultAmount;
        				var tp = amt * sp;
        				$(row).find(".sprice").val(sp);
        				$(row).find(".amount").val(amt);
        				$(row).find(".tprice").val(tp.toFixed(2));
        			}

        			return false;
                }
        );

        $("#order_list").on("click", ".delete_product",
                function()
                {
        			$(this).closest("tr").remove();
                }
        );
    }
);

function addOrder()
{
    var result = "<hr /><div id='order'>";
    result += getOrderTitle(id_customer);
    result += getOrderTable(id_product);
    result += "</div>";
    $("div#order_list").append(result);
}

function getOrderTitle(id)
{
    var result = "";
    result += "<div id='order'>";
    result += "<div id='order_title' class='row'>";
    result += "<div class='col-sm-3'>";
    result += getDropDown("选择客户", id);
    result += "</div>";
    result += "<div class='col-sm-6'>";
    result += "<lable />";
    result += "</div>";
    result += "<div class='col-sm-3'>总额: ";
    result += "</div>";
    result += "</div>";
    result += "</div>";
    result += "<br />";
    return result;
}

function getOrderTable(id)
{
    var result = "";
    result += "<table id='orderTable' class='table table-striped table-hover table-condensed'>";
    result += getTableHeader();
    result += getTableBody(id);
    result += "</table>";
    return result;
}

function getTableHeader()
{
    var result = "";
    result += "<thead>"
    result += "<tr><td>商品</td><td>单价</td><td>数量</td><td>总价</td><td>单重</td><td>总重</td><td>其他</td><td></td></tr>"
    result += "</thead>";
    return result;
}

function getTableBody(id)
{
    var result = "";
    result += "<tbody>";
    result += addRowPair(id);
    result += "</tbody>";
    return result;
}

function addRowPair(id)
{
    var result = "";
    result += addRow(true, false, id);
    result += addRow(false, true, id);
    return result;
}

function addRow(show, is_button, id)
{
    var result = "";
    result += ("<tr>");
    if( is_button )
    {
        result += ("<td style='vertical-align: middle'>" +  getPrimaryButton("添加商品") + "</td>");
    }
    else
    {
        result += ("<td style='vertical-align: middle'>" + getDropDown("商品列表", id) + "</td>");
    }

    if (show)
    {
        result += ("<td>" + getInputLine("sprice", "disabled") + "</td>");
        result += ("<td>" + getInputLine("amount", "") + "</td>");
        result += ("<td>" + getInputLine("tprice", "") + "</td>");
        result += ("<td>" + getInputLine("sweight", "") + "</td>");
        result += ("<td>" + getInputLine("tweight", "") + "</td>");
        result += ("<td>" + getInputLine("other", "") + "</td>");
        result += ("<td style='text-align:center;vertical-align: middle'><input type='image' src='/imgs/delete.png' class='delete_product' /></td>");
    }
    else
    {
        result += ("<td><label></label></td>");
        result += ("<td><label></label></td>");
        result += ("<td><label></label></td>");
        result += ("<td><label></label></td>");
        result += ("<td><label></label></td>");
        result += ("<td><label></label></td>");
        result += ("<td style='text-align:right'>" + getSuccessButton("保存订单") + "</td>");
    }
    result += "</tr>";
    return result;
}

function getInputLine(name, visible)
{
    var result = "";
    result += "<div class='form-group, input'>";
    result += "<input type='text' class='form-control " + name + "' "+ visible +">";
    result += "</div>";
    return result;
}

function getDropDown(name, id) {
    var result = "<div id='" + id + "' class='dropdown'><button class='btn btn-default dropdown-toggle' type='button' id='menu1' data-toggle='dropdown'>" + name + "<span class='caret'></span></button>";
    result += "<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>";
    if( id == id_customer)
    {
    	for(var i = 0; i < window.json_customer.length; i++)
    	{
    		var firstName = window.json_customer[i].firstName;
    		var lastName = window.json_customer[i].lastName;
    		var uid = window.json_customer[i].id;
    		result += ("<li role='presentation'><a role='menuitem' tabindex='-1' href='#' uid='" + uid + "'>" + lastName + " " + firstName + "</a></li>");
    	}
    }
    else if( id == id_product )
    {
    	for(var i = 0; i < window.json_product.length; i++)
    	{
    		var name = window.json_product[i].name;
    		var uid = window.json_product[i].id;
    		result += ("<li role='presentation'><a role='menuitem' tabindex='-1' href='#' uid='" + uid + "'>" + name + "</a></li>");
    	}
    }
    result += "</ul></div>";
    return result;
}

function find_product(id)
{
	for(var i = 0; i < window.json_product.length; i++)
	{
		var uid = window.json_product[i].id;
		if( uid == id )
		{
			return window.json_product[i];
		}
	}
}

function getPrimaryButton(button_name) {
    return "<button id='btn_product' type='button' class='btn btn-primary'>" + button_name + "</button>";
}

function getSuccessButton(button_name) {
    return "<button id='btn_save' type='button' class='btn btn-success' style='width:90px'>" + button_name + "</button>";
}