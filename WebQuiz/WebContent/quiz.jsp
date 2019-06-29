<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="Temp.QuestionManager"%>
<%@page import="Temp.QuizManager"%>
<%@page import="Temp.Quiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String q = request.getParameter("id");
	int quizID = Integer.parseInt(q);
	
	ServletContext cont = getServletContext();
    Object obj = cont.getAttribute("Quiz");
	
    QuizManager quizManager = (QuizManager)obj;
	QuestionManager questionManager = new QuestionManager();
	
	Database.DateBaseManager d = (Database.DateBaseManager)cont.getAttribute("baseManager");
    Statement stm = null;

    try {
    	stm = d.getConnection().createStatement();
    } catch (SQLException e) {
    	// Auto-generated catch block
    	e.printStackTrace();
    }
	
	Quiz quiz = quizManager.getQuiz(quizID, questionManager, stm);
	
	String title = quiz.getQuizName();
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
				
				<h1><%= title %></h1>
		
			</div>
				
			<div id="sidebarContent"></div>
		
		</div>
		
		<div id="footerContent"></div>
		
	</div>
	
</body>
</html>