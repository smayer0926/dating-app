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
![Create new user](Images/New-user.jpg)
![Create new question](Images/New-question.JPG)
![Create new date review](images/new-date-review.JPG)
![Link user and question](images/link-user-and-question.JPG)
![Get all users](images/get-all-users.JPG)
![Get all date reviews](images/get-all-date-reviews.JPG)
![Get specific user](images/get-specific-user.JPG?)
![Get all users who answered specific question](images/get-all-users-who-answered-question.JPG)
![Get all questions answered by specific user](images/get-all-questions-answered-by-user.JPG)

# Known Bugs

Date review "content" property currently not showing up in JSON get requests.

## Technologies Used

Java, IntelliJ, Postman, H2 mode-postgreSql

### License

Copyright &copy; 2017 Trevor Gill
