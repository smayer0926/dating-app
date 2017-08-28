# Startup Weekend Event Tracker

#### Dating Website API project, August 2017

#### By Trevor Gill

## Description

RESTful routing to allow for accessing of dating website API. Users can access information about users, questions asked to to users, and date reviews through the API.

## Setup/Installation Requirements

* Clone the repo
* Run App.java
* Run Postman
* Enter REST routes as defined in App.java

## Specifications

| Behavior      |
| ------------- |
| The user shall be able to create a new dating app user |
| The user shall be able to view details of a specific dating app user |
| The user shall be able to view details of all dating app users |
| The user shall be able to create a new dating app question |
| The user shall be able to view details of a specific dating app question |
| The user shall be able to view the details of all dating app questions |
| The user shall be able to link questions and users via a many-to-many relationship |
| The user shall be able to create a new dating app date review |
| The user shall be able to view details of all dating app date reviews |

## Screenshots
![Create new user](images/new-user.JPG?raw=true "Create new user")
![Create new question](images/new-question.JPG?raw=true "Create new question")
![Create new date review](images/new-date-review.JPG?raw=true "Create new date review")
![Link user and question](images/link-user-and-question.JPG,?raw=true "Link user and question")
![Get all users](images/get-all-users.JPG?raw=true "Get all users")
![Get all date reviews](images/get-all-date-reviews.JPG?raw=true "Get all date reviews")
![Get specific user](images/get-specific-user.JPG?raw=true "Get specific user")
![Get all users who answered specific question](images/get-all-users-who-answered-question.JPG?raw=true "Get all users who answered specific question")
![Get all questions answered by specific user](images/get-all-questions-answered-by-user.JPG?raw=true "Get all questions answered by specific user")

# Known Bugs

Date review "content" property currently not showing up in JSON get requests.

## Technologies Used

Java, IntelliJ, Postman, H2 mode-postgreSql

### License

Copyright &copy; 2017 Trevor Gill
