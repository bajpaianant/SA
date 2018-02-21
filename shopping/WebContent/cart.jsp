<%@page import="shopping.database_connection"%>
<%@page import="shopping.Shopping_cart"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,shopping.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<body>
	<%
		HashMap<String, Products> m2 = database_connection.getCartItems(session);
		for (String key : m2.keySet()) {
			String imgDataBase64 = new String(Base64.getEncoder().encode(m2.get(key).getImageData()));
	%>
	<br>
	<%=m2.get(key).getItem_name()%>
	<%=m2.get(key).getItem_price()%>
	<img src="data:image/gif;base64,<%=imgDataBase64%>"
		style="width: 128px; height: 128px;" />
	<%
		}
	%>
</body>
</html>