
# RestAssured API Learning Path

![RestAssured](https://img.shields.io/badge/RestAssured-5.x-brightgreen?style=for-the-badge&logo=java)
![Cucumber](https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.x-orange?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-17%2B-blue?style=for-the-badge&logo=openjdk)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-critical?style=for-the-badge&logo=jenkins)
![Hamcrest](https://img.shields.io/badge/Hamcrest-assertions-purple?style=for-the-badge)
![Jackson](https://img.shields.io/badge/Jackson-JSON--binding-ff9933?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![DataFaker](https://img.shields.io/badge/DataFaker-dynamic--data-teal?style=for-the-badge)

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Installation](#installation)
4. [Configuration](#configuration)
5. [Usage](#usage)
6. [Folder Structure](#folder-structure)
7. [Dependencies](#dependencies)
8. [Reporting and Logs](#reporting-and-logs)
9. [Contributing](#contributing)
10. [License](#license)

## Project Overview

The **restassured-api-learning-path** is a Java-based API testing framework designed to automate REST API validations using **RestAssured**, **TestNG**, and **Hamcrest**. It supports comprehensive CRUD operations across multiple public APIs and includes structured logging, assertions, and POJO-based deserialization with **Jackson**. The framework leverages **DataFaker** to generate dynamic and localized test data, enhancing variability in test scenarios.

### Tested APIs

This framework includes test scenarios against the following public/mock APIs:

- **Zippopotam.us API**  
  Provides postal code and location data for validating JSON structure and values.  
  🔗 http://api.zippopotam.us

- **GoRest API**  
  User and comment management with full CRUD support.  
  🔗 https://gorest.co.in/public/v2

- **Campus API (Mersys)**  
  Simulates country/location management with role-based authentication.  
  🔗 https://test.mersys.io

- **Restful-Booker API**  
  Designed to simulate hotel booking flows with authentication and full lifecycle actions (`POST`, `PUT`, `PATCH`, `DELETE`).  
  🔗 https://restful-booker.herokuapp.com

- **Reqres API**  
  Allows testing of pagination, user creation, and BDD validations.  
  🔗 https://reqres.in/api

> For detailed test implementations, see [Key Test Scenarios](#key-test-scenarios).

## Features

- **API Testing with RestAssured**  
  Supports HTTP methods including `GET`, `POST`, `PUT`, `PATCH`, and `DELETE` for comprehensive API coverage.

- **POJO Modeling**  
  Enables object-oriented validation by deserializing JSON responses into clean, reusable Java POJO classes.

- **Behavior-Driven Development (BDD)**  
  Uses TestNG annotations and fluent RestAssured syntax to define test flows clearly and readably.

- **Data Randomization with DataFaker**  
  Integrates `net.datafaker` to generate realistic and localized test data dynamically.

- **Reusable Specifications**  
  Utilizes `RequestSpecification` and `ResponseSpecification` to centralize request/response configurations.

- **Token-Based Authentication**  
  Handles authentication dynamically using tokens (e.g., for secured booking APIs).

- **API Key Authentication**  
  Supports header-based API key testing (e.g., for role-restricted endpoints like Campus API).

- **Comprehensive Logging**  
  Automatically logs URIs, request/response bodies, headers, and status codes to simplify debugging.

- **Dependency Management with Maven**  
  Ensures reliable builds and version control through Maven with clearly defined dependencies.

- **Multi-API Integration**  
  Implements test flows across five different public APIs to validate CRUD operations, auth flows, and data handling.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/cihat-kose/restassured-api-learning-path.git
   ```
2. **Import the Project in IntelliJ IDEA**:
   - Open IntelliJ IDEA.
   - Select **File > Open** and choose the project folder.
   - Ensure Maven auto-import is enabled to download dependencies.

3. **Install Plugins** (if required):
   - **TestNG** and **RestAssured** support plugins in IntelliJ IDEA for enhanced IDE integration.

## Configuration

- **TestNG.xml**: Defines the suite structure and test class execution flow.
- **pom.xml**: Manages dependencies and configurations for the framework. 

## Usage

### Running Tests

To run tests, use Maven or execute directly from IntelliJ IDEA.

#### Maven
```bash
mvn clean test
```

#### IntelliJ IDEA
- Right-click on `TestNG.xml` and select **Run** to execute all tests.
- Alternatively, right-click any test class or method to run specific tests.

### Key Test Scenarios

1. **_01_APITestIntro.java**  
   Intro to basic `GET` requests, response logging, JSON field validation, and assertions with Hamcrest.

2. **_02_APITestSpec.java**  
   Demonstrates usage of reusable specifications with `RequestSpecBuilder` and `ResponseSpecBuilder`.

3. **_03_APITestExtract.java**  
   Focuses on extracting response data using `.path()` and validating with TestNG assertions.

4. **_04_APITestPOJO.java**  
   Shows how to deserialize JSON responses into custom POJO classes like `Location` and `Place`.

5. **_05_Tasks.java**  
   Collection of exercise-style test cases using `jsonplaceholder.typicode.com` with validation and POJO handling.

6. **_06_PathAndJSONPath.java**  
   Explains and contrasts usage of `.path()` vs `.jsonPath()` in extracting nested JSON data and converting to Java objects.

7. **_07_GoRestUsersTest.java**  
   Handles user creation, retrieval by ID, updating, and deletion using GoRest API.

8. **_08_GoRestCommentTest.java**  
   Manages comments: creation, retrieval, update, and deletion.

9. **_09_CountryTest.java**  
   Covers country creation, duplicate checks, updates, and deletions in the Campus API.

10. **_10_RestfulBookerAPITest.java**  
    Tests hotel booking flows: token generation, booking creation, update (PUT & PATCH), retrieval, and deletion.

11. **_11_ReqresUserTest.java**  
    Includes status code checks, BDD-style tests, and user creation with POST.

12. **_12_APIKeyTest.java**  
    Demonstrates sending authenticated requests using API keys.

## HTTP Status Codes

This project commonly encounters a variety of HTTP status codes in responses. Below is a summary of some frequently used status codes and their meanings:

![HTTP Status Codes](./images/HTTP_Status_Codes.png)

## Folder Structure

```plaintext
cihat-kose/restassured-api-learning-path/
│
├── src/
│   └── test/
│       ├── java/
│       │   ├── model/                        # POJO models for JSON response data
│       │   │   ├── Country.java
│       │   │   ├── Location.java
│       │   │   ├── Place.java
│       │   │   ├── ToDo.java
│       │   │   └── User.java
│       │   ├── _01_APITestIntro.java        # Intro to basic API tests and assertions
│       │   ├── _02_APITestSpec.java         # Reusable request/response specifications
│       │   ├── _03_APITestExtract.java      # Extracting and asserting response data
│       │   ├── _04_APITestPOJO.java         # Deserialization into POJOs
│       │   ├── _05_Tasks.java               # Task-style practical API test cases
│       │   ├── _06_PathAndJSONPath.java     # Path vs JSONPath comparison
│       │   ├── _07_GoRestUsersTest.java     # GoRest: user CRUD
│       │   ├── _08_GoRestCommentTest.java   # GoRest: comment CRUD
│       │   ├── _09_CountryTest.java         # Campus API: country CRUD
│       │   ├── _10_RestfulBookerAPITest.java# Restful-Booker: full booking flow
│       │   ├── _11_ReqresUserTest.java      # ReqRes: status & BDD testing
│       │   └── _12_APIKeyTest.java          # API Key authentication testing
│       │
│       └── resources/
│           └── TestNG.xml                   # TestNG suite configuration
│
├── target/                                  # Build output (generated)
├── images/                                  # Project images
│   └── HTTP_Status_Codes.png                # HTTP status codes reference image
├── pom.xml                                  # Maven build configuration
└── README.md                                # Project overview and structure
```

## Dependencies

> ⚠️ This project uses Java 17 features (e.g., text blocks, modern APIs). Ensure your JDK version is 17 or above.

Dependencies are managed in `pom.xml`. Key dependencies include:

- **RestAssured**: For making HTTP requests to test APIs.
- **TestNG**: For organizing and managing test execution.
- **Hamcrest**: For enhanced assertions in test validations.
- **Jackson**: For JSON serialization and deserialization.
- **DataFaker**: For generating realistic and localized fake test data.
- **SLF4J (Simple Logging Facade for Java)**: For logging support.

## Reporting and Logs

This framework logs API requests and responses, which can be useful for debugging. Logs include:

- **Request and Response Bodies**: Automatically logged for all test methods.
- **Status Codes and Headers**: Available in logs to confirm correct responses.

Test results and logs are saved in the `target` directory after test execution.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License - see the `LICENSE` file for details.
