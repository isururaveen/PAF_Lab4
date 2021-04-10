<%@ page import="com.Item" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>	
<%
	//Insert item-----------------------------------------
	if(request.getParameter("itemCode") != null)
	{
		Item itemObj = new Item();
		
		String stsMsg = itemObj.insertItem(request.getParameter("itemCode"), 
				request.getParameter("itemName"),
				request.getParameter("itemPrice"), 
				request.getParameter("itemDesc"));
		
		System.out.println(stsMsg);
		session.setAttribute("statusMsg", stsMsg);
	}

	//Delete Item------------------------------------------
	if(request.getParameter("itemID") != null)
	{
		Item itemObj =  new Item();
		String stsMsg = itemObj.deleteItem(request.getParameter("itemID"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>
<!DOCTYPE html>
<html>
<head>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>

	<center><h1>Items Management</h1></center>
	<br>
			<div class="container">
				<form class="form-group">
					<div class="form-form-row">
						<form method = "post" action = "items.jsp">
						  	<label class="col-sm-2 col-form-label">Item code:</label> 
						  	<div class="col-sm-3">
						  		<input name="itemCode" tyoe ="text" class="form-control">
						  	</div>
					</div>
					
					<div class="form-form-row">
							<label class="col-sm-2 col-form-label">Item name:</label>
							<div class="col-sm-3">
								<input name="itemName" type="text" class="form-control">
							</div>
					</div>
				
					<div class="form-form-row">
						<label class="col-sm-2 col-form-label">Item price:</label>
						<div class="col-sm-3">
							<input name="itemPrice" type="text" class="form-control">
						</div>
					</div>
				
					<div class="form-form-row">
						<label class="col-sm-2 col-form-label">Item Description: </label>
						<div class="col-sm-3">
							<input name="itemDesc" type="text" class="form-control">
						</div>
					</div>
					<br>
					<input name ="btnSubmit" type="submit" value="Save" class="btn btn-primary"> 
				
				</form>
			
			<br>
			<div class ="alert alert-sucess">
				<%
					out.print(session.getAttribute("statusMsg"));
				%>
			</div>
			<%
				Item itemObj = new Item();
				out.print(itemObj.readItems());
			%>
			</div>
</body>
</html>