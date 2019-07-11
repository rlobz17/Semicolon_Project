<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String p = request.getParameter("error_id");
int n = 0;
if(p!=null) n = Integer.parseInt(p);
%>
    
<!DOCTYPE html>
<html>

<head>

<title>ავტორიზაცია</title>

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
		
				
							<div class="loginSucc"> <a href="/WebQuizProject/registration.jsp" style="color: #2a6113; text-decoration: none;"> 
								• ჩვენს ვებ-გვერდის ადმინისტრაციასთან საკონტაქტოდ მოიწერეთ მეილი <A HREF="mailto:rlobz17@freeuni.edu.ge">აქ</A>
							</a></div>
						
						
			</div>
				
			<div id="sidebarContent"></div>
		
		</div>
		
		<div id="footerContent"></div>
		
	</div>
	
</body>

