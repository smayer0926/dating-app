SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 age INTEGER,
 gender VARCHAR,
 matchminage INTEGER,
 matchmaxage INTEGER
);

CREATE TABLE IF NOT EXISTS questions (
 id int PRIMARY KEY auto_increment,
 prompt VARCHAR
);

CREATE TABLE IF NOT EXISTS journalentries (
 id int PRIMARY KEY auto_increment,
 userid INTEGER,
 postname VARCHAR,
 postcontent VARCHAR,
);

CREATE TABLE IF NOT EXISTS userquestions (
 id int PRIMARY KEY auto_increment,
 userid INTEGER,
 questionid INTEGER
);