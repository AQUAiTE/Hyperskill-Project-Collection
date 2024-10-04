# Hyperskill: Cinema Room REST Service 
This project uses Spring to expose HTTP Endpoints and respond to these requests with JSON objects. It simulates the existence of a Movie Theater, where customers can buy, return, and check the seats for their tickets. It additionally allows managers to check the number of seats purchased, available, and the income of the theater.

## Learned From Each Stage:

### Stage 1:
* Setup, build, and run a Java Spring Boot application hosted on a local port
* Create and use an `@RestController` class that exposes and responds to a GET endpoint request
* Use `@Autowired` to automatically create the Cinema Theater in the Controller

### Stage 2:
* Create an endpoint that responds to POST requests to allow a seat to be purchased
* Catch and respond with custom JSON responses to invalid requests

### Stage 3:
* Implement a pseudo-token system to track ticket purchases using Java's UUID class
* Use a service class to make the logic of buying/returning clearer
* Create another POST endpoint and give it a custom error response
* Refactored exception handling to use `@ExceptionHandler` to remove try-catch blocks from endpoint coded

### Stage 4:
* Adding an additional GET endpoint using an optinal `@RequestParam` and validating this request
* Creating a custom class for an Exception and using it with `@ExceptionHandler`

### Future Additions
* Will likely come back and comment the code for clarity
* Will do javadocs for the more lengthy functions
