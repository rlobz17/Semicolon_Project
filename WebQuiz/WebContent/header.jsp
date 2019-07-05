<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String user = (String)request.getSession().getAttribute("username");

	boolean logged = false;
	if(user!=null) logged = true;
%>
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
	
		<%
			if(!logged){
				%>
				
				<div class="loginButt">
					<a href="/WebQuizProject/login.jsp" class="loginTxt"> ავტორიზაცია </a>
				</div>
				
				<div class="loginButt">
					<a href="/WebQuizProject/registration.jsp" class="loginTxt"> რეგისტრაცია </a>
				</div>
				
		<%
			} else{
				%>
				
				<div class="loginButt">
					<a href="/WebQuizProject/Profile.jsp?username=<%= user %>" class="myProfileTxt"> პროფილი </a>
				</div>
				
				<div class="loginButt">
					<a href="/WebQuizProject/index.jsp?log=out" class="logoutTxt"> გასვლა </a>
				</div>
				
				<%
			}
		%>
		
		<div>
			<form action="/WebQuizProject/search/searchServlet" method="get">
                <input type="hidden" name="do" value="search">
                <input type="text" name="search" class="searchInput" placeholder="შეიყვანეთ საძიებო სიტყვა...">
            </form>
		</div>
		
	</div>
	
</body>

</html>