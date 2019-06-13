<%@page import="java.util.ArrayList"%>
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

username = Account.whitespaceChange(username);

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
				
				String date = acc.getRegistrationDate().toString();
				String mail = acc.getMail();
				String imgurl = acc.getImgUrl();
				
				int quizesTaken = acc.getQuizesTaken();
				int quizesCreated = acc.getQuizesCreted();
				ArrayList<String> friends = acc.getFriendsList();
				
				String first = acc.getFirstName();
				String last = acc.getLastName();
				
				if(first==null){
					first = "";
				}
				
				if(last==null){
					last = "";
				}
				
				if(imgurl==null){
					imgurl = "https://cdn3.iconfinder.com/data/icons/users-6/100/4-512.png";
				}
				
				%>
				
				<table class="profileTable">
				<tbody>
				
					<tr>
						<td colspan="2" 
							style="
    							background: linear-gradient(to bottom, #f1f0f0 0%,#edecec 28%,#dddcdc 100%);
    							text-shadow: #fff 0px 1px 0px;
    							padding-left: 30%;
    							border: 1px solid #ccc;
    							color: #696969;
    							font-size: 19px;
						">
							მომხმარებლის ინფორმაცია
						</td>
					
					</tr>
		
					<tr>
						<td colspan="2">
							<img src="<%= imgurl %>" alt="" 
								style="
    								width: 150px;
    								height: 150px;
    								margin: auto;
    								padding-left: 40%;
							">
						</td>
					
					</tr>
				
					<tr>
						<td>
							ლოგინი:
						</td>
					
						<td>
							<%= username %>
						</td>
					</tr>
				
					<tr>
						<td>
							მეილი:
						</td>
					
						<td>
							<%= mail %>
						</td>
					</tr>
					
					<tr>
						<td>
							სახელი:
						</td>
					
						<td>
							<%= first %>
						</td>
					</tr>
					
					<tr>
						<td>
							გვარი:
						</td>
					
						<td>
							<%= last %>
						</td>
					</tr>
					
					<tr>
						<td>
							რეგისტრაციის თარიღი:
						</td>
					
						<td>
							<%= date %>
						</td>
					</tr>
					
					<tr>
						<td>
							შექმნილი ქვიზები:
						</td>
							
						<td>
							<%= quizesCreated %>
						</td>
					</tr>
					
					<tr>
						<td>
							გაკეთებული ქვიზები:
						</td>
					
						<td>
							<%= quizesTaken %>
						</td>
					</tr>
					
					<tr>
						<td>
							მეგობრები:
						</td>
					
						<td>
							
						</td>
					</tr>
					
		
				</tbody>
				</table>
				
				<%
			}
		%>
		
		</div>
				
		<div id="footerContent"></div>
		
	</div>	
	
</body>

</html>