# Introduction
This is a sample project built using Springboot framework to provide a RESTful API to retrieve Account and Transaction related data from the backend. The application is developed based on Java 11 and gradle 7.1.1

## Assumptions
1. Log formatting is out of scope of this project
2. Pagination of the data is out of scope
3. Only GET methods are supported at the moment
4. Security aspect is not considered given the short time frame

## Further Improvements
1. Based on the application architecture, a decision should be taken to whether the application is open to outside or not and implement security as required.
2. Find a solution to the "An illegal reflective access operation has occurred" WARNING for java.util.Date when using the ModelMapper.
3. Use @Aspect to create a TransactionLogger class to weave boilerplate logging code around method calls. @EnableAspectJAutoProxy should be used in the AnzAccountApplication class

## How to build and Run

### Requirements
JAVA- 11
Gradle- 7.x.x

1. Use the database script in the src/main/scripts to create the db schema and insert test records
2. update the datasource details in src/main/resources/application.properties file
3. run command `gradle bootRun`
4. swagger documentation can be found at src/main/resources folder

## Usage Examples
### Accounts url 
http://localhost:8080/anz/v1/account-services/accounts

### Transactions url
http://localhost:8080/anz/v1/account-services/transactions/321143048