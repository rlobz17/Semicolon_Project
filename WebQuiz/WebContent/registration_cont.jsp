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

</head>
<body>
	<div class="content">
		<div class="login">ახალი მომხმარებლის რეგისტრაცია</div>
		
		<%
			
			if(user==null){
				%>
					
					<form action="/WebQuizProject/login/RegistrationServlet" method="POST">
					
						<table class="loginTable"><tbody>
						
							<tr>
								<td>
									ლოგინი:*
								</td>
								<td>
									<input id="username" type="text" name="username" class="loginInput">
								</td>
							</tr>
									
							<tr>
								<td>
									პაროლი:*
								</td>
								<td>
									<input id="password" type="password" name="password" class="loginInput">
								</td>
							</tr>
							
							<tr>
								<td>
									გაიმეორეთ პაროლი:*
								</td>
								<td>
									<input id="repeatPass" type="password" name="repeatPass" class="loginInput">
								</td>
							</tr>
									
							<tr>
								<td>
									სახელი:
								</td>
								<td>
									<input id="firstname" type="text" name="firstname" class="loginInput">
								</td>
							</tr>
									
							<tr>
								<td>
									გვარი:
								</td>
								<td>
									<input id="lastname" type="text" name="lastname" class="loginInput">
								</td>
							</tr>
									
							<tr>
								<td>
									მეილი:*
								</td>
								<td>
									<input id="mail" type="text" name="mail" class="loginInput">
								</td>
							</tr>
							
						
						</tbody></table>
								
						<label><input value="დარეგისტრირება" class="logButt" type="submit"></label>
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