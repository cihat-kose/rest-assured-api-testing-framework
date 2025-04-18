
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

The **rest-assured-api-testing-framework** is a Java-based API testing framework designed to automate REST API validations using **RestAssured**, **TestNG**, and **Hamcrest**. It supports comprehensive CRUD operations across multiple public APIs and includes structured logging, assertions, and POJO-based deserialization with **Jackson**. The framework leverages **DataFaker** to generate dynamic and localized test data, enhancing variability in test scenarios.

### Tested APIs

This framework includes test scenarios against the following public/mock APIs:

- **GoRest API**  
  User and comment management with full CRUD support.  
  🔗 https://gorest.co.in/public/v2

- **Campus API**  
  Simulates country/location management with role-based authentication.  
  🔗 https://test.mersys.io

- **Zippopotam.us API**  
  Provides postal code and location data for validating JSON structure and values.  
  🔗 http://api.zippopotam.us

- **ReqRes API**  
  Allows testing of pagination, user creation, and BDD validations.  
  🔗 https://reqres.in/api

- **Restful-Booker API**  
  Designed to simulate hotel booking flows with authentication and full lifecycle actions (`POST`, `PUT`, `PATCH`, `DELETE`).  
  🔗 https://restful-booker.herokuapp.com

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

---

### Scenarios Covered

This framework is designed to validate a wide variety of API behaviors and edge cases:

- **Authentication Testing**
  - Token-based auth flow including token generation and usage (e.g., Restful-Booker API).
  - Header-based API key validation to access secured endpoints (e.g., Campus API).

- **CRUD Operations**
  - Create, read, update, and delete scenarios for entities such as users, countries, bookings, and comments.

- **Parameterized Testing**
  - Uses `pathParam` and `queryParam` to test pagination, filtering, and dynamic path building.

- **Data Extraction & Assertion**
  - Extracts fields using `.path()` and `.jsonPath()` for validation.
  - Asserts response bodies and metadata with Hamcrest matchers and TestNG.

- **POJO-Based Validation**
  - Maps entire or partial JSON responses to POJOs for deep structure validation.

- **Reusable Request/Response Specs**
  - Defines centralized configurations to promote cleaner test classes and reduce duplication.

- **Logging and Reporting**
  - Logs full request and response data.
  - Integrates with reporting tools (e.g., Jenkins) for CI/CD visibility.

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
