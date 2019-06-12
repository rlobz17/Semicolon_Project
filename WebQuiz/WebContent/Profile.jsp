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

String name = acc.getFirstName();
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= username %></title>
</head>
<body>
	<h1><%= name %></h1>
</body>
</html>