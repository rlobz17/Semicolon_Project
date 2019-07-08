<%@page import="History.AccountHistoryManager"%>
<%@page import="java.sql.Connection"%>
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

Connection con = d.getConnection();

username = Account.whitespaceChange(username);

Object accHist = cont.getAttribute("AccHistory");

AccountHistoryManager accountHistory = (AccountHistoryManager)accHist;

Account acc = m.getAccount(username, accountHistory, con);

String user = (String)request.getSession().getAttribute("username");

Account viewerAcc = m.getAccount(user, accountHistory, con);

boolean myAccount = false;
boolean isAdmin = false;

if(user!=null){
	myAccount = user.equals(username);
	isAdmin = viewerAcc.isAdmin();
}

String result = request.getParameter("result");

%>
    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mainPage.css"/>

<title><%= username %></title>

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
						
						double avg = acc.getQuizesAverage();
						
						boolean admin = acc.isAdmin();
						
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
						
						if(result!=null){
						
							if(result.equals("0")){
								%>
									<div class="loginSucc"> • მონაცემები წარმატებით შეიცვალა</div>
								<%
							} else if(result.equals("1")){
								%>
									<div class="loginErr"> • პაროლები ერთმანეთს არ ემთხვევა ან ფორმატი არასწორია</div>
								<%	
							} else if(result.equals("2")){
								%>
									<div class="loginErr"> • შეყვანილი პაროლი არასწორია</div>
								<%
							} else if(result.equals("3")){
								%>
									<div class="loginErr"> • მეილი უკვე დაკავებულია</div>
								<%
							}
						
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
									<a href="/WebQuizProject/user_quizes.jsp?user=<%= acc.getUserID() %>" class="seeQuizes">[ქვიზების ნახვა]</a>
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
									საშუალო ქულა:
								</td>
							
								<td>
									<%= avg %>
								</td>
							</tr>
							
							<tr>
								<td>
									სტატუსი:
								</td>
								
								<%
									if(admin){
										%>
										
										<td>
											ადმინისტრატორი
										</td>
										
										<%
									} else{
										%>
										
										<td>
											მომხმარებელი
										</td>
										
										<%
									}
								%>
								
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
							
							<form action="/WebQuizProject/login/ProfileEditServlet" method="POST">
								<input type="hidden" name="user" value="<%= username %>" />
								
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
									
									<tr>
										<td>
											ახალი სურათი (URL):
										</td>
										<td>
											<input id="img" type="text" name="img" class="loginInput">
										</td>
									</tr>
									
									<%
										if(isAdmin){
											%>
											
											<tr>
												<td>
													სტატუსის შეცვლა:
												</td>
												<td>
													<input type="checkbox" name="status" > 
													
												</td>
											</tr>
											
											<%
										}
									%>
									
								
								</tbody></table>
										
								<label><input value="შეცვლა" class="logButt" type="submit"></label>
							
							</form>
						
						</div>
						
						
						<div class="profileEdit">
						
							<div class="login">პაროლის ცვლილება</div>
							
							<form action="/WebQuizProject/login/ChangePasswordServlet" method="POST">
								
								<input type="hidden" name="user" value="<%= username %>" />
							
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
									
									<tr>
										<td>
											გაიმეორეთ პაროლი:*
										</td>
										<td>
											<input id="repeatPass" type="password" name="repeatPass" class="loginInput">
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