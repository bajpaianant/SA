<%@page import="shopping.database_connection"%>
<%@page import="shopping.Shopping_cart" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,shopping.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categories</title>
<script type="text/javascript">
function addRemove(key){
		var data = $('#b-'+key).html();
		var addFlag = 0;
		if(data.charAt(0)=='A')//Add to cart option
			addFlag=1;
		
	    $.ajax({
	    	url: "Shopping_cart?option="+addFlag+"&qty=1&key="+key,
	    	success: function(result){
	    
	    	if(result=="{operation:true}"){
		    	if(addFlag==1)
		        	$('#b-'+key).html("Remove from cart");
		    	else
		    		$('#b-'+key).html("Add to cart");
	    	}
	    }});

}
</script>
<script src="./js/jquery-3.2.1.min.js"></script>
</head>
<body>

    <li class="button-dropdown">
    <a href="javascript:void(0)" class="dropdown-toggle">
    </a>
    <ul class="dropdown-menu">
<% String id=request.getParameter("cid");
       HashMap<String,Products> m2= database_connection.getItems(id); 
      	for(String key:m2.keySet()){
      		String imgDataBase64= new String(Base64.getEncoder().encode(m2.get(key).getImageData()));
     %> 
      
      
        
          <br>
         <%=m2.get(key).getItem_name() %> <%=m2.get(key).getItem_price() %>
          <img src="data:image/gif;base64,<%= imgDataBase64 %>" style="width:128px;height:128px;"/>
           <button  id='b-<%=key%>' onclick="addRemove('<%=key%>')">Add To Cart</button>
          
        
      
      <% } %>
      <a  href="cart.jsp" >Show Cart</a>
    </ul>
  </li>
</body>
</html>