<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

</head>

<body>

	<div class="body">
	
		<div id="headerContent"></div>
		
		<div class="loginErr"> • სამწუხაროდ, ამ ლოგინით მომხმარებელი ვერ მოიძებნა</div>
		
		<div class="loginSucc"> <a href="/WebQuizProject/registration.jsp" style="color: #2a6113;"> 
		• ჩვენს ვებ-გვერდზე დასარეგისტრირებლად გთხოვთ დააკლიკოთ 
		</a></div>
		
		<div id="loginContent"></div>
		
		<div id="footerContent"></div>
		
	</div>
	
</body>

</html>