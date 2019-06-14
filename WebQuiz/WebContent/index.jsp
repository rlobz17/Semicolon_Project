<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<link href="mainPage.css" rel="stylesheet" type="text/css">

<title>WebQuiz</title>

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
	$("#sidebarContent").load("sidebar.jsp"); 
	});
</script>

</head>

<body>

	<div class="body">
	
		<div id="headerContent"></div>
		
		<div class="content"> 
		
			<div class="navigation">
			
			</div>
			
			<div id="sidebarContent"></div>
		
		</div>
		
		<div id="footerContent"></div>
		
	</div>
	
</body>

</html>