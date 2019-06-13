<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<link href="mainPage.css" rel="stylesheet" type="text/css">

</head>

<body>

	<div class="header">
		<div class="logo">
			<a href="/WebQuizProject"><img src="http://www.picz.ge/img/s2/1906/8/d/d4c61c920969.png"></a>
		</div>
	</div>
		
	<div class="searchArea">
	
		<div class="loginButt">
			<a href="/WebQuizProject/login.jsp" class="loginTxt"> ავტორიზაცია </a>
		</div>
		
		<div class="loginButt">
			<a href="/WebQuizProject/registration.jsp" class="loginTxt"> რეგისტრაცია </a>
		</div>
		
		<div>
			<form action="search/searchServlet" method="post">
                <input type="hidden" name="do" value="search">
                <input type="text" name="input" class="searchInput" placeholder="შეიყვანეთ საძიებო სიტყვა...">
            </form>
		</div>
		
	</div>
	
</body>

</html>