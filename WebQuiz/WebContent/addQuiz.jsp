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

<title>ქვიზის დამატება</title>

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
				%>
				
				<form action="/WebQuizProject/Quiz/addQuizServlet" method="POST">
					
					<div class="question">
						<div class="currentQuestion">ქვიზის დამატება</div>
						<div class="questionTask">
							აირჩიეთ ქვიზის კატეგორია:
						</div>
						
						<div class="chooseType">
							<input type="radio" name="category" value="1"> კულტურა <br>
							<input type="radio" name="category" value="2"> რელიგია <br>
							<input type="radio" name="category" value="3"> პოლიტიკა <br>
							<input type="radio" name="category" value="4"> სპორტი <br>
							<input type="radio" name="category" value="5"> ისტორია <br>
							<input type="radio" name="category" value="6"> მეცნიერება <br>
							<input type="radio" name="category" value="7"> ხელოვნება <br>
							<input type="radio" name="category" value="8"> საბავშვო <br>
							<input type="radio" name="category" value="9"> მედიცინა <br>
							<input type="radio" name="category" checked value="10"> სხვა
						</div>
						
						<input value="გაგრძელება" class="finishQuiz" type="submit">
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