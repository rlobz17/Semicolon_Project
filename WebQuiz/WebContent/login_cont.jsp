<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<link href="mainPage.css" rel="stylesheet" type="text/css">

</head>
<body>

	<div class="content">
	
		<div class="login">მომხმარებლის ავტორიზაცია</div>
		
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
		
	</div>

</body>

</html>