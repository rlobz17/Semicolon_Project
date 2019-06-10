<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ავტორიზაცია</title>
<meta charset="UTF-8">
<link href="mainPage.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script> 
<script> 
   	$(function(){
	$("#headerContent").load("header.jsp"); 
	});
</script>
<script> 
   	$(function(){
	$("#footerContent").load("footer.jsp"); 
	});
</script>
<script> 
   	$(function(){
	$("#loginContent").load("login_cont.jsp"); 
	});
</script>
</head>
<body>
	<div class="body">
		<div id="headerContent"></div>
		<div class="loginErr"> • არასწორი ლოგინი ან პაროლი</div>
		<div id="loginContent"></div>
		<div id="footerContent"></div>
	</div>
</body>
</html>