# Dating Website API project

#### Exploring APIs through RESTFUL routing, August 2017

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
_Create new user_
![Create new user](Images/New-user.jpg)
_Create new question_
![Create new question](Images/New-question.JPG)
_Create new date review_
![Create new date review](images/new-date-review.JPG)
_Link user and question_
![Link user and question](images/link-user-and-question.JPG)
_Get all users_
![Get all users](images/get-all-users.JPG)
_Get all date reviews_
![Get all date reviews](images/get-all-date-reviews.JPG)
_Get specific user_
![Get specific user](images/get-specific-user.JPG?)
_Get all users who answered specific question_
![Get all users who answered specific question](images/get-all-users-who-answered-question.JPG)
_Get all questions answered by specific user_
![Get all questions answered by specific user](images/get-all-questions-answered-by-user.JPG)

# Known Bugs

Date review "content" property currently not showing up in JSON get requests.

## Technologies Used

Java, IntelliJ, Postman, H2 mode-postgreSql

### License

Copyright &copy; 2017 Trevor Gill
