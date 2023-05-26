# Simple social network with Spring Boot

A small project for studying purposes.

## Requirements

* Java [JDK 17+]
* Maven [3.0+]
* Docker Compose

## Setup

Run in `./message_analyzer_microservice` and `./web_service` to build artifacts:
```
 mvn package spring-boot:repackage -Dspring.profiles.active=test
```

Run in root:
```
 docker-compose up -d
```

The web interface of web-service will be available on [localhost:8080](http://localhost:8080/)
and message-analyzer microservice will run on [localhost:8090](http://localhost:8090/).

## Functionality

`/` represents the main page with global chat. If you try to send something by clicking on `Send` button, 
you will be redirected on login page.

There is also a model of smart message analyzing for users with nicknames ending up with `_pro` which
only checks if a message matches some regexps (is an email address and ends up with ".com") at the moment. 
Remember it is a studying project.

## TODO

* Integration tests
* Separate frontend from user and message logic
* Add message broke
* Move from embedded database
