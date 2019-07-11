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

<title>კონტაქტი</title>

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
	$("#sidebarContent").load("/WebQuizProject/sidebar.jsp"); 
	});
</script>

</head>

<body>

	<div class="body">
	
		<div id="headerContent"></div>
		
		<div class="content"> 
			
			<div class="mainContent">		
		
				
							<div class="loginSucc"> <p> 
								• ჩვენს ვებ-გვერდის ადმინისტრაციასთან საკონტაქტოდ მოიწერეთ მეილი ერთ-ერთ მეილზე:
								<br>
								1) <a HREF="mailto:rlobz17@freeuni.edu.ge">rlobz17@freeuni.edu.ge</a>
								<br>
								2) <a HREF="mailto:snoza17@freeuni.edu.ge">snoza17@freeuni.edu.ge</a> 
							</p></div>
						
						
			</div>
				
			<div id="sidebarContent"></div>
		
		</div>
		
		<div id="footerContent"></div>
		
	</div>
	
</body>

