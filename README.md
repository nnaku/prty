# PRTY
Prty was an school project, and development is discontinue.  The project runs on JVM with Spring-boot and frontend is powered by Vue.js framework.

------------
### Requirements
* Maven
* Java
* Node.js
* SQL Database

### Prerequisites
Install at least what is listed on *requirements*

##### Create database and user
```
CREATE DATABASE prty;
CREATE USER 'prty'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON prty . * TO 'prty'@'localhost';
FLUSH PRIVILEGES;
```

##### Set JAVA_HOME
Environment variable [instructions](http://google.com "instructions") here

### Run it
```
cd backend
mvn spring-boot:run
```
app runs is [localhost:8080](http://localhost:8080/ "localhost:8080")

###### Login with default user credentials
-u admin@prty.fi

-p AdminSecr3t!

##### Build it
Not going happen atm :cry:

## How to play
Login, select game and start an lobby.

Other players can join by giving lobby token "qwerty" and name, from web root.

Start a game.

Its a bit tricky, because its unfinished. :cry:

Do not hesitate to reboot jvm again

------------
# Authors
**Aku Kangas** - [Github](https://github.com/nnaku "Github")

**Matti Holopainen** - [Github](https://github.com/MattiJH "Github")

**Tiina Ojala** - [Github](https://github.com/Tiunu "Github")

**Maarit Saariniemi** - [Github](http://https://github.com/maaritti "Github")


## Acknowledgments

**We were in a hurry and it is also visible**
