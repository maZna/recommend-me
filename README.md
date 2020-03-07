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

Instructions on running the project:
1) Clone the project to a local folder
2) To launch the back-end application (using eg. Eclipse), go to File -> Import -> Maven -> Existing Maven Project and select the recommend-me-backend folder as the project folder. Now run the RecommendMeBackendApplication.java file.
3) To launch the front-end application, open the command line terminal in the recommend-me-frontend directory and run the command "npm start"
4) The front-end can be accessed at http://localhost:3000/
5) The H2 In-memory database can be accessed at http://localhost:8080/h2-console/ and authenticated using the username "sa" and an empty password by default.
6) Happy Surveying!
