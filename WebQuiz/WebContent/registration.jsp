<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String p = request.getParameter("info_id");
int n = -1;
if(p!=null) n = Integer.parseInt(p);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mainPage.css"/>

<title>რეგისტრაცია</title>
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
	$("#registrationContent").load("/WebQuizProject/registration_cont.jsp"); 
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
					if(n==1){
						%>
						
							<div class="loginErr"> • ლოგინი უკვე დაკავებულია</div>
							<div id="registrationContent"></div>
							
						<%
					} else if(n==2){
						%>
						
							<div class="loginErr"> • მეილი უკვე დაკავებულია</div>
							<div id="registrationContent"></div>
						
						<%
					} else if(n==3){
						%>
						
							<div class="loginErr"> • მეილი და ლოგინი უკვე დაკავებულია</div>
							<div id="registrationContent"></div>
					
						<%
					} else if(n==0){
						%>
						
							<div class="loginSucc"> • გილოცავთ, თქვენ წარმატებით დარეგისტრირდით ჩვენს საიტზე</div>
							
							<div class="loginSucc"> <a href="/WebQuizProject/login.jsp" style="color: #2a6113;"> 
								• ახლა უკვე თქვენ შეგიძლიათ ლოგინით და პაროლით გაიაროთ ავტორიზაცია 
							</a></div>
							
						<%
					} else if(n==4){
						%>
						
							<div class="loginErr"> • პაროლები ერთმანეთს არ ემთხვევა</div>
							<div id="registrationContent"></div>
					
						<%
					}else if(n==5){
						%>
							
							<div class="loginErr"> • პაროლის უნდა იყოს სულ მცირე 6 სიგრძის</div>
							<div id="registrationContent"></div>
					
						<%
					} else{
						%>
						
							<div id="registrationContent"></div>
				
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