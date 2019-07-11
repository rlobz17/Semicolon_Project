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
	
	<div class="top-menu">
		<div id="top-menu">
			<div id="css-menu">
				<ul>
					<li><a href="/WebQuizProject"><span>მთავარი გვერდი</span></a></li>
					<li><a href="/WebQuizProject/addQuiz.jsp">ქვიზის დამატება</a></li>
					<li><a href="/">კონტაქტი</a></li>
					<li class='active has-sub'><a href='#'><span>ადმინისტრაცია</span></a><ul>
						<li class='has-sub'><a href='/WebQuizProject/Profile.jsp?username=snoza17'><span>snoza17</span></a>
						<li class='has-sub'><a href='/WebQuizProject/Profile.jsp?username=rlobz17'><span>rlobz17</span></a>
						<li class='has-sub'><a href='/WebQuizProject/Profile.jsp?username=dpopk17'><span>dpopk17</span></a>
						<li class='has-sub'><a href='/WebQuizProject/Profile.jsp?username=asurg17'><span>asurg17</span></a>
						<li class='has-sub'><a href='/WebQuizProject/Profile.jsp?username=dkvel17'><span>dkvel17</span></a>
					<ul> </ul> </li> </ul> </li>
				</ul>
			</div>
		</div>
	</div>

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