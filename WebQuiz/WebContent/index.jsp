<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebQuiz</title>
<link href="mainPage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="body">
		<div class="header">
			<div class="logo">
				<a href="/WebQuiz"><img src="http://www.picz.ge/img/s2/1906/8/d/d4c61c920969.png"></a>
			</div>
		</div>
		
		<div class="searchArea">
			<div class="loginButt">
				<a href="/WebQuiz" class="loginTxt"> ავტორიზაცია </a>
			</div>
			<div class="loginButt">
				<a href="/WebQuiz" class="loginTxt"> რეგისტრაცია </a>
			</div>
			<div>
				<form action="search/searchServlet" method="post">
                	<input type="hidden" name="do" value="search">
                	<input type="text" name="input" class="searchInput" placeholder="შეიყვანეთ საძიებო სიტყვა...">
            	</form>
			</div>
		</div>
		
		<div class="footer">
			<div class="footLeft"> Copyright © 2019 | All Rights Reserved </div>
			<div class="footRight"> Software Engineering: Semicolon; </div>
		</div>
	</div>
</body>
</html>