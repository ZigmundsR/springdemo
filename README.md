# Spring Boot demo - Zigmunds
Spring Boot demo with thymeleaf

Spring Boot, Spring MVC, Spring Security, JPA, validation

Requirements:
JDK Development Kit 17 or higher, MySQL Database (MySQL Database Server and MySQL Workbench)

Before Running application you need to setup MySQL database(check sql-script folder):
1) in 1.create-use.sql file you can create database user, you can change it if you want.(where "IDENTIFIED BY" is the users password.) You can also use your own. 
By default just run this script first.
2) In application.properties file you can setup username and password for database. (change it if you changed anything in 1. section or have your own user)
3) Then run 2. and 3. and 4. scripts in order

To run the application(you can do one of these):
1) Need an IDE, for example IntelliJ, open the project and run SpringdemoApplication.java
2) (Windows) In CMD cd in directory of project, run "mvnw package", then run "java -jar target\springdemo-0.0.1-SNAPSHOT.jar" or "mvnw spring-boot:run"

Then you should be able to access it under http://localhost:8080/ and see homepage. For full functionalities login as admin password: "nimda"

Functionalities implemented:
- login/logout user with roles for access. Using BCrypt for passwords.
- Homepage where anyone can access and can login.
- Navigation bar has links to homepage, Students, Courses and Instructors and also to Employees and Users but with specific roles. But you can only access them if you login.
- There are 3 groups Employee, Manager and Admin
- Every role has access to Students, Courses and Instructors, Manager role has access to Employees and Admin has access to Users.
- Every Form for creating a new/updating object has a validation for needed attributes (not null, min size = 5 etc.)
- In Users directory, only Admins role can access, you can see a list of users with their username, password(encrypted), roles and also linked employee info(FirstName, LastName, email) 
and you can search by a search term(searches for everything except password and roles). You can create a user, delete and update it.
- Employee Directory, only Manager role can access, you can see a list of employees, update them (They were created in users directory via Admin) and search by a search term to find the employee.
- Instructor Directory, every role can access, you can see a list of instructors, create, update, delete an instructor.
- Student Directory, every role can access, you can see a list of students, you can create, update, delete a student. Also you can view their courses and add course to a student.
- Course Directory, every role can access, you can see a list of courses(with their instructor), you can create, update, delete a course. You can view students for the course. Also view reviews, where you can delete a review and submit a review.