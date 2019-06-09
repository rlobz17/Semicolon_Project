use webquizdatabase;

DROP TABLE IF EXISTS quizes;
 -- remove table if it already exists and start from scratch

CREATE TABLE quizes (
    name varchar(64),
    date date
);


INSERT INTO quizes VALUES
	("simpleQuiz1", curdate()),
    ("simpleQuiz2", curdate())
    ;
