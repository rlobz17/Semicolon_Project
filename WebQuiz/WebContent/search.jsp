<%@page import="java.util.Date"%>
<%@page import="Temp.QuizLite"%>
<%@page import="java.util.ArrayList"%>
<%@page import="search.navigation"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="Temp.QuizLiteManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    	ServletContext cont = getServletContext();
    Object obj = cont.getAttribute("QuizLite");

    QuizLiteManager m = (QuizLiteManager)obj;

    Database.DateBaseManager d = (Database.DateBaseManager)cont.getAttribute("baseManager");
    Statement stm = null;

    try {
    	stm = d.getConnection().createStatement();
    } catch (SQLException e) {
    	// Auto-generated catch block
    	e.printStackTrace();
    }

    
    String p = request.getParameter("page");
    
    int currentPage = 1;
    if(p!=null){
    	currentPage = Integer.parseInt(p);
    }

    int count = 10;
    int beginIndex = (currentPage-1)*count;
    
    String search = (String)request.getAttribute("searchInput");
    
    if(search==null){
    	search = request.getParameter("search");
    }
    
	int quizNumber = m.searchQuizLites(search, null, beginIndex, count, stm).getValue();
    
    navigation n = new navigation(quizNumber, currentPage);

    int pages = n.getPageNumber();

    if(currentPage<1 || currentPage > pages){
    	currentPage = 1;
    }

    ArrayList<Integer> pagesArr = n.pagesToShow();

    ArrayList<QuizLite> quizes = m.searchQuizLites(search, null, beginIndex, count, stm).getKey();
    %>    
    
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mainPage.css"/>

<title>WebQuiz</title>

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
				
				<div class="loginSucc"> • ძიების შედეგები:</div>
			
				<%
					int size = quizes.size();
					for(int i=0; i<size; i++){
						QuizLite quiz = quizes.get(i);
						
						String title = quiz.getTitle();
						String img = quiz.getImgurl();
						String author = quiz.getPublisher();
						int quizDone = quiz.getQuizDone();
						Date dat = quiz.getDate();
						
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
						
						%>
							
							<div class="shortstoryMain">
								<div class="shortstoryImg">
									<img src="<%= img %>">
									<h1 class="shortstoryTxt">
									<a class="shortstoryTitle" style="text-decoration: none;" href="/"><%= title %></a>
									</h1>
								</div>

        						<div class="shortstoryLower">
									<ul class="meta grey">
									<li class="meta_author">
                					<img src="http://www.picz.ge/img/s2/1811/5/6/67d913510a23.png" class="icon icon-author">
										<a href="http://localhost:8080/WebQuizProject/Profile.jsp?username=<%= author%>">	<%= author %> </a>
									</li>
            						<span class="grey" style="margin-left: 2%; margin-right: 2%;"><%= date %></span>
									<li class="meta_coms"> <img src="http://www.picz.ge/img/s1/1811/5/1/19e64068e570.png" class="icon icon-coms"> <%= quizDone %> </li>
									</ul>
       							</div>
       							
							</div>
						
						<%
					}
				
				%>
				
				<div class="navigation">
					<%
						for(int i=1; i<=pages; i++){
							if(i==currentPage){
								%>
							
								<span><%= i %></span>
							
								<%
							} else{
								%>
							
								<a href="/WebQuizProject/search.jsp?page=<%= i %>&search=<%= search %>" ><%= i %></a>
							
								<%
							}
						}
					%>
				</div>
			
			</div>
			
			<div id="sidebarContent"></div>
		
		</div>
		
		<div id="footerContent"></div>
		
	</div>
	
</body>

</html>