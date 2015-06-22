var order_counter = 0;
var id_customer = "cust";
var id_product = "prod";
var id_status = "order_status";
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
        
        $("button#remove_all_order").click(

                function ()
                {
                    removeAllOrders();
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
        			$(this).parents('.dropdown').find('.dropdown-toggle').html(selText+'<span class="caret" uid="' + prod_id  +'"></span>').end().children( '.dropdown-toggle' ).dropdown( 'toggle' );

        			var product = find_product(prod_id);
        			if( product )
        			{
        				var row = $(this).closest("tr");
        				var sp = product.defaultPrice;
        				var amt = product.defaultAmount;
        				var tp = amt * sp;
        				var swt = product.nettoWeigth;
        				var twt = amt * swt;
        				$(row).find(".sprice").val(sp);
        				$(row).find(".amount").val(amt);
        				$(row).find(".tprice").val(tp.toFixed(2));
        				$(row).find(".sweight").val(swt);
        				$(row).find(".tweight").val(twt);
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

        $("#order_list").on("click", "#btn_save", function(){
        	var table = $(this).closest("table");
        	var order = new Object();
        	var order_id = table.attr("order_id");
        	if( order_id == undefined)
        	{
        		order.id = 0;
        	}
        	else
        	{
        		order.id = order_id;
        	}
        	order.customer = window.userID;
        	var items = [];
        	table.find('tbody > tr').each(function(i){
        		var productID = $(this).find("span").attr("uid");
        		var item_id = $(this).attr("item_id");
        		if (typeof( productID ) != "undefined" && productID != 0)
        		{
        			var $tds = $(this).find('td');
        			var item = new Object();
        			item.item_id = item_id;
        			item.product = productID;
        			item.sprice = $tds.eq(1).find("input").val();
        			item.amount = $tds.eq(2).find("input").val();
        			item.tprice = $tds.eq(3).find("input").val();
        			item.sweight = $tds.eq(4).find("input").val();
        			item.tweight = $tds.eq(5).find("input").val();
        			item.other = $tds.eq(6).find("input").val();
        			items.push(item);
        		}
        	});

        	order.items = items;
        	sendJsonToDataServlet(JSON.stringify(order));
        });
        
        loadOrders();
    }
);

function removeAllOrders()
{
	$.ajax(
		    {
		        url : "/data?entity=2&opt=5&uid=" + window.userID,
		        type: "GET",
		        success:function(data, textStatus, jqXHR)
		        {
		        	alert("success");
		        	location.reload();
		        },
		        error: function(jqXHR, textStatus, errorThrown)
		        {
		        	alert("error");
		        }
		    });
}

function loadOrders()
{
	$.ajax(
		    {
		        url : "/data?entity=2&opt=2&uid=" + window.userID,
		        type: "GET",
		        success:function(data, textStatus, jqXHR)
		        {
		        	var orders = data;
		        	if( orders != undefined)
		        	{
		        		for( var i = 0; i < orders.length; i++)
		        		{
			        		var order = orders[i];
			        		addOrderWithData(order);
		        		}
		        	}
		        },
		        error: function(jqXHR, textStatus, errorThrown)
		        {
		        	alert("error");
		        }
		    });
}

function sendJsonToDataServlet(data)
{
	if(data != "undefined")
	{
		$.ajax(
			    {
			        url : "/data?entity=2&opt=0",
			        type: "POST",
			        data : {content:data},
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
}

function addOrder()
{
    var result = "<hr /><div id='order'>";
    result += getOrderTitle(id_customer);
    result += getOrderTable(id_product);
    result += "</div>";
    $("div#order_list").append(result);
}

function addOrderWithData(order)
{
	var result = "<hr /><div id='order'>";
    result += getOrderTitleWithData(id_customer, order);
    result += getOrderTableWithData(id_product, order);
    result += "</div>";
    $("div#order_list").append(result);
}

function getOrderTitle(id, order_id)
{
    var result = "";
    result += "<div id='order'>";
    result += "<div id='order_title' class='row'>";
    result += "<div class='col-sm-2'>";
    result += getDropDown("选择客户 ", id);
    result += "</div>";
    result += "<div class='col-sm-2'>";
    result += "<a>订单号: " + order_id + "</a>";
    result += "</div>";
    result += "<div class='col-sm-5'>";
    result += "<lable />";
    result += "</div>";
    result += "<div class='col-sm-1'>总额: ";
    result += "</div>";
    result += "</div>";
    result += "</div>";
    result += "<br />";
    return result;
}

function getOrderTitleWithData(id, order)
{
    var result = "";
    result += "<div id='order'>";
    result += "<div id='order_title' class='row'>";
    result += "<div class='col-sm-2'>";
    result += getDropDown("选择客户 ", id);
    result += "</div>";
    result += "<div class='col-sm-2'>";
    result += "<a class='order_id'>订单号: " + order.id + "</a>";
    result += "</div>";
    result += "<div class='col-sm-5'>";
    result += "<lable />";
    result += "</div>";
    result += "<div class='col-sm-1'>总额: ";
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

function getOrderTableWithData(id, order)
{
    var result = "";
    result += "<table id='orderTable' class='table table-striped table-hover table-condensed' order_id='" + order.id + "'>";
    result += getTableHeader();
    result += "<tbody>";
    var items = order.orders;
    for(var i = 0; i < items.length; i++)
    {
    	result += addRowWithData(true, false, id, items[i]);
    }
    result += addRow(false, true, id);
    result += "</tbody>";
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
    if( is_button )
    {
    	result += ("<tr>");
        result += ("<td style='vertical-align: middle'>" +  getPrimaryButton("添加商品") + "</td>");
    }
    else
    {
    	result += ("<tr item_id=0>");
        result += ("<td style='vertical-align: middle'>" + getDropDown("商品列表 ", id) + "</td>");
    }

    if (show)
    {
        result += ("<td>" + getInputLine("sprice", "") + "</td>");
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
        result += ("<td style='text-align:right'>"+ getDropDown("订单状态", id_status) +"</td>");
        result += ("<td style='text-align:right'>" + getSuccessButton("保存订单") + "</td>");
    }
    result += "</tr>";
    return result;
}

function addRowWithData(show, is_button, id, item)
{
    var result = "";
    result += ("<tr item_id=" + item.id + ">");
    result += ("<td style='vertical-align: middle'>" + getDropDownWithData("商品列表 ", id, item) + "</td>");
    result += ("<td>" + getInputLineWithValue("sprice", "", item.singlePrice) + "</td>");
    result += ("<td>" + getInputLineWithValue("amount", "", item.amount) + "</td>");
    result += ("<td>" + getInputLineWithValue("tprice", "", item.totalPrice) + "</td>");
    result += ("<td>" + getInputLineWithValue("sweight", "", item.singleWeight) + "</td>");
    result += ("<td>" + getInputLineWithValue("tweight", "", item.totalWeight) + "</td>");
    result += ("<td>" + getInputLineWithValue("other", "", item.others) + "</td>");
    result += ("<td style='text-align:center;vertical-align: middle'><input type='image' src='/imgs/delete.png' class='delete_product' /></td>");
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

function getInputLineWithValue(name, visible, value)
{
    var result = "";
    result += "<div class='form-group, input'>";
    result += "<input type='text' " + "value='" + value + "' " + "class='form-control " + name + "' "+ visible +">";
    result += "</div>";
    return result;
}

function getDropDown(name, id) 
{
    var result = "<div id='" + id + "' class='dropdown'><button class='btn btn-default dropdown-toggle' type='button' id='menu1' data-toggle='dropdown'>" + name + "<span class='caret' uid=0 ></span></button>";
    result += "<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>";
    if( id == id_customer)
    {
    	for(var i = 0; i < window.json_customer.length; i++)
    	{
    		var firstName = window.json_customer[i].firstName;
    		var lastName = window.json_customer[i].lastName;
    		var uid = window.json_customer[i].id;
    		result += ("<li role='presentation'><a role='menuitem' tabindex='-1' href='#' uid='" + uid + "'>" + lastName + " " + firstName + " " + "</a></li>");
    	}
    }
    else if( id == id_product )
    {
    	for(var i = 0; i < window.json_product.length; i++)
    	{
    		var name = window.json_product[i].name;
    		var uid = window.json_product[i].id;
    		result += ("<li role='presentation'><a role='menuitem' tabindex='-1' href='#' uid='" + uid + "'>" + name + " " + "</a></li>");
    	}
    }
    else if( id == id_status )
    {
    	var status = getStatusList();
    	
    	for( var i = 0; i < status.length; i++)
    	{
    		result += ("<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>" + status[i] + "</a></li>");
    	}
    }
    result += "</ul></div>";
    return result;
}

function getDropDownWithData(name, id, item)
{
	var temp = "";
	var current_id;
	var current_name;
    if( id == id_customer)
    {
    	for(var i = 0; i < window.json_customer.length; i++)
    	{
    		var firstName = window.json_customer[i].firstName;
    		var lastName = window.json_customer[i].lastName;
    		var uid = window.json_customer[i].id;
    		temp += ("<li role='presentation'><a role='menuitem' tabindex='-1' href='#' uid='" + uid + "'>" + lastName + " " + firstName + " " + "</a></li>");
    	}
    }
    else if( id == id_product )
    {
    	for(var i = 0; i < window.json_product.length; i++)
    	{
    		var uid = window.json_product[i].id;
    		var item_name = window.json_product[i].name;
    		if( item.product == uid)
    		{
    			current_id = uid;
    			current_name = item_name
    		}
    		temp += ("<li role='presentation'><a role='menuitem' tabindex='-1' href='#' uid='" + uid + "'>" + item_name + " " + "</a></li>");
    	}
    }
    else if( id == id_status )
    {
    	var status = getStatusList();
    	
    	for( var i = 0; i < status.length; i++)
    	{
    		temp += ("<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>" + status[i] + "</a></li>");
    	}
    }
    
    var result = "<div id='" + current_id + "' class='dropdown'><button class='btn btn-default dropdown-toggle' type='button' id='menu1' data-toggle='dropdown'>" + current_name + " <span class='caret' uid=0 ></span></button>";
    result += "<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>";
    result += temp;
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

function getStatusList()
{
	var status = ["订单状态", "确认订单", "购买商品", "准备发货", "货物已发", "确认收款", "订单结束"];
	
	return status;
}

function getPrimaryButton(button_name) 
{
    return "<button id='btn_product' type='button' class='btn btn-primary'>" + button_name + "</button>";
}

function getSuccessButton(button_name) 
{
    return "<button id='btn_save' type='button' class='btn btn-success' style='width:90px'>" + button_name + "</button>";
}