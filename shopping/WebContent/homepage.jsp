<%@page import="shopping.database_connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,shopping.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage-Shopping</title>
<script type="text/javascript">


function validateForm(e){
	flag1 = validate('uname');
	flag2=validate('pwd');
	return flag1 && flag2;
}
function validate(id){

	var objValue=$('#'+id).val();
	if(objValue==''){
		$('#+id+''2').fadeIn(1000);
		return false;
	}
	return true;
}
	 	
function mclear(id){
	$('#'+id+'2').fadeOut(1000);	
}
</script>

<script src="./js/jquery-3.2.1.min.js"></script>
</head>
<body>

<form action='data_b' method='post' onsubmit='return validateForm(event)'>
<hr noshade>
		username <input type='text' name='uname' id='uname' style="color:black;border:3px  red;" onblur='validate(this.id)' onfocus="mclear(this.id)"/><span id='uname2' style="display:none">Dont leave username blank</span> <br> 
		password <input type='password' name='pwd' id='pwd' style="color:black;border:3px  red;"  onblur='validate(this.id)' onfocus="mclear(this.id)"/><span id='pwd2' style="display:none">Password cannot be blank!</span> <br>
		<input type='submit' />
		<a href="New User?" onclick=fadeIn(1000)>New User?</a>
		<hr>
		Categories:
		<hr>
		<ul class="nav">
  
 
  <li class="button-dropdown">
    <a href="javascript:void(0)" class="dropdown-toggle">
    </a>
    <ul class="dropdown-menu">
      <%  HashMap<String,String> m1= database_connection.getCategories(); 
      	for(String key:m1.keySet()){
     %> 
      
      <li>
        <a  href="categories.jsp?cid=<%=key %>" >
          <%=m1.get(key)%>
          
        </a>
      </li>
      <% } %>
    </ul>
  </li>
  
		
		</body>
</html>