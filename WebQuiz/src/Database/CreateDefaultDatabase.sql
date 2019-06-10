use webquizdatabase;

DROP TABLE IF EXISTS quizes, accounts, questions, quizQuestionLinks, questionTypes;
 -- remove tables if they already exist and start from scratch

DROP TABLE IF EXISTS accounts, questions, quizQuestionLinks, questionTypes;

 -- we used SHA-512 which generates char(128)
CREATE TABLE accounts (
	account_id int(8) not null auto_increment,
    account_first_name varchar(64) not null,
    account_last_name varchar(64) not null,
    account_username varchar(64) not null,
    account_mail varchar(64),
    account_password char(128) not null,
    account_created datetime default now(),
    primary key (account_id)
);


CREATE TABLE quizes (
	quiz_id int(8) not null auto_increment,
    quiz_name varchar(64) not null,
    quiz_created datetime default now(),
    quiz_edited datetime,
    quiz_publisher int(8) not null,
    primary key (quiz_id),
    foreign key (quiz_publisher) references accounts(account_id)
);


CREATE TABLE questions(
	question_id int(8) not null auto_increment,
    questionType_name char(16) not null,
    questionInType_id int(8) not null,
    primary key (question_id)
);

CREATE TABLE quizQuestionLinks (
	id int(8) not null auto_increment,
	quiz_id int(8) not null,
    question_id int(8) not null,
    primary key (id)
);

INSERT INTO accounts (account_first_name, account_last_name, account_username, account_mail, account_password) VALUES
	("rezi", "lobzhanidze", "rlobz17", "rlobz17@freeuni.edu.ge", "rezi1234"),
    ("shota", "nozadze", "snoza17", "snoza17@freeuni.edu.ge", "shota1234"),
    ("davit", "popkhadze", "dpopk17","dpopk17@freeuni.edu.ge", "dudu1234"),
    ("davit", "kveladze", "dkvel17", "dkvel17@freeuni.edu.ge","onedq1234")
    ;
    

INSERT INTO quizes (quiz_name, quiz_publisher) VALUES
	("simpleQuiz1", 1),
    ("simpleQuiz2", 2),
    ("simpleQuiz3", 3),
    ("simpleQuiz4", 4),
    ("simpleQuiz5", 1),
    ("simpleQuiz6", 2)  
    ;
