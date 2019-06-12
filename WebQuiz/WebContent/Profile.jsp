<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="Temp.Account"%>
<%@page import="Temp.AccountManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
ServletContext cont = getServletContext();
Object obj = cont.getAttribute("profile");

AccountManager m = (AccountManager)obj;

String username = request.getParameter("username");

Database.DateBaseManager d = (Database.DateBaseManager)cont.getAttribute("baseManager");
Statement stm = null;

try {
	stm = d.getConnection().createStatement();
} catch (SQLException e) {
	// Auto-generated catch block
	e.printStackTrace();
}

Account acc = m.getAccount(username, stm);

%>
    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<link href="mainPage.css" rel="stylesheet" type="text/css">

<title><%= username %></title>

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

</head>

<body>
	
	<div class="body">
	
		<div id="headerContent"></div>
		
		<div class="content">
				
		<%
			if(acc==null){ %>
				
				<div class="loginErr"> • ასეთი მომხმარებელი ბაზაში არ მოიძებნა</div>
				
				<%
			} else{
				%>
				
				
				
				<%
			}
		%>
		
		</div>
				
		<div id="footerContent"></div>
		
	</div>	
	
</body>

</html>