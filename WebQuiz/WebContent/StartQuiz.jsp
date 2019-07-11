<%@page import="java.util.HashMap"%>
<%@page import="Temp.AnswerManager"%>
<%@page import="Temp.Answer"%>
<%@page import="Temp.QuestionTypes"%>
<%@page import="Temp.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="Temp.AccountManager"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="Temp.QuestionManager"%>
<%@page import="Temp.QuizManager"%>
<%@page import="Temp.Quiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String q = request.getParameter("id");

	int quizID = -1;
		
	for(int i=0; i<q.length(); i++){
		if(q.charAt(i)<'0' || q.charAt(i) > '9'){
			break;
		}
		
		if(i==q.length()-1){
			quizID = Integer.parseInt(q);
		}
	}
	
	ServletContext cont = getServletContext();
    Object obj = cont.getAttribute("Quiz");
	
    QuizManager quizManager = (QuizManager)obj;
	
	Database.DateBaseManager d = (Database.DateBaseManager)cont.getAttribute("baseManager");
   	
	Connection con = d.getConnection();
	
	Quiz quiz = quizManager.getQuiz(quizID, con);
	
	String user = (String)request.getSession().getAttribute("username");

	boolean logged = false;
	if(user!=null) logged = true;
	
	Object obj2 = cont.getAttribute("Question");
	QuestionManager questionManager = (QuestionManager)obj2;
	
%>
    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mainPage.css"/>

<%

	if(quiz!=null){
		%>
		
			<title><%= quiz.getQuizName() %></title>
		
		<%
	} else{
		%>
		
			<title>ქვიზი ვერ მოიძებნა</title>
		
		<%
	}

%>

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
					if(quiz==null){
						
						%>
						
							<div class="loginErr"> • ასეთი ქვიზი ვერ მოიძებნა</div>
							
						
						<%
						
					} else{
						String title = quiz.getQuizName();
						String imgURL = quiz.getImgUrl();
						int authorID = quiz.getCreatorID();
						
						Date dat = quiz.getCreationData();
						int quizDone = quiz.getQuizTaken();
						
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
						
						Object objAcc = cont.getAttribute("manager");
						
					    AccountManager accM = (AccountManager)objAcc;
					    
					    String author = accM.getAccountUsername(authorID, con);
					    
					    
					    %>
					    
					    	<div class="fullQuiz">
					
								<div class="fullTitle"> 
									<%= title%>
								</div>
								
								<div class="fullstoryImg">
									<img src="<%= imgURL %>">
								</div>
								
								
								<%
									if(!logged){
										%>
										
											<div class="loginErr"> • თქვენ არ ხართ შესული ექაუნთში</div>
							
											<div class="loginSucc"> <a href="/WebQuizProject/login.jsp" style="color: #2a6113;"> 
												• გთხოვთ გაიაროთ ავტორიაზაცია
											</a></div>
											
										<%									
										
									} else{
										
										ArrayList<Question> questions = quiz.getQuestions();
										
										String questionStr = request.getParameter("question");
										
										if(questionStr.equals("result")){
											
											HashMap<Integer, ArrayList<String>> mp = (HashMap)request.getAttribute("answers");
												
											%>
											
											<div class="question">
												<div class="currentQuestion">
													დასრულდა
													
												</div>
											</div>
											
											<%
										} else{
										
											int questionNum = Integer.parseInt(questionStr);
											
											Question currentQuestion = questions.get(questionNum);
											
											String curr = " " + (questionNum+1) + "/" + questions.size();
											
											int questionType = currentQuestion.getQuestionType();
											
											String type = questionManager.getQuestionType(questionType, con);
										%>
											<div class="question">
												<div class="currentQuestion">
													კითხვა: <%=  curr%>
												</div>
												
												<%
													if(currentQuestion.getQuestionImgUrl()!=null){
														%>
															<img src="<%= currentQuestion.getQuestionImgUrl() %>" class="questionImg">
														<%
													}
												%>		
												
												<div class="questionTask">
													<%= currentQuestion.getQuestionDetail() %>
												</div>
													
													<form action="/WebQuizProject/Quiz/QuizServlet" method="POST">
														
												
												<%	
													QuestionTypes types = new QuestionTypes();
													
													if(type.equals(types.getMultiAnswerType())){
														// ღია კითხვა
														ArrayList<Answer> correctAns = currentQuestion.getCorrectAnswers();
														int answerLastIndex = correctAns.get(correctAns.size()-1).getAnswerIndex();
														
														for(int i=0; i<answerLastIndex; i++){ %>
															<div class="answerArea">
																<input type="text" name="AnswerField<%= i %>" class="answerField"> 
															</div> 
														<% } %>
														
															<input type="hidden" name="FieldAnswersNum" value="<%= answerLastIndex %>" />
														
														<%
													} else if(type.equals(types.getMultipleChoiceType())){
														// რამდენიმე სავარაუდოდან 1ის არჩევა
														
																ArrayList<Answer> answers = currentQuestion.getPossibleAnswers();
																%> <input type="hidden" name="MultiAnswersNum" value="<%= answers.size() %>" /> <%
															
																for(int i=0; i<answers.size(); i++){
																	Answer ans = answers.get(i);
																	String str = ans.getAnswerDetail();
																	%>	
																		<input type="hidden" name="currAns<%= i %>" value="<%= str %>" />
																		<input type="radio" name="AnswerPossible" value="<%= str %>"> <%= str %> <br>
																	<%
																}
															
														
													} else if(type.equals(types.getMultipleChoiceWithMultipleAnswersType())){
														// რამდენიმე სავარაუდოდან რამდენიმეს არჩევა
														
																ArrayList<Answer> answers = currentQuestion.getPossibleAnswers();
																%> <input type="hidden" name="MultiAnswersMultiNum" value="<%= answers.size() %>" /> <%
																
																for(int i=0; i<answers.size(); i++){
																	Answer ans = answers.get(i);
																	String str = ans.getAnswerDetail();
																	%>	
																		<input type="hidden" name="currAns<%= i %>" value="<%= str %>" />
																		<input type="checkbox" name="AnswerPossible<%= i %>" > <%= str %> <br>
																	<%
																}
															
														
													} else if(type.equals(types.getMatchingType())){
														// შესაბამისობა
														ArrayList<Answer> possAns = currentQuestion.getPossibleAnswers();
														int s = possAns.size();
														
														for(int i=0; i<s; i++){
															Answer currAn = possAns.get(i);
														%>	<div class="answerArea">
																<%= currAn.getAnswerDetail() %>
																<input type="text" name="AnswerField<%= i %>" class="answerField">
															</div>
														<% } %>
														
															<input type="hidden" name="FieldAnswersNum" value="<%= s %>" />
														
														<%
													}
													
													if(questionNum == questions.size()-1){
														
														%>
																						
															<input value="დასრულება" class="finishQuiz" type="submit">
															<input type="hidden" name="quiz_id" value="<%= quiz.getQuizID() %>">
															<input type="hidden" name="question_id" value="result">
														
														<%
														
													} else{
														%>
																						
															<input value="შემდეგი >" class="nextQuestion" type="submit">
															<input type="hidden" name="quiz_id" value="<%= quiz.getQuizID() %>">
															<input type="hidden" name="question_id" value="<%= questionNum+1 %>">
																													
														<%
													}
												
												%> </div> <%
											}
											%> </form> <%

								}
								%>
								
								
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
		
			</div>
				
			<div id="sidebarContent"></div>
		
		</div>
		
		<div id="footerContent"></div>
		
	</div>
	
</body>
</html>