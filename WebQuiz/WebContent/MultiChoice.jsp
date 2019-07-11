<%@page import="Temp.QuestionTypes"%>
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

<title>ღია კითხვა</title>

<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mainPage.css"/>
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
	$("#loginContent").load("/WebQuizProject/login_cont.jsp"); 
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
				if(!logged){
				%>
				
					<div class="loginErr"> • თქვენ არ ხართ შესული ექაუნთში</div>
							
					<div class="loginSucc"> <a href="/WebQuizProject/login.jsp" style="color: #2a6113;"> 
						• გთხოვთ გაიაროთ ავტორიაზაცია
					</a></div>
					
				<%	
				} else{
					QuestionTypes t = new QuestionTypes();
				%>
				
				<form action="/WebQuizProject/Quiz/addQuizServlet" method="POST">
					
					<div class="question">
						<div class="currentQuestion">კითხვის დამატება</div>
						
						<div class="addQuestionArea" style="margin-top: 60px;">
							კითხვა:
							<input type="text" name="answersNum" class="addQuestionField" style="width: 500px;height: 100px;">
						</div>
						
						<div class="addQuestionArea">
							სურათის URL(არასავალდებულო):
							<input type="text" name="answersNum" class="addQuestionField">
						</div>
						
						<% 	String st = response.getHeader("answersNum"); 
							int answersNum = Integer.parseInt(st);
							
							for(int i=0; i<answersNum; i++){
								%>
								
								<div class="answerArea">
									<input type="radio" name="possibleAnswer" checked>
									<%= i+1 %>: <input type="text" name="AnswerField<%= i %>" class="answerField"> 
								</div> 
								<% 
							}
							
						%>
						
						<input value="კითხვის კიდევ დამატება" class="finishQuiz" type="submit" style="width: 300px;margin-left: 25%;">
						<input value="დასრულება" class="finishQuiz" type="submit">
					</div>
					
				</form>
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