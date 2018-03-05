DROP DATABASE Quiz;
CREATE DATABASE Quiz CHARACTER SET utf8 COLLATE utf8_bin;
USE Quiz;

CREATE TABLE Questions (
    ID int AUTO_INCREMENT UNIQUE NOT NULL,
	Author varchar(255) NOT NULL,
    Private BOOLEAN default 1 NOT NULL,
    Question TEXT NOT NULL,
    Correct varchar(255) UNIQUE NOT NULL,
    False1 varchar(255) UNIQUE NOT NULL,
    False2 varchar(255) UNIQUE NOT NULL,
    False3 varchar(255) UNIQUE NOT NULL,
    False4 varchar(255),
    False5 varchar(255),
    False6 varchar(255),
    False7 varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE Games (
    ID INT AUTO_INCREMENT UNIQUE NOT NULL,
    Author VARCHAR(255) NOT NULL,
    GameName VARCHAR(255) NOT NULL,
    Private BOOLEAN default 1 NOT NULL,
    Questions LONGTEXT NOT NULL,
    PRIMARY KEY (ID)
);