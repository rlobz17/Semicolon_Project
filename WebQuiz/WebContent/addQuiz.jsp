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
						<div class="currentQuestion">კითხვის ფორმის შევსება</div>
						<div class="questionTask">
							<div class="addQuestionArea">
								სავარაუდო პასუხების რ-ბა:
								<input type="text" name="AnswersNumber" class="addQuestionField">
							</div>
							
							<div class="addQuestionArea">
								პასუხების დასაშვები რ-ბა:
								<input type="text" name="MaxAnswers" class="addQuestionField">
							</div>
						</div>
						
						<div class="chooseType"> აირჩიეთ კითხვის ტიპი: <br> <br>
							<input type="radio" name="type" checked> ღია კითხვა 1 ან რამდენიმე პასუხით <br>
							<input type="radio" name="type" > დახურული კითხვა 1 პასუხით <br>
							<input type="radio" name="type" > დახურული კითხვა რამდენიმე პასუხით <br>
							<input type="radio" name="type" > შესაბამისობა <br>
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