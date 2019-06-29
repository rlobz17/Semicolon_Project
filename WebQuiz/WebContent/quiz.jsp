<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String quiz = request.getParameter("id");
	int quizID = Integer.parseInt(quiz);
%>
    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mainPage.css"/>

<title>WebQuiz</title>

<script src="https://code.jquery.com/jquery-1.10.2.js"></script> 

<script> 
   	$(function(){
	$("#headerContent").load("/WebQuizProject/header.jsp"); 
	});
</script>

<script> 
   	$(function(){
	$("#footerContent").load("/WebQuizProject/footer.jsp"); 
	});
</script>

<script> 
   	$(function(){
	$("#sidebarContent").load("/WebQuizProject/sidebar.jsp"); 
	});
</script>

</head>

<body>
	
	<div class="body">
	
		<div id="headerContent"></div>
		
		<div class="content"> 
			
			<div class="mainContent">		
				
				<h1><%= quizID %></h1>
		
			</div>
				
			<div id="sidebarContent"></div>
		
		</div>
		
		<div id="footerContent"></div>
		
	</div>
	
</body>
</html>