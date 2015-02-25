$(document).ready(
    function()
    {
        $("button#me").click(
            function()
            {
//            	var s = "[{\"id\":5629499534213120,\"firstName\":\"wei\",\"lastName\":\"gong\",\"telefon\":\"55555555\",\"address\":\"bei jing\",\"other\":\"\",\"contury\":\"china\",\"province\":\"beijing\",\"city\":\"bei jing\",\"zipcode\":\"011111\"}]";
//            	var json = jQuery.parseJSON( s );
//            	alert(json[0].id);
                $.ajax(
                    {
                        type: "GET",
                        url: "/customer?opt=1",
                        dataType: "json",
                        success: function(data)
                        {
                            alert(data[0].firstName);
                        },
                        error: function(e)
                        {
                            alert(e.message);
                        }
                    }
                );
            }
        );
    }
);