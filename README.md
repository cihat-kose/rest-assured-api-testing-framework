
# RestAssured API Testing Framework

![Java 17+](https://img.shields.io/badge/Java%2017+-required-blue?style=for-the-badge&logo=openjdk)
![RestAssured](https://img.shields.io/badge/RestAssured-6DB33F?style=for-the-badge&logo=java&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF7300?style=for-the-badge&logo=testng&logoColor=white)
![Hamcrest](https://img.shields.io/badge/Hamcrest-655c83?style=for-the-badge)
![Jackson](https://img.shields.io/badge/Jackson-ff9933?style=for-the-badge)
![DataFaker](https://img.shields.io/badge/DataFaker-00b2a9?style=for-the-badge)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)

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

The **rest-assured-api-testing-framework** is a Java-based API testing framework designed to test REST APIs with RestAssured, TestNG, and Hamcrest. This framework supports CRUD operations on multiple API endpoints, with comprehensive logging and assertions for validation. It includes support for JSON serialization/deserialization via Jackson and data randomization with JavaFaker.

GitHub Repository: [rest-assured-api-testing-framework](https://github.com/cihat-kose/rest-assured-api-testing-framework.git)

### Tested APIs

This framework includes test scenarios against 5 public or mock APIs to cover various testing cases such as CRUD operations, authentication, JSON handling, and POJO deserialization.

APIs used:
- **GoRest API**
- **Campus API**
- **Zippopotam.us API**
- **ReqRes API**
- **Restful-Booker API**

> Detailed scenarios per API are listed under [Key Test Scenarios](#key-test-scenarios).

## Features

- **API Testing with RestAssured**: Supports `GET`, `POST`, `PUT`, and `DELETE` requests.
- **POJO Modeling**: Models API responses using POJO classes for deserialization.
- **Behavior-Driven Development**: Uses TestNG annotations for structured and readable test scenarios.
- **Data Randomization**: Utilizes JavaFaker for generating random data, enhancing test variety.
- **Custom Request Specifications**: Leverages `RequestSpecification` and `ResponseSpecification` for reusable configurations.
- **Comprehensive Logging**: Logs detailed information for requests and responses.
- **Dependency Injection**: Provides flexibility with dependencies, managed through Maven.
- **Hotel Booking API Testing**: Covers authentication, booking creation, retrieval, update, and deletion.
- **Token-Based Authentication**: Uses token authentication to test secure API endpoints.

### Scenarios Covered

This framework enables testing a wide range of API testing scenarios, including:

- **Authentication**
   - Token-based authentication (e.g., Restful-Booker)
   - API key validation (e.g., Campus API)

- **CRUD Operations**
   - Creating, reading, updating, and deleting resources (users, countries, bookings, comments)

- **Parameterized Testing**
   - Using path and query parameters to validate pagination and filtered requests

- **Data Extraction & Assertions**
   - Verifying response bodies using Hamcrest matchers
   - Extracting values with `.path()` and `.jsonPath()`
   - Asserting response structure and business rules with TestNG

- **Deserialization (POJO Mapping)**
   - Mapping JSON responses into Java objects for clean and reusable assertions

- **Reusable Specs & Logging**
   - Centralized request/response specifications with `RequestSpecBuilder`
   - Auto-logging of URIs, request bodies, and responses for easier debugging

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/cihat-kose/rest-assured-api-testing-framework.git
   ```
2. **Import the Project in IntelliJ IDEA**:
   - Open IntelliJ IDEA.
   - Select **File > Open** and choose the project folder.
   - Ensure Maven auto-import is enabled to download dependencies.

3. **Install Plugins** (if required):
   - **TestNG** and **RestAssured** support plugins in IntelliJ IDEA for enhanced IDE integration.

## Configuration

- **TestNG.xml**: Contains configurations for TestNG test execution.
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

1. **Country Test**
   - **Location**: `campus._09_CountryTest`
   - Covers country creation, duplicate checks, updates, and deletions in the Campus API.

2. **User Management in GoRest**
   - **Location**: `goRest._07_GoRestUsersTest`
   - Handles user creation, retrieval by ID, updating, and deletion.

3. **Comments API Test**
   - **Location**: `goRest._08_GoRestCommentTest`
   - Manages comments: creation, retrieval, update, and deletion.

4. **API Key Test**
   - **Location**: `apiKeyTest.APIKeyTest`
   - Demonstrates sending authenticated requests using API keys.

5. **Restful-Booker API Test**
   - **Location**: `restBooker.RestfulBookerAPITest`
   - Tests hotel booking flows including token generation, booking creation, update (PUT & PATCH), retrieval, and deletion.

6. **ReqRes API Test**
   - **Location**: `reqres.ReqResUserTest`
   - Includes status code checks, BDD-style tests, and user creation with POST.

7. **Standalone API Learning Scenarios**
   These test classes are located directly under `test/java` and focus on learning and practicing core API testing concepts:

   - **`_01_APITestIntro`**  
     Intro to basic `GET` requests, response logging, JSON field validation, and assertions with Hamcrest.

   - **`_02_APITestSpec`**  
     Demonstrates usage of reusable specifications with `RequestSpecBuilder` and `ResponseSpecBuilder`.

   - **`_03_APITestExtract`**  
     Focuses on extracting response data using `.path()` and validating it with TestNG assertions.

   - **`_04_APITestPOJO`**  
     Shows how to deserialize JSON responses into custom POJO classes like `Location` and `Place`.

   - **`_05_Tasks`**  
     Collection of exercise-style tasks using `jsonplaceholder.typicode.com` with validation and POJO handling.

   - **`_06_PathAndJSONPath`**  
     Explains and contrasts usage of `.path()` vs `.jsonPath()` in extracting nested JSON data and converting to Java objects.

## HTTP Status Codes

This project commonly encounters a variety of HTTP status codes in responses. Below is a summary of some frequently used status codes and their meanings:

![HTTP Status Codes](./images/HTTP_Status_Codes.png)

## Folder Structure

```plaintext
cihat-kose/rest-assured-api-testing-framework/
│
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       ├── java/
│       │   ├── apiKeyTest/             # API Key authentication tests
│       │   │   └── APIKeyTest.java
│       │   ├── campus/                 # Campus-related API tests
│       │   │   ├── _09_CountryTest.java
│       │   │   └── Country.java
│       │   ├── goRest/                 # GoRest API tests
│       │   │   ├── _07_GoRestUsersTest.java
│       │   │   └── _08_GoRestCommentTest.java
│       │   ├── model/                  # POJO models for JSON data
│       │   │   ├── Location.java
│       │   │   ├── Place.java
│       │   │   ├── ToDo.java
│       │   │   └── User.java
│       │   ├── reqres/                 # ReqRes API tests
│       │   │   └── ReqResUserTest.java
│       │   ├── restBooker/             # Restful-Booker API tests
│       │   │   └── RestfulBookerAPITest.java
│       │   ├── _01_APITestIntro.java   # Intro to basic API tests and assertions
│       │   ├── _02_APITestSpec.java    # Reusable request/response specifications
│       │   ├── _03_APITestExtract.java # Extracting and asserting response data
│       │   ├── _04_APITestPOJO.java    # Deserialization into POJOs
│       │   ├── _05_Tasks.java          # Task-style practical API test cases
│       │   └── _06_PathAndJSONPath.java# Path vs JSONPath examples and comparison
│       │
│       └── resources/
│           └── TestNG.xml              # TestNG suite configuration
├── target/                             # Build output
├── images/                             # Project images
│   └── HTTP_Status_Codes.png           # HTTP status codes reference image
├── pom.xml                             # Maven configuration file
└── README.md                           # Project README
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
