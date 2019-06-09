<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ავტორიზაცია</title>
<meta charset="UTF-8">
<link href="mainPage.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script> 
<script> 
   	$(function(){
	$("#headerContent").load("header.jsp"); 
	});
</script>
<script> 
   	$(function(){
	$("#footerContent").load("footer.jsp"); 
	});
</script>
</head>
<body>
	<div class="body">
		<div id="headerContent"></div>
		<div class="content">
			<div class="login">მომხმარებლის ავტორიზაცია</div>
				<form action="login/LoginServlet" method="POST">
					
					<table class="loginTable"><tbody>
		
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
			
		
					</tbody></table>
				
					<label style="margin-left: 50%;"><input value="შესვლა" type="submit"></label>
				</form>
		</div>
		<div id="footerContent"></div>
	</div>
</body>
</html>