use webquizdatabase;

DROP TABLE IF EXISTS quizes, accounts, questions, answers, quizQuestionLinks, questionTypes;
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
    primary key (account_id)
);


CREATE TABLE quizes (
	quiz_id int(8) not null auto_increment,
    quiz_name varchar(64) not null,
    quiz_created datetime default now(),
    quiz_edited datetime,
    quiz_publisherId int(8) not null,
    quiz_imgUrl text,
    quiz_doneCount int default 0,
    primary key (quiz_id),
    foreign key (quiz_publisherId) references accounts(account_id)
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
    question_detail text, /*if null - question doesn't need any extra infromation*/
    question_task text, /*if null - default questionType Task*/
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
    answer_index int(8) default null, /* determines if answers have order or not */
    answer_detail text not null,
    primary key (answer_id),
    foreign key (question_id) references questions(question_id)
);

-- password is 'rezi1234', 'shota1234', 'dudu1234', 'kvela1234' hashed in sha-512
INSERT INTO accounts (account_first_name, account_last_name, account_username, account_mail, account_password) VALUES
	("rezi", "lobzhanidze", "rlobz17", "rlobz17@freeuni.edu.ge", '899ebae3fc157705404ea24cb090ce5f42e7af6a019dc304e3fc7943adf4360060cc67f51b86ba83ff76344fc7d77a4e34d30a213e5804212f58acd737f760a1'),
    ("shota", "nozadze", "snoza17", "snoza17@freeuni.edu.ge", 'eb734e38f4f768eba4cf7c8d02e4d04f8350616c680a506cedb68a6af1fcafef83be5ddb3ea3038a8bff12ae198549fe936e06f9b80c229a15e396c4765d5f57'),
    ("davit", "popkhadze", "dpopk17","dpopk17@freeuni.edu.ge", '227893fda4a3337dc6a242538fa543c0d58489dc43c2e7574d2f8f8fe04876016ad2ac5c010a577d63abf35af3ab15918a08405d18c8af5df002954c31704bd9'),
    ("davit", "kveladze", "dkvel17", "dkvel17@freeuni.edu.ge", '7899d010326dbc410102de1294962da127d057609b522ee802922d799244ef3e2877c4f39e00d95ce1035e77045d97122cc77b29c76cf1daac5d07c7022d7a6c')
    ;
    

INSERT INTO quizes (quiz_name, quiz_publisherId, quiz_imgUrl) VALUES
	("simpleQuiz1", 1, "https://extensionscdn.joomla.org/cache/fab_image/5a1d9263bfb27_resizeDown1200px525px16.png"),
    ("simpleQuiz2", 2 ,"https://cdn.davidwolfe.com/wp-content/uploads/2016/11/perfectionist-quiz-FI.jpg")
    ;
    

    
INSERT INTO questionTypes (questionType_id, questionType_name, questionType_defaultTask) VALUES
	(1,"Question_Response_type", "Answer the question"),
    (2,"Fill_In_The_Blank_type", "Fill in the blank space"),
    (3,"Multiple_Choice_type", "Select the correct answer"),
    (4,"Picture_Response_type", "What does the picture show"),
    (5,"Multi_Answer_type", "Fill in with answers"),
    (6,"Multiple_Choice_With_Multiple_Answers_type", "Select Correct answers" ),
    (7,"Mathcing_type" , "Match correct pairs"),
    (8,"Auto_Generated_type" , "Auto generated question"),
    (9,"Graded_Question_type" , "Answer the question (Will be graded later)"),
    (10,"Timed_Question_type", "Answer the question before the time runs out")
    ;
    
INSERT INTO questions (questionType_id, question_detail, question_task) VALUES
	(1, "Who is the best student in the group?" , null),
    (4, "http://freeuni.edu.ge/sites/default/themes/freeuni/images/free-og.png", "Which University logo is painted in the picture?")
    ;
    
INSERT INTO quizQuestionLinks (quiz_id, question_id) VALUES
    (1, 1),
    (2, 2)
    ;

INSERT INTO answers (question_id, answer_detail) VALUES
    (1, "rlobz17"),
    (1, "rezi"),
    (1, "rezgo"),
    (2, "freeuni"),
    (2, "free university of tbilisi")
    ;    

    
    
    
    
    
    
    
    
    
    
    
    
    
/*
 *this code is left for undo purpose (probably never used)
 
DROP TABLE IF EXISTS Question_Response_type, Fill_In_The_Blank_type, Multiple_Choice_type, Picture_Response_type, Multi_Answer_type, Multiple_Choice_With_Multiple_Answers_type,
						Mathcing_type, Auto_Generated_type, Graded_Question_type, Timed_Question_type, answers;
 -- remove tables if they already exist and start from scratch
    
*/





