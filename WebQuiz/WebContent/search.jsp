<%@page import="java.sql.Connection"%>
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
   	
    Connection con = d.getConnection();

    
    String p = request.getParameter("page");
        
    int currentPage = 1;
    if(p!=null){
    	
    	for(int i=0; i<p.length(); i++){
    		if(p.charAt(i)<'0' || p.charAt(i) > '9'){
    			break;
    		}
    		
    		if(i==p.length()-1){
    			currentPage = Integer.parseInt(p);
    		}
    	}
    	
    }
    
    if(currentPage<0){
    	currentPage = 1;
    }

    int count = 10;
    int beginIndex = (currentPage-1)*count;
    
    String search = (String)request.getAttribute("searchInput");
    
    if(search==null){
    	search = request.getParameter("search");
    }
    
	int quizNumber = m.searchQuizLites(search, null, null, beginIndex, count, con).getValue();
    
    navigation n = new navigation(quizNumber, currentPage);

    int pages = n.getPageNumber();

    if(currentPage<1 || currentPage > pages){
    	currentPage = 1;
    }

    ArrayList<Integer> pagesArr = n.pagesToShow();

    ArrayList<QuizLite> quizes = m.searchQuizLites(search, null, null, beginIndex, count, con).getKey();
    
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
						
						int quizID = quiz.getQuizID();
						
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
									<a class="shortstoryTitle" style="text-decoration: none;" href="/WebQuizProject/quiz.jsp?id=<%= quizID%>"><%= title %></a>
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
						int last=0;
						for(int i=0; i<pagesArr.size(); i++){
							int show = pagesArr.get(i);
							
							if(show-last>1){
								%>
								
								<span> ... </span>
							
								<%
								
							}	
							
							last = show;
							
							if(show==currentPage){
								%>
							
								<span><%= show %></span>
							
								<%
							} else{
								%>
							
								<a href="/WebQuizProject/search.jsp?page=<%= show %>&search=<%= search %>" ><%= show %></a>
							
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