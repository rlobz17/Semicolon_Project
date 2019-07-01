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
		
				<%
					if(n==1){
						%>
						
							<div class="loginErr"> • სამწუხაროდ, ამ ლოგინით მომხმარებელი ვერ მოიძებნა</div>
				
							<div class="loginSucc"> <a href="/WebQuizProject/registration.jsp" style="color: #2a6113;"> 
								• ჩვენს ვებ-გვერდზე დასარეგისტრირებლად გთხოვთ დააკლიკოთ 
							</a></div>
							
						<%
					} else if(n==2){
						%>
						
							<div class="loginErr"> • შეყვანილი პაროლი არასწორია, სცადეთ თავიდან</div>
						
						<%
					} else if(n==3){
						%>
						
							<div class="loginErr"> • ტექნიკური მიზეზების გამო ავტორიზაცია ვერ ხერხდება</div>
					
						<%
					}
				%>
				
				<div id="loginContent"></div>
		
			</div>
				
			<div id="sidebarContent"></div>
		
		</div>
		
		<div id="footerContent"></div>
		
	</div>
	
</body>

</html>