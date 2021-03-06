<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<title>რეგისტრაცია</title>

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
	$("#regContent").load("/WebQuizProject/registration_cont.jsp"); 
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
		
				<div class="loginErr"> • შეიყვანეთ აუცილებელი ველები! გაითვალისწინეთ, ლოგინი უნდა შეიცავდეს მხოლოდ ლათინურ სიმბოლოებს, ციფრებს, სფეისს ან . -</div>
				<div id="regContent"></div>
				
			</div>
			
			<div id="sidebarContent"></div>
		
		</div>
			
		<div id="footerContent"></div>
		
	</div>
	
</body>

</html>