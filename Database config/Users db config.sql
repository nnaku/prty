DROP DATABASE Users;
CREATE DATABASE Users;
USE Users;

CREATE TABLE UserProfiles (
    ID int AUTO_INCREMENT UNIQUE NOT NULL,
	FirstName varchar(255) NOT NULL,
    LastName varchar(255) NOT NULL,
    NickName varchar(255) NOT NULL,
    Email varchar(255) UNIQUE NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE UserRoles (
    ID int,
    RoleName varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE UserAccounts (
    UserProfiles_ID INT UNIQUE NOT NULL,
    UserName VARCHAR(255) UNIQUE NOT NULL,
    PasswordHash VARCHAR(255) NOT NULL,
    UserRoles_ID INT DEFAULT 1 NOT NULL,
    PRIMARY KEY (UserProfiles_ID),
    FOREIGN KEY (UserProfiles_ID)
		REFERENCES UserProfiles (ID),
    FOREIGN KEY (UserRoles_ID)
		REFERENCES UserRoles (ID)
);

INSERT INTO UserRoles (ID,RoleName) VALUES
	(0,'Banned'),
	(1,'Member'),
	(9,'Admin'),
	(99,'The God')
;

INSERT INTO UserProfiles (FirstName,LastName,NickName,Email) VALUES 
	('Aku','Kangas','nAku','Aku.Kangas@metropolia.fi'),
	('Matti','Holopainen','Matti','Matti.Holopainen2@metropolia.fi'),
	('Tiina','Ojala','Tiina','Tiina.Ojala3@metropolia.fi'),
	('Maarit','Saariniemi','Maarit','Maarit.Saariniemi@metropolia.fi')
;

