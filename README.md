## Environment:
- Java version: 1.8
- Maven version: 3.*
- Spring Boot version: 2.2.1.RELEASE

## Run tests from:
- src/test/*


## Requirements:
`Collect`: A data Collection platform which is used to powering customer's critical activities including team management, different forms, and data collection, and even to implement data post-submission business logic.

Following architectural components have been implemented as part of this application:
* `ContactController`: controller class where you have to define REST endpoints for POST and GET methods
* `ContactService`: service class expected to be used by controller class to save form and retrieve responses from users
* `Repository classes`: repository class expected to be used by service class to handle database operations
* `Model Classes`: Model class to hold the Table information


## Task:
1. Design a sample schema for which is capable to store forms (with questions) and responses (with answers) in the Collect data store. Forms, Questions, Responses and Answers each will have relevant metadata. 
2. Optimize the solution. 

We need to complete the given project so that it passes all the test cases when running. The project supports the use of the JPA repository for database.


## Solution:
1. Create a `user table`(storing details of all users), `form table`(store form and owner details), `question table`(store all questions linked with form), `answer table`(store answers submitted by different users of a form), `response table`(store for a particular form, who all have submitted the response).
2. Optimizing the solution: Created multiple tables instead of storing in one for normalization (reducing redundancy and multiple DB search). Also, horizontal scalability, single point of failure, latency, availability, consistency etc. also kept in mind while designing. 
3. Corner Cases: On form creation make that user owner of that form, multiple values should not be stored in database, automatically store the response in response table and others. 


## Corner Cases:
1. Check if the details are valid while saving/ storing user, question, response, etc. in the data storage.
2. User table shouldn't contain duplicate record.
3. User should be a registered user for from creation and for submitting the response.
4. And after creating form user is set to be as owner.
5. Add questions and its responses in a valid form.
6. 

Here is the detailed document, explaining the solution: https://docs.google.com/document/d/1yGpGGiwikH9ooi2DohW9-nYtoG-2juItQNI427NV6Uw/edit?usp=sharing 
