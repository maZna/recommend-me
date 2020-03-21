# RecommendMe!
RecommendMe! is a web application that uses REST APIs to create and share Surveys.

Users can use this application to create Surveys regarding various topics. A typical survey will include a relevant topic and a question related to said topic. Once created, other users can fill the survey by assigning an appropriate score to answer the question asked, while also providing feedback.

Tools and Technologies used:
1) SpringBoot
2) JPA and Hibernate
3) Maven
4) H2 In-memory database
5) ReactJS
6) Swagger Documentation for endpoints
7) Docker

Instructions on running the project:
In order to run the project, you need to have docker and docker-compose installed on your machine.

1) Clone the project to a local folder
2) Open the command line terminal in the root folder of the project and run the command: "sudo docker-compose up".
3) The front-end can be accessed at http://localhost:3000/
4) The H2 In-memory database can be accessed at http://localhost:8080/h2/ and authenticated using the username "sa" and an empty password by default.
5) After using the application, run the command "sudo docker-compose down"
6) Happy Surveying!
