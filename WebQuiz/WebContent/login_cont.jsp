<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	String user = (String)request.getSession().getAttribute("username");

%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<link href="mainPage.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-1.10.2.js"></script> 

<script> 
   	$(function(){
	$("#sidebarContent").load("sidebar.jsp"); 
	});
</script>

</head>
<body>

	<div class="content">
	
		<div class="login">მომხმარებლის ავტორიზაცია</div>
		
		<%
			if(user==null){
				%>
				
					<form action="/WebQuizProject/login/LoginServlet" method="POST">
					
						<table class="loginTable">
						<tbody>
					
						<tr>
							<td>
								ლოგინი:
							</td>
							<td>
								<input id="username" type="text" name="username" class="loginInput">
							</td>
						</tr>
								
						<tr>
							<td>
								პაროლი:
							</td>
							<td>
								<input id="password" type="password" name="password" class="loginInput">
							</td>
						</tr>
					
						</tbody>
						</table>
							
						<label><input value="შესვლა" class="logButt" type="submit"></label>
						
					</form>
				
				<%
			} else{
				%>
				
					<div class="loginErr"> • თქვენ უკვე შესული ხართ ექაუნთში</div>
				
				<%
			}
		%>
		
	</div>

</body>

</html>