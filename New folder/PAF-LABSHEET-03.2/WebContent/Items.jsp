<link rel="stylesheet" href="Views/bootstrap.min.css">
<%@page import="com.Item" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<% 
	//Insert iten---------------------------------
	if (request.getParameter("itemCode") != null){
			
			Item item = new Item();
			
			String stsmsg = item.insertItem(request.getParameter("itemCode"), 
							 	request.getParameter("itemName"), 
							 	request.getParameter("itemPrice"), 
							 	request.getParameter("itemDescription"));
			
			session.setAttribute("statusMsg",stsmsg);
		
		}

	
	//Delete item---------------------------------
	if(request.getParameter("itemID") != null){
		
		Item item = new Item();
	    
		String stsMsg1 = item.deleteitems(request.getParameter("itemID"));
		session.setAttribute("statusmsg",stsMsg1);
		
		
		
	}
	

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Items Management</h1>
	<form method="post" action="Items.jsp">
		Item Code: <input type="text" name="itemCode"></input><br>
		Item Name: <input type="text" name="itemName"></input><br>
		Item Price: <input type="text" name="itemPrice"></input><br>
		Item Description: <input type="text" name="itemDescription"></input><br>
		<input type="submit" name="btnSubmit" value="Save"></input>	
	</form>
	
	<% 
		out.print(session.getAttribute("statusMsg"));
	%>
	
	
	<% 
		 Item item = new Item();
		 out.print(item.readItems());
	%>	
	

</body>
</html>