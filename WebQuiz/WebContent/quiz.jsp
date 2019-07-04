<%@page import="java.sql.Connection"%>
<%@page import="Temp.AccountManager"%>
<%@page import="java.util.Date"%>
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
	
	Database.DateBaseManager d = (Database.DateBaseManager)cont.getAttribute("baseManager");
   	
	Connection con = d.getConnection();
	
	Quiz quiz = quizManager.getQuiz(quizID, con);
	
	
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
			
				<%
					if(quiz==null){
						
						%>
						
							<div class="loginErr"> • ასეთი ქვიზი ვერ მოიძებნა</div>
							
						
						<%
						
					} else{
						String title = quiz.getQuizName();
						String imgURL = quiz.getImgUrl();
						int authorID = quiz.getCreatorID();
						
						Date dat = quiz.getCreationData();
						int quizDone = quiz.getQuizTaken();
						
						int year = dat.getYear() + 1900;
						int month = dat.getMonth() + 1;
						int day = dat.getDate();
						
						int hour = dat.getHours();
						int min = dat.getMinutes();
						
						String dd = "" + day;
						String mm = "" + month;
						
						String hourr = "" + hour;
						String minn = "" + min;
						
						if(day<10){
							dd = "0" + day;
						}
						
						if(month<10){
							mm = "0" + month;
						}
						
						if(hour<10){
							hourr = "0" + hour;
						}
						
						if(min<10){
							minn = "0" + min;
						}
						
						String date = dd + "/" + mm + "/" + year + ", " + hourr + ":" + minn;
						
						Object objAcc = cont.getAttribute("manager");
						
					    AccountManager accM = (AccountManager)objAcc;
					    
					    String author = accM.getAccountUsername(authorID, con);
					    
					    
					    %>
					    
					    	<div class="fullQuiz">
					
								<div class="fullTitle"> 
									<%= title%>
								</div>
								
								<div class="fullstoryImg">
									<img src="<%= imgURL %>">
								</div>
								
								<div class="startQuiz">
									<a href="/" class="quizTxt"> ქვიზის დაწყება </a>
								</div>
								
								<div class="shortstoryLower">
									<ul class="meta grey">
									<li class="meta_author">
									<img src="http://www.picz.ge/img/s2/1811/5/6/67d913510a23.png" class="icon icon-author">
										<a href="http://localhost:8080/WebQuizProject/Profile.jsp?username=<%= author%>">	<%= author %> </a>
									</li>
									<span class="grey" style="margin-left: 2%; margin-right: 2%;"><%= date %></span>
									<li class="meta_coms"> <img src="http://www.picz.ge/img/s1/1811/5/1/19e64068e570.png" class="icon icon-coms"> <%= quizDone %> </li>
									</ul>
								</div>
								
							</div>
					    	
					    <%
					}
				%>	
		
			</div>
				
			<div id="sidebarContent"></div>
		
		</div>
		
		<div id="footerContent"></div>
		
	</div>
	
</body>
</html>