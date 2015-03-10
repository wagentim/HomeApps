var order_counter = 0;
var show_alert_delay_time = 4000;
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
                var row = addRowPair();
                $(this).closest("tbody").append(row);
                $(this).closest("tr").remove();
            }
        );
        
        $("#order_list").on("click", ".dropdown-menu li a",
                function()
                {
        			var selText = $(this).text();
        			$(this).parents('.dropdown').find('.dropdown-toggle').html(selText+'<span class="caret"></span>');
                }
        );
        
        $("button#modify_customer").click(function()
        {
        	window.location.href = "/pages/buymanager/usereditor.jsp";
        });
    }
);

function addOrder()
{
    var result = "<div id='order'>";
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

function getOrderTable()
{
    var result = "";
    result += "<table id='orderTable' class='table table-striped table-hover table-condensed'>";
    result += getTableHeader();
    result += getTableBody();
    result += "</table>";
    return result;
}

function getTableHeader()
{
    var result = "";
    result += "<thead>"
    result += "<tr><td>商品</td><td>单价</td><td>数量</td><td>总价</td><td>单重</td><td>总重</td><td>其他</td></tr>"
    result += "</thead>";
    return result;
}

function getTableBody()
{
    var result = "";
    result += "<tbody>";
    result += addRowPair();
    result += "</tbody>";
    return result;
}

function addRowPair()
{
    var result = "";
    result += addRow(true, false);
    result += addRow(false, true);
    return result;
}

function addRow(show, is_button)
{
    var result = "";
    result += ("<tr>");
    if( is_button )
    {
        result += ("<td>" +  getPrimaryButton("添加商品") + "</td>");
    }
    else
    {
        result += ("<td>" + getDropDown("商品列表") + "</td>");
    }
    
    if (show) 
    {
        result += ("<td>" + getInputLine() + "</td>");
        result += ("<td>" + getInputLine() + "</td>");
        result += ("<td>" + getInputLine() + "</td>");
        result += ("<td>" + getInputLine() + "</td>");
        result += ("<td>" + getInputLine() + "</td>");
        result += ("<td>" + getInputLine() + "</td>");
    } 
    else 
    {
        result += ("<td><label></label></td>");
        result += ("<td><label></label></td>");
        result += ("<td><label></label></td>");
        result += ("<td><label></label></td>");
        result += ("<td><label></label></td>");
        result += ("<td style='text-align:right'>" + getDangerButton("保存") + "</td>");
    }
    result += "</tr>";
    return result;
}

function getInputLine()
{
    var result = "";
    result += "<div class='form-group'>";
    result += "<input type='text' class='form-control' id='input' disabled>";
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
    		result += ("<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>" + lastName + " " + firstName + "</a></li>");
    	}
    }
    result += "</ul></div>";
    return result;
}

function getPrimaryButton(button_name) {
    return "<button id='btn_product' type='button' class='btn btn-primary'>" + button_name + "</button>";
}

function getDangerButton(button_name) {
    return "<button id='btn_save' type='button' class='btn btn-danger' style='width:90px'>" + button_name + "</button>";
}