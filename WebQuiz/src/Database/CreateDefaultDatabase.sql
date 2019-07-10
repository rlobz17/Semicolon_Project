use webquizdatabase;

-- history tables
DROP TABLE IF EXISTS accountQuizTakeLinks, takeHistory;
 -- remove tables if they already exist and start from scratch 
 
-- message tables
DROP TABLE IF EXISTS messages, messageTypes;
 -- remove tables if they already exist and start from scratch
 
-- main tables
DROP TABLE IF EXISTS quizes, accounts, questions, answers, quizQuestionLinks, questionTypes, quizCategories;
 -- remove tables if they already exist and start from scratch



 -- we used SHA-512 which generates char(128)
CREATE TABLE accounts (
	account_id int(8) not null auto_increment,
    account_first_name varchar(64),
    account_last_name varchar(64),
    account_username varchar(64) not null,
    account_mail varchar(64) not null,
    account_password char(128) not null,
    account_imgUrl text,
    account_created datetime default now(),
    account_isAdmin bool default false,
   
    primary key (account_id),
	fulltext(account_first_name, account_last_name, account_username, account_mail)
);

CREATE TABLE quizCategories(
	quizCategory_id int (8) not null auto_increment,
    quizCategory_name varchar(64) not null,
    primary key (quizCategory_id)
);

INSERT INTO quizCategories (quizCategory_name) values
	('culture'),
    ('religion'),
    ('politics'),
    ('sport'),
    ('history'),
    ('science'),
    ('art'),
    ('kids'),
    ('medicine'),
    ('others')
    ;
    

CREATE TABLE quizes (
	quiz_id int(8) not null auto_increment,
    quiz_name varchar(64) not null,
    quiz_created datetime default now(),
    quiz_edited datetime,
    quiz_publisherId int(8) not null,
    quiz_imgUrl text,
    quizCategory_id int (8) default 10, -- if null this quiz is added to others category
    primary key (quiz_id),
    foreign key (quiz_publisherId) references accounts(account_id),
	fulltext(quiz_name)
);

CREATE TABLE questionTypes (
	questionType_id int(8) not null auto_increment,
	questionType_name varchar(64) not null,
    questionType_defaultTask text not null,
    primary key (questionType_id)
);

CREATE TABLE questions(
	question_id int(8) not null auto_increment,
    questionType_id int(8) not null,
    question_detail text, /* if null - question doesn't need any extra infromation */
    question_task text, /* if null - default questionType Task */
    question_imgUrl text, /* if null - question doesn't need any extra information */
    quiestion_answerOrder boolean default false,
    primary key (question_id),
    foreign key (questionType_id) references questionTypes(questionType_id)
);

CREATE TABLE quizQuestionLinks (
	id int(8) not null auto_increment,
	quiz_id int(8) not null,
    question_id int(8) not null,
    primary key (id),
    foreign key (quiz_id) references quizes(quiz_id),
    foreign key (question_id) references questions(question_id)
);

CREATE TABLE answers(
	answer_id int(8) not null auto_increment,
    question_id int(8) not null,
    answer_index int(8) default -1, /* determines if answers have order or not */
    answer_detail text not null,
    answer_correct boolean default true,
    primary key (answer_id),
    foreign key (question_id) references questions(question_id)
);

CREATE TABLE messageTypes (
	messageType_id int(8) not null auto_increment,
    messageType_name varchar(64) not null,
	primary key (messageType_id)
);

CREATE TABLE messages (
	message_id int(8) not null auto_increment,
    messageType_id int(8) not null,
    from_account_id int(8) not null,
    to_account_id int(8) not null,
    sent_date date,
    text_message text,
    quiz_id int(8), 
    max_score double,
    
	primary key (message_id),
	foreign key (messageType_id) references messageTypes(messageType_id),
    foreign key (from_account_id) references accounts (account_id),
    foreign key (to_account_id) references accounts (account_id),
    foreign key (quiz_id) references quizes (quiz_id)
    
);

-- password is 'rezi1234', 'shota1234', 'dudu1234', 'kvela1234' hashed in sha-512
INSERT INTO accounts (account_first_name, account_last_name, account_username, account_mail, account_password, account_isAdmin) VALUES
	("rezi", "lobzhanidze", "rlobz17", "rlobz17@freeuni.edu.ge", '899ebae3fc157705404ea24cb090ce5f42e7af6a019dc304e3fc7943adf4360060cc67f51b86ba83ff76344fc7d77a4e34d30a213e5804212f58acd737f760a1', 1),
    ("shota", "nozadze", "snoza17", "snoza17@freeuni.edu.ge", 'eb734e38f4f768eba4cf7c8d02e4d04f8350616c680a506cedb68a6af1fcafef83be5ddb3ea3038a8bff12ae198549fe936e06f9b80c229a15e396c4765d5f57', 1),
    ("davit", "popkhadze", "dpopk17","dpopk17@freeuni.edu.ge", '227893fda4a3337dc6a242538fa543c0d58489dc43c2e7574d2f8f8fe04876016ad2ac5c010a577d63abf35af3ab15918a08405d18c8af5df002954c31704bd9', 1),
    ("davit", "kveladze", "dkvel17", "dkvel17@freeuni.edu.ge", '7899d010326dbc410102de1294962da127d057609b522ee802922d799244ef3e2877c4f39e00d95ce1035e77045d97122cc77b29c76cf1daac5d07c7022d7a6c', 0)
    ;
    

INSERT INTO quizes (quiz_name, quiz_publisherId, quiz_imgUrl) VALUES
	("simpleQuiz1", 1, "https://spectator.imgix.net/content/uploads/2017/10/iStock-501042977.jpg?auto=compress,enhance,format&crop=faces,entropy,edges&fit=crop&w=820&h=550"),
    ("simpleQuiz2", 2 ,"https://cdn.davidwolfe.com/wp-content/uploads/2016/11/perfectionist-quiz-FI.jpg")
;
    
    
INSERT INTO questionTypes (questionType_id, questionType_name, questionType_defaultTask) VALUES
    (1,"Multi_Answer_type", "Fill in with answers"),
	(2,"Multiple_Choice_type", "Select one correct answer"),
    (3,"Multiple_Choice_With_Multiple_Answers_type", "Select Correct answers" ),
    (4,"Mathcing_type" , "Match correct pairs")
    ;
    
INSERT INTO questions (questionType_id, question_detail, question_imgUrl) VALUES
	(1, "Who is the best student in the group?", "https://trademarks.justia.com/media/og_image.php?serial=87226326" ),
    (1, "Which University logo is painted in the picture?", "http://freeuni.edu.ge/sites/default/themes/freeuni/images/free-og.png")
    ;
    
INSERT INTO quizQuestionLinks (quiz_id, question_id) VALUES
    (1, 1),
    (1, 2)
    ;

INSERT INTO answers (question_id, answer_index, answer_detail) VALUES
    (1, -1, "rlobz17"),
    (1, -1, "rezi"),
    (1, -1, "rezgo"),
    (2, -1, "freeuni"),
    (2, -1, "free university of tbilisi")
    ;    

    
 -- History Part    
Create table takeHistory(
	takeHistory_id int(8) not null auto_increment,
    takeHistory_date datetime default now(),
    takeHistory_score double,
    primary key (takeHistory_id)
);

create table accountQuizTakeLinks(
	accountQuizTakeLink_id int(8) not null auto_increment,
    account_id int(8) not null,
    quiz_id int(8) not null,
    takeHistory_id int(8) not null,
    primary key (accountQuizTakeLink_id),
    foreign key (account_id) references accounts(account_id),
    foreign key (quiz_id) references quizes(quiz_id),
    foreign key (takeHistory_id) references takeHistory(takeHistory_id)
);
    

INSERT INTO takeHistory (takeHistory_score) VALUES
    (100),
    (90)
    ; 
    
 do sleep(1);
 
 INSERT INTO takeHistory (takeHistory_score) VALUES
    (70),
    (95),
    (100),
    (30)
    ; 
    
INSERT INTO accountQuizTakeLinks (account_id, quiz_id, takeHistory_id) VALUES
	(1, 1, 1),
    (2, 1, 2),
    (3, 1, 3),
    (4, 1, 4),
    (1, 2, 5),
    (2, 2, 6)
;
    
    


/*
 * Multi_Answer_type შემმოწმებელი ქუიზი.
 */

INSERT INTO quizes (quiz_name, quiz_publisherId, quiz_imgUrl) VALUES
	("ქვიზი Multi_Answer_type", 2, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKC2Kco6H7iMrwNyfy2FbC3bzbJhMw4bFEcn46SFOkP41pLpaq");
	
INSERT INTO questions (questionType_id, question_detail, question_task, question_imgUrl, quiestion_answerOrder) VALUES
	(1, "რამდენია 2+3?" , null, "https://i.ytimg.com/vi/XM1NNRNmZ6c/maxresdefault.jpg", false),
    (1, "3 გამრავლებული ___(1)___-ზე არის 21", null, "https://cdn-t-3.bvkstatic.com/us/static/images/5s/7-times-table.png", false),
    (1, "2 + 3 = ___(1)___ და  7 + 8 =  ___(2)___", "შეავსეთ გამოტოვებული ადგილები სწორად", null, true),
	(1, "დაწერეთ 8ის ჯერადები, რომლებიც მეტია 7ზე და ნაკლებია 17-ზე არიან:", null, null, false),
    (1, "დაწერეთ 1 ცალი 8ის გამყოფი:", null, null, false);

INSERT INTO quizQuestionLinks (quiz_id, question_id) VALUES   
	(3, 3),
	(3, 4),
	(3, 5),
	(3, 6),
	(3, 7);
    
INSERT INTO answers (question_id, answer_index, answer_detail) VALUES 
	(3, 1, "5"),
	(4, 1, "7"),
	(5, 1, "5"),
	(5, 2, "15"),
	(6, 1, "8"),
	(6, 2, "16"),
	(7, 1, "8"),
	(7, 1, "4"),
	(7, 1, "2"),
	(7, 1, "1");  
    
 /*
 * Multiple_Choice_type შემმოწმებელი ქუიზი.
 */   
    
 INSERT INTO quizes (quiz_name, quiz_publisherId, quiz_imgUrl) VALUES
	("ქვიზი Multiple_Choice_type", 1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKC2Kco6H7iMrwNyfy2FbC3bzbJhMw4bFEcn46SFOkP41pLpaq");
	
INSERT INTO questions (questionType_id, question_detail, question_task, question_imgUrl) VALUES
	(2, "რამდენია 2+3?" , null, "https://i.ytimg.com/vi/XM1NNRNmZ6c/maxresdefault.jpg"),
    (2, "3 გამრავლებული ___(1)___-ზე არის 21", null, "https://cdn-t-3.bvkstatic.com/us/static/images/5s/7-times-table.png"),
    (2, "8*8?", null, "https://cdn-t-3.bvkstatic.com/us/static/images/5s/7-times-table.png");

INSERT INTO quizQuestionLinks (quiz_id, question_id) VALUES   
	(4, 8),
	(4, 9),
    (4, 10);
    
INSERT INTO answers (question_id, answer_index, answer_detail, answer_correct) VALUES
	(8, 1, "5", true),
    (8, -1, "5", false), 
    (8, -1, "6", false),
	(9, 1, "7", true),
    (9, -1, "7", false),
    (9, -1, "8", false),
    (9, -1, "0", false),
    (10, 1, "64", true),
    (10, -1, "64", false),
    (10, -1, "8", false),
    (10, -1, "128", false),
    (10, -1, "32", false);
      
      
      
      
    
 /*
 * Multiple_Choice_With_Multiple_Answers_type შემმოწმებელი ქუიზი.
 */   
    
 INSERT INTO quizes (quiz_name, quiz_publisherId, quiz_imgUrl) VALUES
	("ქვიზი Multiple_Choice_With_Multiple_Answers_type", 1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKC2Kco6H7iMrwNyfy2FbC3bzbJhMw4bFEcn46SFOkP41pLpaq");
	
INSERT INTO questions (questionType_id, question_detail, question_task, question_imgUrl) VALUES
	(3, "ნატურალური რიცხვი n, რომელიც აკმაყოფილებს პირობას: 0 < n < 3" , null, "https://i.ytimg.com/vi/XM1NNRNmZ6c/maxresdefault.jpg"),
    (3, "რიცხვი ___(1)___-ის კვადრატი არის 81", null, "https://cdn-t-3.bvkstatic.com/us/static/images/5s/7-times-table.png");

INSERT INTO quizQuestionLinks (quiz_id, question_id) VALUES   
	(5, 11),
	(5, 12);
    
INSERT INTO answers (question_id, answer_index, answer_detail, answer_correct) VALUES
	(11, 1, "1", true),
    (11, 2, "2", true), 
    (11, -1, "1", false),
	(11, -1, "2", false),
    (11, -1, "3", false),
    (12, 1, "9", true),
    (12, 2, "-9", true),
    (12, -1, "9", false),
    (12, -1, "-9", false),
    (12, -1, "0", false),
    (12, -1, "7", false),
    (12, -1, "80", false),
    (12, -1, "3", false),
    (12, -1, "27", false);



 /*
 * Mathcing_type შემმოწმებელი ქუიზი.
 */   
    
 INSERT INTO quizes (quiz_name, quiz_publisherId, quiz_imgUrl) VALUES
	("ქვიზი Mathcing_type", 1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKC2Kco6H7iMrwNyfy2FbC3bzbJhMw4bFEcn46SFOkP41pLpaq");
	
INSERT INTO questions (questionType_id, question_detail, question_task, question_imgUrl) VALUES
	(4, "(1) x=8 | (2) x=3 | (3) x=0" , "შეუსაბამეთ უცნობის მნიშვნელობები მათ გამოსახულებებს", "https://i.ytimg.com/vi/XM1NNRNmZ6c/maxresdefault.jpg");

INSERT INTO quizQuestionLinks (quiz_id, question_id) VALUES   
	(6, 13);
    
INSERT INTO answers (question_id, answer_index, answer_detail, answer_correct) VALUES
	(13, 1, "2", true),
    (13, 2, "1", true), 
    (13, 3, "3", true),
    (13, 1, "x-1=2", false),
	(13, 2, "x-1=7", false),
    (13, 3, "x-1=-1", false);



