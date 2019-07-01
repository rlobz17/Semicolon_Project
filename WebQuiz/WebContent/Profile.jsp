<%@page import="java.util.Date"%>
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

String user = (String)request.getSession().getAttribute("username");
Account viewerAcc = m.getAccount(user, stm);

boolean myAccount = false;
boolean isAdmin = false;

if(user!=null){
	myAccount = user.equals(username);
	isAdmin = viewerAcc.isAdmin();
}

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
			
			<div class="mainContent">
				
				<%
					if(acc==null){ %>
						
						<div class="loginErr"> • ასეთი მომხმარებელი ბაზაში არ მოიძებნა</div>
						
						<%
					} else{
						
						Date dat = acc.getRegistrationDate();
						
						int year = dat.getYear() + 1900;
						int month = dat.getMonth() + 1;
						int day = dat.getDate();
						
						int hour = dat.getHours();
						int min = dat.getMinutes();
						
						String dd = "" + day;
						String mm = "" + month;
						
						String hourr = "" + hour;
						String minn = "" + min;
						
						if(day<10){
							dd = "0" + day;
						}
						
						if(month<10){
							mm = "0" + month;
						}
						
						if(hour<10){
							hourr = "0" + hour;
						}
						
						if(min<10){
							minn = "0" + min;
						}
						
						String date = dd + "/" + mm + "/" + year + ", " + hourr + ":" + minn;
						
						String mail = acc.getMail();
						String imgurl = acc.getImgUrl();
						
						int quizesTaken = acc.getQuizesTaken();
						int quizesCreated = acc.getQuizesCreated();
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
				
			<%
				if(myAccount || isAdmin){
					%>
						<div class="profileEdit">
						
							<div class="login">მონაცემების ცვლილება</div>
							
							<form action="/" method="POST">
							
								<table class="loginTable"><tbody>
											
									<tr>
										<td>
											ახალი სახელი:
										</td>
										<td>
											<input id="firstname" type="text" name="firstname" class="loginInput">
										</td>
									</tr>
											
									<tr>
										<td>
											ახალი გვარი:
										</td>
										<td>
											<input id="lastname" type="text" name="lastname" class="loginInput">
										</td>
									</tr>
											
									<tr>
										<td>
											ახალი მეილი:
										</td>
										<td>
											<input id="mail" type="text" name="mail" class="loginInput">
										</td>
									</tr>
									
								
								</tbody></table>
										
								<label><input value="შეცვლა" class="logButt" type="submit"></label>
							
							</form>
						
						</div>
						
						
						<div class="profileEdit">
						
							<div class="login">პაროლის ცვლილება</div>
							
							<form action="/" method="POST">
							
								<table class="loginTable"><tbody>
											
									<tr>
										<td>
											ძველი პაროლი:*
										</td>
										<td>
											<input id="old_password" type="password" name="old_password" class="loginInput">
										</td>
									</tr>
									
									<tr>
										<td>
											ახალი პაროლი:*
										</td>
										<td>
											<input id="new_password" type="password" name="new_password" class="loginInput">
										</td>
									</tr>
									
								
								</tbody></table>
										
								<label><input value="შეცვლა" class="logButt" type="submit"></label>
							
							</form>
						
						</div>
						
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